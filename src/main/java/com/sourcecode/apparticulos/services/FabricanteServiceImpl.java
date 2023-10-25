package com.sourcecode.apparticulos.services;

import com.sourcecode.apparticulos.dao.IFabricanteDao;
import com.sourcecode.apparticulos.models.Fabricante;
import com.sourcecode.apparticulos.response.FabricanteResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FabricanteServiceImpl implements IFabricanteService {
    private static final Logger log = Logger.getLogger(FabricanteServiceImpl.class.getName());
    @Autowired
    private IFabricanteDao iFabricanteDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> mostrarFabricantes() {
        log.info("Ejecutando mostrarFabricantes()");
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();
        try {
            List<Fabricante> fabricanteList = iFabricanteDao.findAll();
            fabricanteResponseRest.getFabricanteResponse().setFabricanteList(fabricanteList);
            fabricanteResponseRest.setMetadata("Respuesta Exitosa", "200", "Lista de Fabricantes");
        } catch (Exception exception) {
            log.severe("Error en metodo mostrarFabricantes()" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Respuesta No Exitosa", "500", "Error al Mostrar Fabricantes");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id) {
        log.info("Ejecutando buscarFabricantePorId(Long id)");
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();
        List<Fabricante> fabricanteList = new ArrayList<>();
        try {
        } catch (Exception exception) {
            log.severe("Error al buscar Fabricante" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Error al buscar Fabricante", "500", "Error al buscar Fabricante");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante) {
        return null;
    }

    @Override
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id) {
        return null;
    }
}
