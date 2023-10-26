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
import java.util.Optional;
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
            Optional<Fabricante> fabricanteOptional = iFabricanteDao.findById(id);
            if (fabricanteOptional.isPresent()) {
                fabricanteList.add(fabricanteOptional.get());
                fabricanteResponseRest.getFabricanteResponse().setFabricanteList(fabricanteList);
                fabricanteResponseRest.setMetadata("Respuesta Exitosa", "200", "Fabriante Encontrado");
            } else {
                log.severe("no se encontro el Fabricante con id " + id);
                fabricanteResponseRest.setMetadata("Respuesta no Exitosa", "404", "Fabricante con id " + id + "No encontrado");
                new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al buscar Fabricante" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Error al buscar Fabricante", "500", "Error al buscar Fabricante");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante) {
        log.info("Ejecutando crearFabricante(Fabricante fabricante)");
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();
        List<Fabricante> fabricanteList = new ArrayList<>();
        try {
            Fabricante fabricanteGuardado = iFabricanteDao.save(fabricante);
            if (fabricanteGuardado != null) {
                fabricanteList.add(fabricanteGuardado);
                fabricanteResponseRest.getFabricanteResponse().setFabricanteList(fabricanteList);
                fabricanteResponseRest.setMetadata("Respuesta Exitosa", "200", "Fabricante Guardado");
            } else {
                log.severe("No se creo el Fabricante");
                fabricanteResponseRest.setMetadata("Error al guardar al Fabricante", "500", "Fabricante no Guardado");
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception exception) {
            log.severe("Error al guardar Fabricante" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Errog al Guardar Articulo", "500", "Articulo no guardado");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Fabricante fabricante, Long id) {
        log.info("Ejecutando actualizarFabricante(Fabricante fabricante, Long id)");
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();
        List<Fabricante> fabricanteList = new ArrayList<>();
        try {
            Optional<Fabricante> fabricanteOptionalBuscado = iFabricanteDao.findById(id);
            if (fabricanteOptionalBuscado.isPresent()) {
                fabricanteOptionalBuscado.get().setNombre(fabricante.getNombre());
                Fabricante fabricanteActualizado = iFabricanteDao.save(fabricanteOptionalBuscado.get());
                if (fabricanteActualizado != null) {
                    fabricanteList.add(fabricanteActualizado);
                    fabricanteResponseRest.getFabricanteResponse().setFabricanteList(fabricanteList);
                    fabricanteResponseRest.setMetadata("Respuesta Exitosa", "200", "Fabricante Actualizado");
                } else {
                    log.severe("No se actualizo Fabricante ");
                    fabricanteResponseRest.setMetadata("Error al actualizar Fabricante", "500", "Fabricante no Actualizado");
                    return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                log.severe("No se actualizo Fabricante con id " + id);
                fabricanteResponseRest.setMetadata("Respuesta no Exitosa", "404", "Fabricante " + id + "No actualizado");
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al actualizar Fabricante" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Error al actualizar Fabricante", "500", "Fabricante no Actuzlizado");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id) {
        log.info("Ejecutando eliminarFabricante(Long id)");
        FabricanteResponseRest fabricanteResponseRest = new FabricanteResponseRest();
        try {
            Optional<Fabricante> fabricanteOptionalBuscado = iFabricanteDao.findById(id);
            if (fabricanteOptionalBuscado.isPresent()) {
                iFabricanteDao.delete(fabricanteOptionalBuscado.get());
                fabricanteResponseRest.setMetadata("Respuesta Exitosa", "200", "Fabricante Eliminado");
            } else {
                log.severe("No se encontro Fabricante con id" + id);
                fabricanteResponseRest.setMetadata("Respuesta No Exitosa", "404", "Fabricante " + id + "No encontrado");
                return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al eliminar Fabricante" + exception.getMessage());
            exception.getStackTrace();
            fabricanteResponseRest.setMetadata("Error al eliminar Fabricante", "500", "Fabricante no Eliminaro");
            return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(fabricanteResponseRest, HttpStatus.OK);
    }
}
