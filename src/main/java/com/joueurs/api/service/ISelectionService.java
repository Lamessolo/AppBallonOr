package com.joueurs.api.service;
import java.util.List;
import java.util.Map;

import com.joueurs.api.dto.SelectionCreateDTO;
import com.joueurs.api.dto.SelectionDTO;
import com.joueurs.api.entity.Selection;
import com.joueurs.api.utils.PaginationSelectionResponse;

public interface ISelectionService {

	List<SelectionDTO> getAllSelection ();
	
	SelectionDTO findSelectionById(long selectionId);

	Map<String,Boolean> deleteSelection(long selectionId);

	SelectionDTO createSelection(SelectionCreateDTO selectionCreateDto);

	PaginationSelectionResponse findSelectionByConfederation(String confederation, int pageNo, int pageSize,
			String sortBy);
}
