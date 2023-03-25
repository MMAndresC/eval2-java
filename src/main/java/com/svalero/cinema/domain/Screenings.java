package com.svalero.cinema.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Screenings {
    @NonNull
    private int idHall;
    @NonNull
    private int idMovie;
    @NonNull
    private String titleMovie;
    @NonNull
    private String session;
    private List<Integer> takenSeats;
}
