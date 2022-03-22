package com.example.lascosasquenovemos.bll;

import android.content.Context;

import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.TextoModelo;

public class TextoBll {

    private TextoDAL textoDAL;

    public boolean crearTexto(TextoModelo texto, Context appContext){

        if(!comprobarSintaxis(texto)){
            return false;
        } else{
           textoDAL = new TextoDAL(texto, appContext);
           textoDAL.escribirTexto();
           return true;
        }
    }

    public TextoModelo leerTexto(String id, Context appContext){
        TextoModelo texto = new TextoModelo(id, "","","");
        textoDAL = new TextoDAL(texto, appContext);
        textoDAL.leerTexto();
        TextoModelo aux= textoDAL.obtenerLecturaBD();
        texto = aux;
        return texto;
        }

    public boolean comprobarSintaxis(TextoModelo texto){
        if(!texto.getTexto().equals("") && texto.getTexto().length() <= 2000)
            return true;
        return false;
    }

}
