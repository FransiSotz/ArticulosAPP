package com.sourcecode.apparticulos.controllers;

import com.sourcecode.apparticulos.models.Fabricante;
import com.sourcecode.apparticulos.response.FabricanteResponseRest;
import com.sourcecode.apparticulos.services.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FabricanteController {
    @Autowired
    private IFabricanteService iFabricanteService;

    @GetMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> mostrarFabricantes() {
        return iFabricanteService.mostrarFabricantes();
    }

    @GetMapping("/fabricante/{id}")
    public ResponseEntity<FabricanteResponseRest> consultarPorId(@PathVariable Long id) {
        return iFabricanteService.buscarFabricantePorId(id);
    }

    @PostMapping("/fabricante")
    public ResponseEntity<FabricanteResponseRest> guardarFabricante(@RequestBody Fabricante fabricante) {
        return iFabricanteService.crearFabricante(fabricante);
    }

    @PutMapping("/fabricante/{id}")
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(@RequestBody Fabricante fabricante, @PathVariable Long id) {
        return iFabricanteService.actualizarFabricante(fabricante, id);
    }

    @DeleteMapping("/fabricante/{id}")
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(@PathVariable Long id) {
        return iFabricanteService.eliminarFabricante(id);
    }
}
