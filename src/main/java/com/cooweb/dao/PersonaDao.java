package com.cooweb.dao;

import com.cooweb.models.Persona;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonaDao {

    List<Persona> getPersonas();

    void eliminar(Long id);

    void registrar(Persona persona);

    boolean verificarCredenciales(Persona persona);

}