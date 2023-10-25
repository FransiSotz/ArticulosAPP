package com.sourcecode.apparticulos.services;

import com.sourcecode.apparticulos.models.Fabricante;
import com.sourcecode.apparticulos.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {
    public ResponseEntity<FabricanteResponseRest> mostrarFabricantes();

    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id);

    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante);

    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id);

    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id);
}
