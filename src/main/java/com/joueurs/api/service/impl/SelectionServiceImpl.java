package com.joueurs.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.joueurs.api.dto.SelectionDTO;
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
	public  Selection mapDtoToEntity(SelectionDTO SelectionDto) {
	
		return mapper.map(SelectionDto, Selection.class);			
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
	
	

}
