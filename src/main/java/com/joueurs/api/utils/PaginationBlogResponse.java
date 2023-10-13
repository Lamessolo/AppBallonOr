package com.joueurs.api.utils;

import java.util.List;

import com.joueurs.api.dto.BlogDTO;
import com.joueurs.api.dto.ClubDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationBlogResponse {

	private List<BlogDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
}
