package com.joueurs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joueurs.api.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

}
