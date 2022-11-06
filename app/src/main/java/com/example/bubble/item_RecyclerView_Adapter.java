package com.example.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class item_RecyclerView_Adapter extends RecyclerView.Adapter<item_RecyclerView_Adapter.ViewHolder>{
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private String aw;
    public item_RecyclerView_Adapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_item, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (items.get(position).getA().equals("")){
            holder.txtItem.setHint(items.get(position).getQuestion());
        }else{
            holder.txtItem.setText(items.get(position).getQuestion());
            holder.txtItemA.setText(items.get(position).getA());
            holder.txtItemB.setText(items.get(position).getB());
            holder.txtItemC.setText(items.get(position).getC());
            holder.txtItemD.setText(items.get(position).getD());
        }
        holder.txtItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    aw = holder.txtItem.getText().toString();
                    items.get(position).setQuestion(aw);
                }
            }
        });
        holder.txtItemA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    aw = holder.txtItemA.getText().toString();
                    items.get(position).setA(aw);
                }
            }
        });
        holder.txtItemB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    aw = holder.txtItemB.getText().toString();
                    items.get(position).setB(aw);
                }
            }
        });
        holder.txtItemC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    aw = holder.txtItemC.getText().toString();
                    items.get(position).setC(aw);
                }
            }
        });
        holder.txtItemD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    aw = holder.txtItemD.getText().toString();
                    items.get(position).setD(aw);
                    Toast.makeText(context,
                            items.get(position).getQuestion()+
                            items.get(position).getA()+
                            items.get(position).getB()+
                            items.get(position).getC()+
                            items.get(position).getD()
                            , Toast.LENGTH_LONG).show();
                }
            }
        });
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
        private EditText txtItem;
        private EditText txtItemA;
        private EditText txtItemB;
        private EditText txtItemC;
        private EditText txtItemD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItem);
            txtItemA = itemView.findViewById(R.id.txtItemA);
            txtItemB = itemView.findViewById(R.id.txtItemB);
            txtItemC = itemView.findViewById(R.id.txtItemC);
            txtItemD = itemView.findViewById(R.id.txtItemD);
        }
    }
}
