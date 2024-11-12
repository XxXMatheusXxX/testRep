package com.teste.hospede.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.hospede.entity.Hospede;
import com.teste.hospede.services.HospedeService;

@RestController
@RequestMapping("/api/hospede")
public class HospedeController {

	@Autowired
	private HospedeService hospedeService;
	
	@PostMapping
	public ResponseEntity<Hospede> salvarHospede(@RequestBody Hospede hospede) {
		Hospede novoHospede = hospedeService.salvarHospede(hospede);
		return new ResponseEntity<>(novoHospede, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Hospede>> listarTodos() {
		List<Hospede> hospedes = hospedeService.getAllHospede();
		return new ResponseEntity<>(hospedes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> buscarPorId(@PathVariable Long id) {
		Hospede hospede = hospedeService.getHospedeById(id);
		if (hospede != null) {
			return new ResponseEntity<>(hospede, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hospede> atualizarHospede(@PathVariable Long id,
			@RequestBody Hospede hospede) {
		
		Hospede existingHospede = hospedeService.getHospedeById(id);
		if (existingHospede == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hospede.setId(id);
		Hospede hospedeAtualizado = hospedeService.updateHospede(id, hospede);
		return new ResponseEntity<>(hospedeAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarHospede (@PathVariable Long id) {
		Hospede existingHospede = hospedeService.getHospedeById(id);
		if (existingHospede == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hospedeService.deleteHospede(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
