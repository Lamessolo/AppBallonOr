package com.joueurs.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.SelectionCreateDTO;
import com.joueurs.api.dto.SelectionDTO;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.exception.SelectionNotFoundException;
import com.joueurs.api.repository.SelectionRepository;
import com.joueurs.api.service.ISelectionService;
import com.joueurs.api.utils.PaginationSelectionResponse;


@Service
public class SelectionServiceImpl implements ISelectionService{

	private SelectionRepository selectionRepository;
	private ModelMapper mapper;
	
	public SelectionServiceImpl(SelectionRepository selectionRepository,ModelMapper mapper) {	
		this.selectionRepository = selectionRepository;
		this.mapper = mapper;
	}

	private SelectionDTO mapEntityToDto(Selection selection) 
	{		
		return mapper.map(selection, SelectionDTO.class);
	}
	
	private Selection mapDtoToEntity(SelectionDTO selectionDto) 
	{		
		return mapper.map(selectionDto, Selection.class);
	}

	@Override
	public List<SelectionDTO> getAllSelection() {
				
		List<Selection> listeDesSelections = selectionRepository.findAll();	
		List<SelectionDTO> content = listeDesSelections.stream().map(this::mapEntityToDto).collect(Collectors.toList());		
		return content;	
	}
	
	public SelectionDTO findSelectionById(long selectionId) {
		
		Optional<Selection> entitySelectionOptional = selectionRepository.findById(selectionId);
		if (entitySelectionOptional.isPresent())
		{  Selection selection = entitySelectionOptional.get();
		return mapEntityToDto(selection);
		}else {
			throw new IllegalArgumentException("L'id de la selection est incorrect");
		}	
				
	}
	@Override
	public Map<String, Boolean> deleteSelection(long selectionId) {
		Selection selection = selectionRepository
				.findById(selectionId)
				.orElseThrow(() -> new SelectionNotFoundException("Selection", "id", selectionId));
	
			selectionRepository.delete(selection);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
	
	@Override
	public SelectionDTO createSelection(SelectionCreateDTO selectionCreateDto) {	   	
		 // Validation des données d'entrée
	    if (selectionCreateDto == null || selectionCreateDto.getName() == null || selectionCreateDto.getConfederation() == null)
	    {
	      throw new IllegalArgumentException("Données d'entrée invalides.");
	    }
	    try {
	        // Création de la nouvelle entité
	        Selection createdNewSelection = new Selection();
	        createdNewSelection.setConfederation(selectionCreateDto.getConfederation());
	        createdNewSelection.setName(selectionCreateDto.getName());
	        
	        // Sauvegarde dans la base de données (dans une transaction)
	        createdNewSelection = selectionRepository.save(createdNewSelection);
	        
	        // Conversion en DTO et retour
	        return mapEntityToDto(createdNewSelection);
	    } catch (DataIntegrityViolationException ex) {
	        // Gestion des erreurs de contrainte (par exemple, clé primaire en double)
	        throw new IllegalArgumentException("Une erreur de contrainte s'est produite lors de la création de la sélection.");
	    }
	    		
	}
	
	
	@Override
	public SelectionDTO updateSelection(long selectionId, SelectionCreateDTO selectionCreateDto) {
		 Selection selection = selectionRepository
		    		.findById(selectionId)
		    		.orElseThrow(()-> new SelectionNotFoundException("Selection", "id", selectionId)); 
		 selection.setConfederation(selectionCreateDto.getConfederation());
		 selection.setName(selectionCreateDto.getName());
		 selectionRepository.save(selection);
		 return mapEntityToDto(selection);	
	}

}
