package com.sourcecode.apparticulos.response;

import com.sourcecode.apparticulos.models.Fabricante;

import java.util.List;

public class FabricanteResponse {
    private List<Fabricante> fabricanteList;

    public List<Fabricante> getFabricanteList() {
        return fabricanteList;
    }

    public void setFabricanteList(List<Fabricante> fabricanteList) {
        this.fabricanteList = fabricanteList;
    }
}
