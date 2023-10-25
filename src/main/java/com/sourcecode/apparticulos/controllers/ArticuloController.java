package com.sourcecode.apparticulos.controllers;

import com.sourcecode.apparticulos.models.Articulo;
import com.sourcecode.apparticulos.response.ArticuloResponseRest;
import com.sourcecode.apparticulos.services.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticuloController {
    @Autowired
    private IArticuloService iArticuloService;

    @GetMapping("/articulos")
    public ResponseEntity<ArticuloResponseRest> mostrarArticulos() {
        return iArticuloService.mostrarArticulos();
    }

    @GetMapping("/articulo/{id}")
    public ResponseEntity<ArticuloResponseRest> consultarPorId(@PathVariable Long id) {
        return iArticuloService.buscarArticuloPorId(id);
    }

    @PostMapping("/articulo")
    public ResponseEntity<ArticuloResponseRest> guardarArticulo(@RequestBody Articulo articulo) {
        return iArticuloService.crearArticulo(articulo);
    }

    @PutMapping("/articulo/{id}")
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(@RequestBody Articulo articulo, @PathVariable Long id) {
        return iArticuloService.actualizarArticulo(articulo, id);
    }

    @DeleteMapping("/articulo/{id}")
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(@PathVariable Long id) {
        return iArticuloService.eliminarArticulo(id);
    }
}
