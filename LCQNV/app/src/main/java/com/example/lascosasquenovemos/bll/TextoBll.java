package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.TextoModelo;

public class TextoBll {

    private TextoDAL textoDAL;

    public int crearTexto(TextoModelo texto){
        //TODO depende de los Transfers
        //return textoDAL.crearTexto(texto);
    }
    public boolean comprobarSintaxis(TextoModelo texto){
        if(!texto.getTexto().equals("") && texto.getTexto().length() <= 2000)
            return true;
        return false;
    }

}
