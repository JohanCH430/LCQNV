package com.example.lascosasquenovemos.model;

import java.util.HashMap;

public class PartidaModelo {

    private String idPartida;
    private HashMap<Integer, PantallaModelo> pantallasPartida;

    public PartidaModelo(String idPartida, HashMap<Integer, PantallaModelo> pantallasPartida) {
        this.idPartida = idPartida;
        this.pantallasPartida = pantallasPartida;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public HashMap<Integer, PantallaModelo> getPantallasPartida() {
        return pantallasPartida;
    }

    public void setPantallasPartida(HashMap<Integer, PantallaModelo> pantallasPartida) {
        this.pantallasPartida = pantallasPartida;
    }

    public void addPantalla(PantallaModelo nuevaPantalla){
        pantallasPartida.put(pantallasPartida.size(), nuevaPantalla);
    }
}
