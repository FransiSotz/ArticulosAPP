package com.sourcecode.apparticulos.services;

import com.sourcecode.apparticulos.models.Articulo;
import com.sourcecode.apparticulos.response.ArticuloResponseRest;
import org.springframework.http.ResponseEntity;

public interface IArticuloService {
    public ResponseEntity<ArticuloResponseRest> mostrarArticulos();

    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id);

    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo);

    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id);

    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id);
}
