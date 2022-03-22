package com.example.lascosasquenovemos.model;

public class QuizModelo {
    String idTexto;
    String idPregunta;
    String tipo;
    String pregunta;
    String respuesta;
    String solucion;
    public QuizModelo(){

    }
    public QuizModelo(String idTexto, String idPregunta, String tipo, String pregunta, String respuesta,
                      String solucion){
        this.idPregunta = idPregunta;
        this.idTexto = idTexto;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.solucion = solucion;
        this.tipo =tipo;

    }
    public String getIdTexto(){
        return idTexto;
    }

    public String getIdPregunta(){
        return idPregunta;
    }
    public String getTipo(){
        return tipo;
    }
    public String getPregunta(){
        return pregunta;

    }
    public String getRespuesta(){
        return respuesta;
    }
    public String getSolucion(){
        return solucion;
    }

    public void setIdTexto(String idTexto){
        this.idTexto = idTexto;
    }

    public void setIdPregunta(String idPregunta){
        this.idPregunta = idPregunta;

    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setPregunta(String pregunta){
        this.pregunta = pregunta;
    }
    public void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }
    public void setSolucion(String solucion){
        this.solucion = solucion;
    }
}
