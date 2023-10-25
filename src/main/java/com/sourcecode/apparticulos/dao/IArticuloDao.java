package com.sourcecode.apparticulos.dao;

import com.sourcecode.apparticulos.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticuloDao extends JpaRepository<Articulo,Long> {
}
