package com.cooweb.controllers;

import com.cooweb.dao.PersonaDao;
import com.cooweb.models.Persona;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private PersonaDao personaDao;

    @RequestMapping(value = "api/personas", method = RequestMethod.GET)
    public List<Persona> getPersonas() {
        return personaDao.getPersonas();
    }

    @RequestMapping(value = "api/personas", method = RequestMethod.POST)
    public void registrarPersona(@RequestBody Persona persona) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, persona.getPassword());
        persona.setPassword(hash);

        personaDao.registrar(persona);
    }

    @RequestMapping(value = "api/personas/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id) {
        personaDao.eliminar(id);
    }

    @RequestMapping(value = "persona/{id}")
    public Persona getPersona(@PathVariable Long id) {
        Persona persona = new Persona();
        persona.setId(id);
        persona.setNombre("Diego");
        persona.setApellido("Vargas");
        persona.setEmail("diegovargas@hotmail.com");
        persona.setTelefono("2615619965");
        return persona;
    }
}