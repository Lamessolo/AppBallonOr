package com.joueurs.api.service;
import java.util.Map;

import com.joueurs.api.dto.SelectionCreateDTO;
import com.joueurs.api.dto.SelectionDTO;
import com.joueurs.api.utils.PaginationSelectionResponse;

public interface ISelectionService {

	PaginationSelectionResponse getAllSelection (int pageNo,int pageSize,String sortBy);
	
	SelectionDTO findSelectionById(long selectionId);

	Map<String,Boolean> deleteSelection(long selectionId);

	SelectionDTO createSelection(SelectionCreateDTO selectionCreateDto);
}
