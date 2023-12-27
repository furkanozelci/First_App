package com.ahmet1.myapplication;



import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

public class Question {
    private String englishWord;
    private String correctAnswer;
    private List<String> options;

    public Question(String englishWord, String correctAnswer, String option2, String option3) {
        this.englishWord = englishWord;
        this.correctAnswer = correctAnswer;
        options = new LinkedList<>();
        options.add(correctAnswer);
        options.add(option2);
        options.add(option3);
        Collections.shuffle(options);
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }
}
