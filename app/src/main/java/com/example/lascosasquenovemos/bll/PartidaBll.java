package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class PartidaBll {

    public static void crearPartida(PartidaModelo pM, PartidaListener pL){/*TODO llamar a partida DAL*/};
    public static void leerPartida(String id, PartidaListener pL){/*TODO llamar a partida DAL*/}

    public static boolean comprobarSintaxisPartida(PartidaModelo pM){
        //Cada pantalla se comprueba al crearse pro lo que solo se comprueba que el id no sea nulo y que la partida tenga al menos una pantalla
        if (pM.getIdPartida().equals(null) || pM.getIdPartida().trim().isEmpty()) return false;
        if (pM.getPantallasPartida().size() == 0) return false;
        return true;
    }
}
