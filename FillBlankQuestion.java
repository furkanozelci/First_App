package com.ahmet1.myapplication;

public class FillBlankQuestion {
    private String questionText;
    private String correctAnswer;

    public FillBlankQuestion(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}