package br.com.claquette.services;

import br.com.claquette.model.dto.request.MovieRequestDto;
import br.com.claquette.model.dto.response.MovieResponseDto;
import br.com.claquette.model.entity.Movie;
import br.com.claquette.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieResponseDto createMovie(MovieRequestDto movie) {
        var entity = modelMapper.map(movie, Movie.class);
        entity.setCreatedAt(LocalDateTime.now());
        var response = movieRepository.save(entity);

        return modelMapper.map(response, MovieResponseDto.class);
    }

    public Page<MovieResponseDto> getMovies(Pageable page) {
        return movieRepository.findAll(page).map(movie -> modelMapper.map(movie, MovieResponseDto.class));
    }

    public MovieResponseDto getMovieById(UUID id) {
        var movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return modelMapper.map(movie, MovieResponseDto.class);
    }

    public void updateMovie(UUID id, MovieRequestDto movie) {
        var entity = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setCreatedAt(LocalDateTime.now());
        modelMapper.map(movie, entity);
        movieRepository.save(entity);
    }

    public void deleteMovie(UUID id) {
        var entity = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        movieRepository.delete(entity);
    }
}
