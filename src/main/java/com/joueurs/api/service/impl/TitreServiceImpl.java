package com.joueurs.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.joueurs.api.dto.TitreCreateDTO;
import com.joueurs.api.dto.TitreDTO;

import com.joueurs.api.entity.Titre;

import com.joueurs.api.repository.TitreRepository;
import com.joueurs.api.service.ITitreService;

@Service
public class TitreServiceImpl implements ITitreService {
	private TitreRepository titreRepository;
	private ModelMapper mapper;
	
	public TitreServiceImpl(TitreRepository titreRepository,ModelMapper mapper) {	
		this.titreRepository = titreRepository;
		this.mapper = mapper;
	}

	private TitreDTO mapEntityToDto(Titre titre) 
	{		
		return mapper.map(titre, TitreDTO.class);
	}
	
	private Titre mapDtoToEntity(TitreDTO titreDto) 
	{		
		return mapper.map(titreDto, Titre.class);
	}
	
	@Override
	public TitreDTO createTitre(TitreCreateDTO titreCreateDto) {
		Titre NewTitre = new Titre();
		NewTitre.setAnneeTitre(titreCreateDto.getAnneeTitre());
		NewTitre.setCompetition(titreCreateDto.getCompetition());
		NewTitre.setConfederation(titreCreateDto.getConfederation());
		NewTitre.setDescription(titreCreateDto.getDescription());
		NewTitre.setName(titreCreateDto.getName());
		NewTitre.setType(titreCreateDto.getType());
		titreRepository.save(NewTitre);
		
		return  mapEntityToDto(NewTitre);
	}

	@Override
	public List<TitreDTO> getAllTitre() {
		
		List<Titre> titresList = titreRepository.findAll();
		
		// Je veux une list de TitreDTO
		List<TitreDTO> contentTitreDTO = titresList.stream()
												   .map(this::mapEntityToDto)
												   .collect(Collectors.toList());
		return contentTitreDTO;
	}

	@Override
	public TitreDTO findTitreById(long titreId) {
		
		Optional<Titre> entityTitreOptional = titreRepository.findById(titreId);
		if (entityTitreOptional.isPresent())
		{  Titre titre = entityTitreOptional.get();
		return mapEntityToDto(titre);
		}else {
			throw new IllegalArgumentException("L'id du titre est incorrect");
		}	
	}

}
