package com.sourcecode.apparticulos.dao;

import com.sourcecode.apparticulos.models.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFabricanteDao extends JpaRepository<Fabricante,Long> {
}
