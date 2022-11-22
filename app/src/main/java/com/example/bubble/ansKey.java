package com.example.bubble;

public class ansKey {
    private int answer_num;
    private String ans_num;

    public ansKey(String ans_num) {
        this.ans_num = ans_num;
    }

    public String getAns_num() {
        return ans_num;
    }

    public void setAns_num(String ans_num) {
        this.ans_num = ans_num;
    }

    public ansKey(int answer_num, String ans_num) {
        this.answer_num = answer_num;
        this.ans_num = ans_num;
    }

    public int getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(int answer_num) {
        this.answer_num = answer_num;
    }

    @Override
    public String toString() {
        return "ansKey{" +
                "answer_num=" + answer_num +
                ", ans_num='" + ans_num + '\'' +
                '}';
    }
}
