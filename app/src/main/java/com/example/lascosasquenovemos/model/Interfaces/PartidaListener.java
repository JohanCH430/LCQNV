package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.PartidaModelo;

public interface PartidaListener {

    void onPartidaReadSuccess(PartidaModelo pM);
    void onPartidaWriteSuccess(String codigo, Boolean correct);
}
