package com.example.bubble;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private int que_id;
    static Integer num;
    static String teachername;
    static String subjectname;
    static ArrayList questions;
    static String name_set;

    public Item(int que_id,String question, String a, String b, String c, String d ) {
        this.que_id = que_id;
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public int getQue_id() {
        return que_id;
    }

    public void setQue_id(int que_id) {
        this.que_id = que_id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", que_id=" + que_id +
                '}';
    }
}

