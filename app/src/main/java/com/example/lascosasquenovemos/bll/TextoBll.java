package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TextoModelo;

public class TextoBll {

    public static void crearTexto(TextoModelo texto, TextListener tl){TextoDAL.creartexto(texto,tl);}

    public static void leerTexto(String id, TextListener tl){TextoDAL.leerTexto(id,tl);}

    public static boolean comprobarSintaxis(TextoModelo texto){
        if(!texto.getTexto().equals("") && texto.getTexto().length() <= 2000)
            return true;
        return false;
    }

}