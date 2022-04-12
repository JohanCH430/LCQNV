package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TematicaDAL;
import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.TematicaModelo;

public class TematicaBll {

    public static boolean comprobarSintaxis(TematicaModelo tematica) {

        if(tematica.getIDTematica().trim().isEmpty()){
            return false;
        } else{
            return true;
        }
    }

    public static void crearTematica(TematicaModelo tematica, TematicaListener tL) {
        TematicaDAL.crearTematica(tematica, tL);
    }
}
