package com.joueurs.api.utils;

import java.util.List;

import com.joueurs.api.dto.SelectionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationSelectionResponse {

	private List<SelectionDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
}
