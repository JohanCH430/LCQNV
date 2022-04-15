package com.example.lascosasquenovemos.model;

public class PantallaModelo {

    private TextoModelo texto;
    private QuizModelo quiz;

    public PantallaModelo(TextoModelo texto, QuizModelo quiz) {
        this.texto = texto;
        this.quiz = quiz;
    }

    public TextoModelo getTexto() {
        return texto;
    }

    public void setTexto(TextoModelo texto) {
        this.texto = texto;
    }

    public QuizModelo getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizModelo quiz) {
        this.quiz = quiz;
    }
}
