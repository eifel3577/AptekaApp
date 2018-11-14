package com.example.android.aptekaapp.Presentation.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.aptekaapp.Presentation.Model.DragModel;
import com.example.android.aptekaapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * адаптер для управления списком{@link DragModel}.
 */
public class DragListAdapter extends RecyclerView.Adapter<DragListAdapter.DragViewHolder> {


    Picasso picasso;

    /**интерфейс для обработки нажатия на юзере.совместный интерфейс с UserFragment */
    public interface OnItemClickListener {
        void onUserItemClicked(DragModel userModel);
    }

    /**коллекция с которой будет работать адаптер */
    private List<DragModel> dragsCollection;
    private final LayoutInflater layoutInflater;

    /**обьект интерфейса */
    private OnItemClickListener onItemClickListener;


    @Inject
    DragListAdapter(Context context) {
        /**инициализация LayoutInflater и дефолтная инициализация коллекции пустом списком */
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dragsCollection = Collections.emptyList();
        //TODO пикассо получать из даггера!
        picasso = new Picasso.Builder(context).
                build();
    }

    /**возвращает размер коллекции */
    @Override public int getItemCount() {
        return (this.dragsCollection != null) ? this.dragsCollection.size() : 0;
    }

    /**создает возвращает вьюхолдер,работающий с итемом */
    @Override public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.drag_item, parent, false);
        return new DragViewHolder(view);
    }

    /**получает холдер и позицию,заполняет итем */
    @Override public void onBindViewHolder(DragViewHolder holder, final int position) {
        final DragModel dragModel = this.dragsCollection.get(position);
        //TODO кроме названия добавить цену
        holder.dragTitle.setText(dragModel.getDragName());
        holder.dragPrice.setText(dragModel.getDragPrice());
        picasso.load(dragModel.getDragPhoto()).into(holder.dragImage);
        holder.dragTitle.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (DragListAdapter.this.onItemClickListener != null) {
                    DragListAdapter.this.onItemClickListener.onUserItemClicked(dragModel);
                }
            }
        });
    }

    /**возвращает позицию */
    @Override public long getItemId(int position) {
        return position;
    }

    /**сначала проверяет не нулевая ли коллекция,затем инициализирует полученной коллекцией usersCollection
     * и обновляет экран*/
    public void setDragsCollection(Collection<DragModel> dragsCollection) {
        this.validateUsersCollection(dragsCollection);
        this.dragsCollection = (List<DragModel>) dragsCollection;
        this.notifyDataSetChanged();
    }

    /**получает лисенер */
    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**проверяет не нулевая ли коллекция,если да выбрасывает исключение */
    private void validateUsersCollection(Collection<DragModel> dragsCollection) {
        if (dragsCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    /**класс вьюхолдера */
    public static class DragViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dragName)
        TextView dragTitle;
        @BindView(R.id.dragPrice)
        TextView dragPrice;
        @BindView(R.id.drag_image)
        ImageView dragImage;

        DragViewHolder(View itemView) {
            super(itemView);
            dragTitle = itemView.findViewById(R.id.dragName);
            dragPrice = itemView.findViewById(R.id.dragPrice);
            dragImage = itemView.findViewById(R.id.drag_image);
        }
    }
}