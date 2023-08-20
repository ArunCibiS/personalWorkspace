package com.javaspringclub.controller;
import com.javaspringclub.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.javaspringclub.gs_ws.ServiceStatus;
import com.javaspringclub.entity.MovieType;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieRestController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieType> getMovieById(@PathVariable long movieId) {
        MovieType movie = movieService.getMovieById(movieId);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<MovieType>> getAllMovies() {
        List<MovieType> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieType> addMovie(@RequestBody MovieType movie) {
        MovieType addedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<ServiceStatus> updateMovie(@PathVariable long movieId, @RequestBody MovieType movie) {
        movie.setMovieId(movieId);
        ServiceStatus status = movieService.updateMovie(movie);
        if ("SUCCESS".equals(status.getStatusCode())) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<ServiceStatus> deleteMovie(@PathVariable long movieId) {
        ServiceStatus status = movieService.deleteMovie(movieId);
        if ("SUCCESS".equals(status.getStatusCode())) {
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
    }
}