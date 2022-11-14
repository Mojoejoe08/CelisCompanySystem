package com.example.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubble.entities.Sets;

import java.util.List;

public class NewSet_RecView_Adapter extends RecyclerView.Adapter<NewSet_RecView_Adapter.ViewHolder> {
    private Context context;
    private List<Sets> setsList;
    public NewSet_RecView_Adapter(Context context){
        this.context = context;
    }

    public void setSetsList(List<Sets> setsList){
        this.setsList = setsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewSet_RecView_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newset_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSet_RecView_Adapter.ViewHolder holder, int position) {
        holder.setBtn.setText(setsList.get(position).sets_name);
    }

    @Override
    public int getItemCount() {
        return this.setsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button setBtn;
        private Button setAnalysisBtn;
        private Button setDeleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setBtn = (Button) itemView.findViewById(R.id.setBtn);
            setAnalysisBtn = (Button) itemView.findViewById(R.id.setAnalysisBtn);
            setDeleteBtn = (Button) itemView.findViewById(R.id.setDeleteBtn);

        }
    }
}
