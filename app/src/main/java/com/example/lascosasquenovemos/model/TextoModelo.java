package com.example.lascosasquenovemos.model;

import java.io.Serializable;

public class TextoModelo implements Serializable {
    String IDTexto;
    String título;
    String texto;
    String temática;

    public TextoModelo(){
    }

    public TextoModelo(String IDTexto, String título, String texto, String temática) {
        this.IDTexto = IDTexto;
        this.título = título;
        this.texto = texto;
        this.temática = temática;
    }

    public String getIDTexto() {
        return IDTexto;
    }

    public void setIDTexto(String IDTexto) {
        this.IDTexto = IDTexto;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTemática() {
        return temática;
    }

    public void setTemática(String temática) {
        this.temática = temática;
    }
}
