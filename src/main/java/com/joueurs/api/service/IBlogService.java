package com.joueurs.api.service;

import com.joueurs.api.dto.BlogCreateDTO;
import com.joueurs.api.dto.BlogDTO;
import com.joueurs.api.utils.PaginationBlogResponse;

public interface IBlogService {

	BlogDTO createBlogPost(BlogCreateDTO blogCreateDto);

	PaginationBlogResponse getAllblogs(int pageNo, int pageSize, String sortBy);
}
