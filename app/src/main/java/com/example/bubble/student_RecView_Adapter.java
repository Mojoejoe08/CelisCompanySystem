package com.example.bubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bubble.entities.Student;

import java.util.List;


public class student_RecView_Adapter extends RecyclerView.Adapter<student_RecView_Adapter.ViewHolder> {
    private Context context;
    private List<Student> studentList;

    public student_RecView_Adapter(Context context) {
        this.context = context;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull student_RecView_Adapter.ViewHolder holder, int position) {
        holder.studentNameBtn.setText(studentList.get(position).stud_name);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button studentNameBtn;
        private Button studentNameDeleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameBtn = (Button) itemView.findViewById(R.id.studentNameBtn);
            studentNameDeleteBtn = (Button) itemView.findViewById(R.id.studentNameDeleteBtn);
        }
    }

}
