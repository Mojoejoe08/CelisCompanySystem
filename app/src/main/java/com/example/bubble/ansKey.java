package com.example.bubble;

public class ansKey {
    private int ans_num;

    public ansKey(int ans_num) {
        this.ans_num = ans_num;
    }

    public int getAns_num() {
        return ans_num;
    }

    public void setAns_num(int ans_num) {
        this.ans_num = ans_num;
    }

    @Override
    public String toString() {
        return "ansKey{" +
                "ans_num=" + ans_num +
                '}';
    }
}
