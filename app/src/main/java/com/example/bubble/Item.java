package com.example.bubble;

public class Item {
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    static Integer num;
    static String teachername;
    static String subjectname;

    public Item(String question, String a, String b, String c, String d) {
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

    @Override
    public String toString() {
        return "Item{" +
                "question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }

}