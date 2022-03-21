package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TextoDAL;

public class TextoBll {

    private TextoDAL textoDAL;

    public int crearTexto(){
        //TODO depende de los Transfers
        return textoDAL.crearTexto();
    }

}
