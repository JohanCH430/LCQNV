package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.TextoModelo;

public interface TextListener{

    void onTextReadSucced(TextoModelo texto);
    void onTextWriteSucced(Boolean bool);
}