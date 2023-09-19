package com.joueurs.api.service;
import com.joueurs.api.utils.PaginationSelectionResponse;

public interface ISelectionService {

	PaginationSelectionResponse getAllSelection (int pageNo,int pageSize,String sortBy);
}
