package br.com.claquette.controllers;

import br.com.claquette.model.dto.request.MovieRequestDto;
import br.com.claquette.model.dto.request.MovieUpdateRequestDto;
import br.com.claquette.model.dto.response.MovieResponseDto;
import br.com.claquette.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody @Valid MovieRequestDto movie, UriComponentsBuilder builder) {
        var response = movieService.createMovie(movie);
        var uri = builder.path("/movies/{id}").buildAndExpand(response.getId()).toUri();

        return  ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponseDto>> getMovies(Pageable page) {
        var response = movieService.getMovies(page);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable UUID id) {
        var response = movieService.getMovieById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}/recommendation")
    public ResponseEntity<String> getRecommendationByMovieId(@PathVariable UUID movieId) {
        var response = movieService.getRecommendationByMovieId(movieId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable UUID id, @RequestBody MovieUpdateRequestDto movie) {
        movieService.updateMovie(id, movie);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }
}
