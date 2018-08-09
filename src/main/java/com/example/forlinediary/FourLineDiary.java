package com.example.forlinediary;

public class FourLineDiary {
    private String date;
    private String phenomenon; // 事象
    private String notice; // 気づき
    private String lesson; // 教訓
    private String declaration; // 宣言

    public FourLineDiary(String date, String phenomenon, String notice, String lesson, String declaration) {
        this.date = date;
        this.phenomenon = phenomenon;
        this.notice = notice;
        this.lesson = lesson;
        this.declaration = declaration;
    }
}
