package com.javaspringclub.service;

import com.javaspringclub.gs_ws.ServiceStatus;
import com.javaspringclub.exceptions.MovieNotFoundException;
import com.javaspringclub.exceptions.MovieServiceException;
import com.javaspringclub.entity.MovieType;

import java.util.List;

public interface MovieService {
    MovieType getMovieById(long movieId) throws MovieNotFoundException;
    List<MovieType> getAllMovies() throws MovieServiceException;
    MovieType addMovie(MovieType movie);
    ServiceStatus updateMovie(MovieType movie) throws MovieNotFoundException;
    ServiceStatus deleteMovie(long movieId) throws MovieNotFoundException;
}
