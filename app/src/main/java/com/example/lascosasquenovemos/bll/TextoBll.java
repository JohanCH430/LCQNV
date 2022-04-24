package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TextoModelo;

public class TextoBll {

    public static void crearTexto(TextoModelo texto, TextListener tl){TextoDAL.creartexto(texto,tl);}

    public static void leerTexto(String id, TextListener tl){TextoDAL.leerTexto(id,tl);}

    public static void leerTodoTexto(TextListener tl){TextoDAL.leerTodoTexto(tl);}
    public static void leerTextoTematica(TextListener tl){TextoDAL.leerTodoTextoTematica(tl);}

    public static int comprobarSintaxis(TextoModelo texto){
        if(texto.getTexto().equals("") || texto.getTexto().length() > 2000 || texto.getTexto().trim().isEmpty()) return -1;
        if (texto.getTítulo().equals("") || texto.getTítulo().trim().isEmpty()) return -2;
        if (texto.getTemática().equals("") || texto.getTemática().equals("Temática")) return -3;
        return 0;
    }

}
