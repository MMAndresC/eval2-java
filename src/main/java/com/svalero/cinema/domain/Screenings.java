package com.svalero.cinema.domain;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Screenings {
    @NonNull
    private int id;
    @NonNull
    private int id_hall;
    @NonNull
    private int id_movie;
    @NonNull
    private Date dateScreening;
    @NonNull
    private String hourScreening;
    private List<Seats> occupied_seats;
}
