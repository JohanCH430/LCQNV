package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.TextoModelo;

import java.util.HashMap;
import java.util.List;

public interface TextListener{

    void onTextReadSucced(TextoModelo texto);
    void onTextWriteSucced(Boolean bool);
    void onTextReadAllSucced(List<String> textos);
    void onTextosTematicasReadAllSucceed(HashMap<String, List<String>> textos);
}