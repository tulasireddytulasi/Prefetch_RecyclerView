package com.example.prefetch_recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class RecyclerviewAdapter extends PagedListAdapter<TiktokDataModel.Msg, RecyclerviewAdapter.MyViewHolder> {

    private Context context;

    protected RecyclerviewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TiktokDataModel.Msg resultsBean = getItem(position);
        if (resultsBean != null) {

            Glide.with(context)
                    .load("https://hcapi.helocherry.com/" + resultsBean.getThum())
                    .placeholder(R.drawable.poster)
                    .centerCrop()
                    .into(holder.imageView);


        } else {
            Toast.makeText(context, "No Data", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<TiktokDataModel.Msg> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TiktokDataModel.Msg>() {
                @Override
                public boolean areItemsTheSame(@NonNull TiktokDataModel.Msg oldItem, @NonNull TiktokDataModel.Msg newItem) {
                    return oldItem.getFb_id() == newItem.getFb_id();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TiktokDataModel.Msg oldItem, @NonNull TiktokDataModel.Msg newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
