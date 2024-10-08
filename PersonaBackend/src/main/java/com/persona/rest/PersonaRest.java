package com.persona.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persona.model.Persona;
import com.persona.service.PersonaService;

@RestController
@RequestMapping("/persona/")
public class PersonaRest {
	

	@Autowired
	private PersonaService personaService;

	@GetMapping
	private ResponseEntity<List<Persona>> getAllPersonas() {
		return ResponseEntity.ok(personaService.findAll());
	}

	@PostMapping
	private ResponseEntity<Persona> savePersona(@RequestBody Persona persona) {
		try {
			Persona personaGuardada = personaService.save(persona);
			return ResponseEntity.created(new URI("/persona/" + personaGuardada.getId())).body(personaGuardada);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping (value="delete/{id}")
	private ResponseEntity<Boolean> deletePersona(@PathVariable ("id") Integer idPersona){
		personaService.deleteById(idPersona);
		return ResponseEntity.ok(!(personaService.findById(idPersona)!=null));
	}

}
