package com.example.lascosasquenovemos.bll;


import com.example.lascosasquenovemos.dal.AdminLoginDAL;
import com.example.lascosasquenovemos.model.Interfaces.AdminLoginListener;
import com.example.lascosasquenovemos.model.PantallaModelo;

public class AdminLoginBll {
    public static void LoginAdmin(String pw, AdminLoginListener aL) {
        AdminLoginDAL.leerContraseña(pw,aL);
    }

    public static boolean comprobarSintaxis(String pw){
        if(pw.equals(null) || pw.equals("")) return false;
        if(pw.length() > 25) return false;
        return true;
    }
}
