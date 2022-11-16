package com.example.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ansKey_RecyclerView_Adapter extends RecyclerView.Adapter<ansKey_RecyclerView_Adapter.ViewHolder> {
    private ArrayList<ansKey> ansList = new ArrayList<>();
    private Context context;
    public ansKey_RecyclerView_Adapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ansKey_RecyclerView_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answerkeyradiogroup, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ansKey_RecyclerView_Adapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.numTxt.setText(String.valueOf(ansList.get(position).getAns_num()+1));
        holder.radyo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbA:{
                        ansList.get(position).setAns_num(0);
                        Toast.makeText(context,String.valueOf(ansList.get(position).getAns_num()), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.rbB:{
                        ansList.get(position).setAns_num(1);
                        Toast.makeText(context,String.valueOf(ansList.get(position).getAns_num()), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.rbC:{
                        ansList.get(position).setAns_num(2);
                        Toast.makeText(context,String.valueOf(ansList.get(position).getAns_num()), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.rbD:{
                        ansList.get(position).setAns_num(3);
                        Toast.makeText(context, String.valueOf(ansList.get(position).getAns_num()), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + checkedId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ansList.size();
    }
    public void setItems(ArrayList<ansKey> ansList) {
        this.ansList = ansList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RadioGroup radyo;
        private TextView numTxt;
        private RadioButton rbA;
        private RadioButton rbB;
        private RadioButton rbC;
        private RadioButton rbD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radyo = (RadioGroup) itemView.findViewById(R.id.radyo);
            numTxt = (TextView) itemView.findViewById(R.id.numTxt);
            rbA = (RadioButton) itemView.findViewById(R.id.rbA);
            rbB = (RadioButton) itemView.findViewById(R.id.rbB);
            rbC = (RadioButton) itemView.findViewById(R.id.rbC);
            rbD = (RadioButton) itemView.findViewById(R.id.rbD);
        }
    }
}
