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
import com.joueurs.api.entity.Club;
import com.joueurs.api.entity.Joueur;
import com.joueurs.api.entity.Poste;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.repository.SelectionRepository;

import com.joueurs.api.service.ISelectionService;
import com.joueurs.api.utils.PaginationSelectionResponse;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SelectionServiceImpl implements ISelectionService{

	private final SelectionRepository selectionRepository;
	private final ModelMapper mapper;
	
     private SelectionDTO mapEntityToDTO(Selection selection) {
		 		
		return mapper.map(selection, SelectionDTO.class);			
	}
	 public  Selection mapDtoToEntity(SelectionCreateDTO SelectionCreateDto) {
	
		return mapper.map(SelectionCreateDto, Selection.class);			
	}
		
	@Override
	public PaginationSelectionResponse getAllSelection(int pageNo, int pageSize, String sortBy) {
		
		PageRequest pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Selection> listeDesSelections = selectionRepository.findAll(pageable);
		List<Selection> selections = listeDesSelections.getContent();
		List<SelectionDTO> content = selections.stream().map(this::mapEntityToDTO).collect(Collectors.toList());
		
		PaginationSelectionResponse pageSelectionsResponse = new PaginationSelectionResponse();
		pageSelectionsResponse.setContent(content);
		pageSelectionsResponse.setPageNo(listeDesSelections.getNumber());
		pageSelectionsResponse.setPageSize(listeDesSelections.getSize());
		pageSelectionsResponse.setTotalElements(listeDesSelections.getTotalElements());
		pageSelectionsResponse.setTotalPages(listeDesSelections.getTotalPages());
		pageSelectionsResponse.setLast(listeDesSelections.isLast());
		return pageSelectionsResponse;	
	}
	
	public SelectionDTO findSelectionById(long selectionId) {
		
		Optional<Selection> entitySelectionOptional = selectionRepository.findById(selectionId);
		if (entitySelectionOptional.isPresent())
		{  Selection selection = entitySelectionOptional.get();
		return mapEntityToDTO(selection);
		}else {
			throw new IllegalArgumentException("L'id de la selection n'est pas bonne");
		}	
				
	}
	@Override
	public Map<String, Boolean> deleteSelection(long selectionId) {
		Optional<Selection> entitySelectionOptional = selectionRepository.findById(selectionId);
		Selection entitySelection = entitySelectionOptional.get();	
			selectionRepository.delete(entitySelection);
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
	        return mapEntityToDTO(createdNewSelection);
	    } catch (DataIntegrityViolationException ex) {
	        // Gestion des erreurs de contrainte (par exemple, clé primaire en double)
	        throw new IllegalArgumentException("Une erreur de contrainte s'est produite lors de la création de la sélection.");
	    }
	    		
	}

}
