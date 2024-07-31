package com.joueurs.api.utils;

import java.util.List;


import com.joueurs.api.dto.ImageSourceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationImageSourceResponse {

	
	private List<ImageSourceDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
