package com.svalero.cinema.domain;

import lombok.*;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Movies {
    private int movies_id;
    @NonNull
    private String title;
    private String poster;
    private String synopsis;
    private String rated;
    private int duration;
    private String genre;
    private String director;
    private String actors;
    @NonNull
    private LocalDate iniDate;
    @NonNull
    private Boolean isPremiere;

}
