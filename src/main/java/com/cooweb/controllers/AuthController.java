package com.cooweb.controllers;

import com.cooweb.dao.PersonaDao;
import com.cooweb.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private PersonaDao personaDao;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Persona persona) {
        // Aquí estaba el error: "verFificarCredenciales"
        // Corrección: "verificarCredenciales"
        if (personaDao.verificarCredenciales(persona)) {
            return "OK";
        }
        return "Fail";
    }
}