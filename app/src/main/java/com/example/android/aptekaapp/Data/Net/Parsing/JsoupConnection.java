package com.example.android.aptekaapp.Data.Net.Parsing;


import android.util.Log;

import com.example.android.aptekaapp.Data.Entity.DragEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class JsoupConnection  implements Callable<List<DragEntity>> {

    String JSOUP_BASE_URL =
                "https://www.apteka24.ua/category/";

    private String url;
    private String request;

    private JsoupConnection(String request) throws MalformedURLException {
        Log.d("2810","JsoupConnection request is "+convertationSearhString(request));
        this.request =  request;
        this.url = JSOUP_BASE_URL+convertationSearhString(request)+"/";
    }

    static JsoupConnection createGET(String url) throws MalformedURLException {
        return new JsoupConnection(url);
    }

    @Override
    public List<DragEntity> call() throws Exception {
        return connectToJsoup();
    }

    public List<DragEntity> connectToJsoup(){

        List<DragEntity>resultList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(this.url).get();
            Elements titleAndPriceElements = document.getElementsByClass("goodstext");
            Log.d("1212",String.valueOf(titleAndPriceElements.size()));
            Elements photoElements = document.getElementsByClass("tovarphoto");
            Log.d("1212",String.valueOf(photoElements.size()));
            resultList = populateList(titleAndPriceElements,photoElements);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return resultList;
    }


    private List<DragEntity> populateList(Elements titleAndPriceElements,Elements photoElements){
        List<DragEntity>result = new ArrayList<>();
        Elements photos = photoElements.select("img");
        for(int i=0;i<titleAndPriceElements.size();i++){
            Elements names = titleAndPriceElements.get(i).getElementsByClass("goodsname");
            Elements coasts = titleAndPriceElements.get(i).getElementsByClass("goodscoast");
            for(int k=0;k<names.size();k++){
                DragEntity dragEntity = new DragEntity();
                dragEntity.setDragPhoto(photos.get(i).absUrl("src"));
                dragEntity.setDragName(names.get(k).text());
                dragEntity.setGroupName(request);
                if (coasts.get(k).text().length() == 0) {
                    continue;
                } else {
                    dragEntity.setDragPrice(coasts.get(k).text());
                }
                result.add(dragEntity);
            }
        }

        return result;
    }



    private String cyr2lat(char ch){
        switch (ch){
            case 'А': return "A";
            case 'Б': return "B";
            case 'В': return "V";
            case 'Г': return "G";
            case 'Д': return "D";
            case 'Е': return "E";
            case 'Ё': return "JE";
            case 'Ж': return "ZH";
            case 'З': return "Z";
            case 'И': return "I";
            case 'Й': return "Y";
            case 'К': return "K";
            case 'Л': return "L";
            case 'М': return "M";
            case 'Н': return "N";
            case 'О': return "O";
            case 'П': return "P";
            case 'Р': return "R";
            case 'С': return "S";
            case 'Т': return "T";
            case 'У': return "U";
            case 'Ф': return "F";
            case 'Х': return "KH";
            case 'Ц': return "C";
            case 'Ч': return "CH";
            case 'Ш': return "SH";
            case 'Щ': return "JSH";
            case 'Ъ': return "HH";
            case 'Ы': return "IH";
            case 'Ь': return "";
            case 'Э': return "EH";
            case 'Ю': return "JU";
            case 'Я': return "JA";
            default: return String.valueOf(ch);
        }
    }

    private String cyr2lat(String s){
        StringBuilder sb = new StringBuilder(s.length()*2);
        for(char ch: s.toCharArray()){
            sb.append(cyr2lat(ch));
        }
        return sb.toString();
    }

    private String convertationSearhString(String startString){
        String result = cyr2lat(startString.toUpperCase());
        return result.toLowerCase();
    }


}

