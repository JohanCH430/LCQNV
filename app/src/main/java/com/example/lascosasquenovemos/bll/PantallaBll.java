package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.model.Interfaces.PantallaListener;
import com.example.lascosasquenovemos.model.PantallaModelo;

public class PantallaBll {

    public static void crearPantalla(PantallaListener pL) {TextoBll.leerTodoTexto(pL);
    }

    public static boolean comprobarSintaxis(PantallaModelo pM){
        //Primero se comprueban si la pantalla tiene un Quiz y un texto
        if (pM.getQuiz() == null) return false;
        if (pM.getTexto() == null) return false;
        //Después se comprueba que el formato este correcto para ese texto usando los comprobar sintaxis de las clases correspondientes
        if (!QuizBll.comprobarSintaxis(pM.getQuiz())) return false;
        if (TextoBll.comprobarSintaxis(pM.getTexto()) != 0) return false;
        return true;
    }
}
