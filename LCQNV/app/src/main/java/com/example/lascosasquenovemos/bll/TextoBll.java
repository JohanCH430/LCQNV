package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.TextoModelo;

public class TextoBll {

    private TextoDAL textoDAL;

    public boolean crearTexto(TextoModelo texto){

        if(!comprobarSintaxis(texto)){
            return false;
        } else{
           int i = textoDAL.crearTexto(texto);

           if(i == 0){
               return true;
           } else{
               return false;
           }
        }


    }
    public boolean comprobarSintaxis(TextoModelo texto){
        if(!texto.getTexto().equals("") && texto.getTexto().length() <= 2000)
            return true;
        return false;
    }

}
