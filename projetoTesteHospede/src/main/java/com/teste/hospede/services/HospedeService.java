package com.teste.hospede.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.hospede.entity.Hospede;
import com.teste.hospede.repository.HospedeRepository;

@Service
public class HospedeService {

	private final HospedeRepository hospedeRepository;

	@Autowired
	public HospedeService(HospedeRepository hospedeRepository) {
		this.hospedeRepository = hospedeRepository;
	}

	public List<Hospede> getAllHospede() {
		return hospedeRepository.findAll();
	}

	public Hospede getHospedeById(Long id) {
		Optional<Hospede> Hospede = hospedeRepository.findById(id);
		return Hospede.orElse(null);
	}

	public Hospede salvarHospede(Hospede hospede) {
		return hospedeRepository.save(hospede);
	}

	public Hospede updateHospede(Long id, Hospede updatedHospede) {
		Optional<Hospede> existingHospede = hospedeRepository.findById(id);
		if (existingHospede.isPresent()) {
			updatedHospede.setId(id);
			return hospedeRepository.save(updatedHospede);
		}
		return null;
	}

	public boolean deleteHospede(Long id) {
		Optional<Hospede> existingHospede = hospedeRepository.findById(id);
		if (existingHospede.isPresent()) {
			hospedeRepository.deleteById(id);
			return true;
		}
		return false;
	}

}