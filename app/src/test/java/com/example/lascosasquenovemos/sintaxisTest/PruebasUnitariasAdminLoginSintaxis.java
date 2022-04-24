package com.example.lascosasquenovemos.sintaxisTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lascosasquenovemos.bll.AdminLoginBll;
import com.example.lascosasquenovemos.bll.TematicaBll;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;

public class PruebasUnitariasAdminLoginSintaxis {
    private static AdminLoginBll bll;
    //Se inicializa el negocio para poder hacer los test de unidad sobre la clase correspondiente
    @BeforeAll
    public static void setUpTests(){
        bll = new AdminLoginBll();
    }

    //Test pw no nulo y menos que 25
    @Test
    public void testSintaxisLoginCorrecto1() {
        assertTrue(AdminLoginBll.comprobarSintaxis("pw"));
    }
    //Test pw nulo
    @Test
    public void testSintaxisLoginFalso() {
        assertFalse(AdminLoginBll.comprobarSintaxis(""));
    }
    //Test pw mayor de 25 caracteres
    @Test
    public void testSintaxisLoginFalso2() {
        char[] chars = new char[26];
        Arrays.fill(chars, 'a');
        String pw = new String(chars);
        assertFalse(AdminLoginBll.comprobarSintaxis(pw));
    }




}
