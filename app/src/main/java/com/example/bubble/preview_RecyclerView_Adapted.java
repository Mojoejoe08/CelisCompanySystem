package com.example.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class preview_RecyclerView_Adapted extends RecyclerView.Adapter<preview_RecyclerView_Adapted.ViewHolder>{
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;

    public preview_RecyclerView_Adapted(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_list_item, parent,false);
        preview_RecyclerView_Adapted.ViewHolder holder = new preview_RecyclerView_Adapted.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.inum.setText(String.valueOf(items.get(position).getQue_id()));
        holder.txtItem.setText(items.get(position).getQuestion());
        holder.txtItemA.setText(items.get(position).getA());
        holder.txtItemB.setText(items.get(position).getB());
        holder.txtItemC.setText(items.get(position).getC());
        holder.txtItemD.setText(items.get(position).getD());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtItem;
        private TextView txtItemA;
        private TextView txtItemB;
        private TextView txtItemC;
        private TextView txtItemD;
        private TextView inum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            txtItemA = itemView.findViewById(R.id.txtItemA);
            txtItemB = itemView.findViewById(R.id.txtItemB);
            txtItemC = itemView.findViewById(R.id.txtItemC);
            txtItemD = itemView.findViewById(R.id.txtItemD);
            inum = itemView.findViewById(R.id.inum);
        }
    }
}

