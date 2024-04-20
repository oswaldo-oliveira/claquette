package br.com.claquette.model.entity;

import br.com.claquette.model.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private LocalDate releaseDate;
    private Category category;
    private String whereToWatch;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
