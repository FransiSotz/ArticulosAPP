package com.sourcecode.apparticulos.services;

import com.sourcecode.apparticulos.dao.IArticuloDao;
import com.sourcecode.apparticulos.models.Articulo;
import com.sourcecode.apparticulos.response.ArticuloResponseRest;
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
public class ArticuloServiceImpl implements IArticuloService {
    private static final Logger log = Logger.getLogger(ArticuloServiceImpl.class.getName());
    @Autowired
    private IArticuloDao iArticuloDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> mostrarArticulos() {
        log.info("Ejecutando mostrarArticulos()");
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();
        try {
            List<Articulo> articuloList = iArticuloDao.findAll();
            articuloResponseRest.getArticuloResponse().setListaArticulos(articuloList);
            articuloResponseRest.setMetadata("Respuesta Exitosa", "200", "Lista de Articulos");
        } catch (Exception exception) {
            log.severe("Error en metodo mostrarArticulos()" + exception.getMessage());
            exception.getStackTrace();
            articuloResponseRest.setMetadata("Respuesta No Exitosa", "500", "Error al Mostrar Articulos");
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ArticuloResponseRest> buscarArticuloPorId(Long id) {
        log.info("Ejecutando buscarArticuloPorId(Long id)");
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();
        List<Articulo> articuloList = new ArrayList<>();
        try {
            Optional<Articulo> articulo = iArticuloDao.findById(id);
            if (articulo.isPresent()) {
                articuloList.add(articulo.get());
                articuloResponseRest.getArticuloResponse().setListaArticulos(articuloList);
                articuloResponseRest.setMetadata("Respuesta Existosa", "200", "Articulo Encontrado");
            } else {
                log.severe("No se encontro articulo con id: " + id);
                articuloResponseRest.setMetadata("Respuesta no Exitosa", "404", "Articulo con id " + id + "No encontrado");
                return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al buscar Articulo" + exception.getMessage());
            exception.getStackTrace();
            articuloResponseRest.setMetadata("Error al consultar Articulo", "500", "Error al consultar Articulo");
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> crearArticulo(Articulo articulo) {
        log.info("Ejecutando crearArticulo(Articulo articulo)");
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();
        List<Articulo> articuloList = new ArrayList<>();
        try {
            Articulo articuloGuardado = iArticuloDao.save(articulo);
            if (articuloGuardado != null) {
                articuloList.add(articuloGuardado);
                articuloResponseRest.getArticuloResponse().setListaArticulos(articuloList);
                articuloResponseRest.setMetadata("Respuesta Exitosa", "200", "Articulo Creado");
            } else {
                log.severe("no se pudo crear el Articulo");
                articuloResponseRest.setMetadata("Error al guardar Articulo", "500", "No se puedo guardar Articulo");
                return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception exception) {
            log.severe("Error al guardar Articulo" + exception.getMessage());
            exception.getStackTrace();
            articuloResponseRest.setMetadata("Error al guardar Articulo", "500", "Error al guardar Articulo");
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> actualizarArticulo(Articulo articulo, Long id) {
        log.info("Ejecutando actualizarArticulo(Articulo articulo, Long id)");
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();
        List<Articulo> articuloList = new ArrayList<>();
        try {
            Optional<Articulo> articuloOptionalBuscado = iArticuloDao.findById(id);
            if (articuloOptionalBuscado.isPresent()) {
                articuloOptionalBuscado.get().setNombre(articulo.getNombre());
                articuloOptionalBuscado.get().setPrecio(articulo.getPrecio());
                Articulo articuloActualizado = iArticuloDao.save(articuloOptionalBuscado.get());
                if (articuloActualizado != null) {
                    articuloList.add(articuloActualizado);
                    articuloResponseRest.getArticuloResponse().setListaArticulos(articuloList);
                    articuloResponseRest.setMetadata("Respuesta Exitosa", "200", "Articulo Actualizado");
                } else {
                    log.severe("No se pudo actualizar articulo");
                    articuloResponseRest.setMetadata("Error al Actualizar Articulo", "500", "No se pudo actualizar el articulo");
                    return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                log.severe("No se pudo actualizar articulo con el id " + id);
                articuloResponseRest.setMetadata("Respuesta no Exitosa", "404", "No se encontro articulo con id" + id);
                return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al Actualizar el Articulo" + exception.getMessage());
            exception.getStackTrace();
            articuloResponseRest.setMetadata("Error al actualizar el Articulo", "500", "Error al actualizar el Articulo");
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ArticuloResponseRest> eliminarArticulo(Long id) {
        log.info("Ejecutando eliminarArticulo(Long id)");
        ArticuloResponseRest articuloResponseRest = new ArticuloResponseRest();
        try {
            Optional<Articulo> articuloOptionalBuscado = iArticuloDao.findById(id);
            if (articuloOptionalBuscado.isPresent()) {
                iArticuloDao.delete(articuloOptionalBuscado.get());
                articuloResponseRest.setMetadata("Respuesta Exitosa", "200", "Articulo Eliminado");
            } else {
                log.severe("No se encontro el articulo con el id" + id);
                articuloResponseRest.setMetadata("Respuesta no Exitosa", "404", "no se encontro articulo con id " + id);
                return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.severe("Error al Eliminar articulo " + exception.getMessage());
            exception.getStackTrace();
            articuloResponseRest.setMetadata("Error al eliminar el Articulo", "500", "Error al eliminar el Articulo");
            return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ArticuloResponseRest>(articuloResponseRest, HttpStatus.OK);
    }
}
