package com.umenq.soexample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umenq.soexample.MainActivity;
import com.umenq.soexample.R;
import com.umenq.soexample.bean.Bean;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private static final int NORMAL_VIEW = 0;
    private Context context;
    private List<Bean.DataBean> list;

    public MyAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){

        onItemClickListener= listener;

    }
     @NonNull
     @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View inflate = View.inflate(context, R.layout.item_layout, null);
         ViewHolder viewHolder = new ViewHolder(inflate);
         return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ViewGroup.LayoutParams layoutParams = holder.imageView.getLayoutParams();
        layoutParams.height = (int) ((100) + Math.random() * 200);

        holder.imageView.setLayoutParams(layoutParams);

        Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
        holder.textView.setTextColor(Color.rgb((int) ((100) + Math.random() * 200),(int) ((300) + Math.random() * 400),(int) ((200) + Math.random() * 600)));

        //监听
        if (getItemViewType(position) == NORMAL_VIEW) {
            if (onItemClickListener != null) {

                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        onItemClickListener.onItemClick(holder.imageView, holder.getLayoutPosition());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_ne);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
