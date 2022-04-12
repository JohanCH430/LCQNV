package com.example.lascosasquenovemos.model;

public class TematicaModelo {

    String IDTematica;
    String descripcion;

    public TematicaModelo(String IDTematica, String descripcion) {
        this.IDTematica = IDTematica;
        this.descripcion = descripcion;
    }

    public String getIDTematica() {
        return IDTematica;
    }

    public void setIDTematica(String IDTematica) {
        this.IDTematica = IDTematica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
