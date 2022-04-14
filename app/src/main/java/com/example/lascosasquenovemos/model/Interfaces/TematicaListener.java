package com.example.lascosasquenovemos.model.Interfaces;

import com.example.lascosasquenovemos.model.TematicaModelo;

import java.util.List;

public interface TematicaListener {

    void onTematicaReadSucced(TematicaModelo tematica);
    void onTematicaWriteSucced(Boolean bool);
    void onTematicaReadAllSucced(List<String> tematicas);

}
