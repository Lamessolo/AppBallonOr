package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joueurs.api.entity.ImageSource;


@Repository
public interface ImageSourceRepository extends JpaRepository<ImageSource, Long> {

}
