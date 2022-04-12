package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.QuizModelo;

import java.util.List;

public interface QuizListener{

    void onQuizReadSucced(QuizModelo quiz);
    void onQuizWriteSucced(Boolean bool);
    void onQuizReadQuizByTextId(List<String> quizs);
}
