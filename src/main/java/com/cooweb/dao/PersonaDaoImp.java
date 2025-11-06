package com.cooweb.dao;

import com.cooweb.models.Persona;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonaDaoImp implements PersonaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Persona> getPersonas() {
        String query = "FROM Persona";
        return entityManager.createQuery(query, Persona.class).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Persona persona = entityManager.find(Persona.class, id);
        entityManager.remove(persona);
    }

    @Override
    public void registrar(Persona persona) {
        entityManager.merge(persona);
    }

    @Override
    public boolean verificarCredenciales(Persona persona) {
        String query = "FROM Persona WHERE email = :email";
        List<Persona> lista = entityManager.createQuery(query, Persona.class)
                .setParameter("email", persona.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return false;
        }

        String passHasheada = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        return argon2.verify(passHasheada, persona.getPassword());
    }

}