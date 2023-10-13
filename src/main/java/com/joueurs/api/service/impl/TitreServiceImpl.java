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

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TitreServiceImpl implements ITitreService {

	
	private final TitreRepository titreRepository;
	private final ModelMapper mapper;
	
    private TitreDTO mapEntityToDTO(Titre titre) {
		
		return mapper.map(titre, TitreDTO.class);			
	}
	
	public Titre mapDtoToEntity(TitreCreateDTO titreCreateDto) {
	   
		return mapper.map(titreCreateDto, Titre.class);		
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
		
		return  mapEntityToDTO(NewTitre);
	}

	@Override
	public List<TitreDTO> getAllTitre() {
		
		List<Titre> titresList = titreRepository.findAll();
		
		// Je veux une list de TitreDTO
		List<TitreDTO> contentTitreDTO = titresList.stream()
												   .map(this::mapEntityToDTO)
												   .collect(Collectors.toList());
		return contentTitreDTO;
	}

	@Override
	public TitreDTO findTitreById(Integer titreId) {
		
		Optional<Titre> entityTitreOptional = titreRepository.findById(titreId);
		if (entityTitreOptional.isPresent())
		{  Titre titre = entityTitreOptional.get();
		return mapEntityToDTO(titre);
		}else {
			throw new IllegalArgumentException("L'id du titre est incorrect");
		}	
	}

}
