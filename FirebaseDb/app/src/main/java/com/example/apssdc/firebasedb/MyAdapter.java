package com.example.apssdc.firebasedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HoldView> {
    Context ct;
    List<MyModel> list;

    public MyAdapter(Context ct, List<MyModel> list) {
        this.ct = ct;
        this.list = list;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoldView(LayoutInflater.from(ct)
                .inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, int position) {
        holder.tv.setText(list.get(position).getSname());
        holder.tv1.setText(list.get(position).getSroll());
        holder.tv2.setText(list.get(position).getSnumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoldView extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2;
        ImageButton edit,del;
        public HoldView(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.sname);
            tv1 = itemView.findViewById(R.id.sroll);
            tv2 = itemView.findViewById(R.id.snumber);
            edit = itemView.findViewById(R.id.edit);
            del = itemView.findViewById(R.id.del);
        }
    }
}
