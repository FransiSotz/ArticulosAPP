package com.sourcecode.apparticulos.response;

import com.sourcecode.apparticulos.models.Articulo;

import java.util.List;

public class ArticuloResponse {
    private List<Articulo> listaArticulos;

    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(List<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }
}
