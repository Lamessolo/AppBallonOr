package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog,Long> {

}
