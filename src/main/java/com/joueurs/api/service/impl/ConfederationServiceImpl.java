package com.joueurs.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.ConfederationDTO;
import com.joueurs.api.entity.Confederation;

import com.joueurs.api.exception.ConfederationNotFoundException;

import com.joueurs.api.repository.ConfederationRepository;
import com.joueurs.api.service.IConfederationService;

@Service
public class ConfederationServiceImpl implements IConfederationService {

	private ConfederationRepository confederationRepository;
	private ModelMapper mapper;
	
	public ConfederationServiceImpl(ConfederationRepository confederationRepository,ModelMapper mapper) {	
		this.confederationRepository = confederationRepository;
		this.mapper = mapper;
	}

	private ConfederationDTO mapEntityToDto(Confederation confederation) 
	{		
		return mapper.map(confederation, ConfederationDTO.class);
	}
	
	private Confederation mapDtoToEntity(ConfederationDTO confederationDto) 
	{		
		return mapper.map(confederationDto, Confederation.class);
	}
	
	@Override
	public List<ConfederationDTO> getAllConfederation() {
		
		List<Confederation> listeDesConfederations = confederationRepository.findAll();	
		List<ConfederationDTO> content = listeDesConfederations.stream().map(this::mapEntityToDto).collect(Collectors.toList());		
		return content;
	}

	@Override
	public ConfederationDTO findConfederationById(long confederationId) {
		Confederation confederation = confederationRepository
				.findById(confederationId)
				.orElseThrow(()-> new ConfederationNotFoundException("Confederation", "id", confederationId));		
				return mapEntityToDto(confederation);	
	}

}
