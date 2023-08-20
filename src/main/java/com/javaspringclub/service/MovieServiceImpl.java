package com.javaspringclub.service;

import com.javaspringclub.gs_ws.ServiceStatus;
import com.javaspringclub.entity.MovieType;
import com.javaspringclub.exceptions.MovieNotFoundException;
import com.javaspringclub.exceptions.MovieServiceException;
import com.javaspringclub.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieType getMovieById(long movieId) throws MovieNotFoundException{
        return movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("Movie with ID " + movieId + " not found"));
    }

    @Override
    public List<MovieType> getAllMovies() throws MovieServiceException{
    	List<MovieType> lst = new ArrayList<>();
        lst = movieRepository.findAll();
        if(lst.size()>0)
        return lst;
        throw new MovieServiceException("No movies in list");
    }

    @Override
    public MovieType addMovie(MovieType movie) {
        return movieRepository.save(movie);
    }

    @Override
    public ServiceStatus updateMovie(MovieType movie) throws MovieNotFoundException{
        if (movieRepository.existsById(movie.getMovieId())) {
            movieRepository.save(movie);
            //return new ServiceStatus("SUCCESS", "Movie updated successfully.");
            ServiceStatus st = new ServiceStatus();
            st.setMessage("Movie updated successfully.");
            st.setStatusCode("SUCCESS");
            return st;
        }
        throw new MovieNotFoundException("Movie with ID " + movie.getMovieId() + " not found");
    }

    @Override
    public ServiceStatus deleteMovie(long movieId) throws MovieNotFoundException{
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            //return new ServiceStatus("SUCCESS", "Movie deleted successfully.");
            ServiceStatus st = new ServiceStatus();
            st.setMessage("Movie deleted successfully.");
            st.setStatusCode("SUCCESS");
            return st;
        }
        throw new MovieNotFoundException("Movie with ID " + movieId + " not found");
    }
}