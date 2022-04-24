package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.bll.AdminLoginBll;
import com.example.lascosasquenovemos.model.Interfaces.AdminLoginListener;

public class AdminLoginVista extends AppCompatActivity implements AdminLoginListener{

    private Button btnLogin, btnAtras;
    private TextView contr, comprobacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_vista);
        btnLogin = findViewById(R.id.BtnLogin);
        btnAtras = findViewById(R.id.BtnAtrasLogin);
        contr = findViewById(R.id.Contraseña);
        comprobacion = findViewById(R.id.TextErrorLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Comprobar que la contraseña no es nula
                if(!AdminLoginBll.comprobarSintaxis(contr.getText().toString())){
                    comprobacion.setText("ERROR: La contraseña es nula o mayor de 25 caracteres");
                }
                else{
                    //Comprobar que existe dicho contraseña
                    //Si cumple con dichos condiciones pasa la siguiente pantalla sino sale un texto de error
                    AdminLoginBll.LoginAdmin(contr.getText().toString(),AdminLoginVista.this);
                }

            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onAdminLoginReadSuccess(String pw) {
        if(pw == null){
            comprobacion.setText("Contraseña incorrecta");
        }
        else{
            startActivity(new Intent(AdminLoginVista.this, AdminVista.class));
        }
    }
}