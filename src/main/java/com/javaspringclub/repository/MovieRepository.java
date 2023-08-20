package com.javaspringclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaspringclub.entity.MovieType;

@Repository
public interface MovieRepository extends JpaRepository<MovieType, Long> {
}
