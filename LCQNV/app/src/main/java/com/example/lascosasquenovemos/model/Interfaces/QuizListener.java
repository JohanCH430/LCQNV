package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.FirebaseListener;
import com.example.lascosasquenovemos.model.QuizModelo;

public interface QuizListener{

    void onQuizReadSucced(QuizModelo quiz);
    void onQuizWriteSucced(Boolean bool);
}
