package com.joueurs.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.CompteCreateDTO;
import com.joueurs.api.dto.CompteDTO;
import com.joueurs.api.entity.Compte;

import com.joueurs.api.repository.CompteRepository;
import com.joueurs.api.service.ICompteService;
import com.joueurs.api.utils.PaginationCompteResponse;



@Service
public class CompteServiceImpl implements ICompteService{

	private CompteRepository compteRepository;
	private  ModelMapper mapper ;
	
	public CompteServiceImpl(CompteRepository compteRepository,ModelMapper mapper) {
		this.compteRepository = compteRepository;
		this.mapper = mapper;
	}
	
	
	public  CompteDTO mapEntityToDto(Compte compte) {
		
		return mapper.map(compte, CompteDTO.class);	
	}
	
	public  Compte mapDtoToEntity(CompteCreateDTO compteCreateDto) {
		return mapper.map(compteCreateDto, Compte.class);
	}
	
	
	@Override
	public CompteDTO createCompte(CompteCreateDTO compteCreateDto) {
	
		 // Validation des données d'entrée
	    if (compteCreateDto == null || compteCreateDto.getName().isBlank()|| compteCreateDto.getName().isEmpty())
	    {
	        throw new IllegalArgumentException("Données d'entrée invalides.");
	    }
	    
	    // Vérifier si un compte avec le même nom et prénom existe déjà
        Optional<Compte> existingCompte = compteRepository.findByNameAndPrenom(compteCreateDto.getName(), compteCreateDto.getPrenom());
        if (existingCompte.isPresent()) {
            throw new IllegalStateException("Un compte avec le même nom et prénom existe déjà.");
        }
				
		// Je transforme le CompteDTO en une nouvelle entité compte
		    Compte createNewCompte = mapDtoToEntity(compteCreateDto);   
				
				// Je sauve une entité dans la base			 
				compteRepository.save(createNewCompte);
				
				return mapEntityToDto(createNewCompte);
		
	}

	@Override
	public PaginationCompteResponse getAllCompte(int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Compte> listeDesComptes = compteRepository.findAll(pageable);
		List<Compte> comptes = listeDesComptes.getContent();
		List<CompteDTO> content = comptes.stream().map(this::mapEntityToDto).collect(Collectors.toList());
		
		PaginationCompteResponse pageCompteResponse = new PaginationCompteResponse();
		pageCompteResponse.setContent(content);
		pageCompteResponse.setPageNo(listeDesComptes.getNumber());
		pageCompteResponse.setPageSize(listeDesComptes.getSize());
		pageCompteResponse.setTotalElements(listeDesComptes.getTotalElements());
		pageCompteResponse.setTotalPages(listeDesComptes.getTotalPages());
		pageCompteResponse.setLast(listeDesComptes.isLast());
		return pageCompteResponse;	
	}

}
