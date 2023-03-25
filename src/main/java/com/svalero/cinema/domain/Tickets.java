package com.svalero.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Tickets {
    @NonNull
    private String clientEmail;
    @NonNull
    private String clientName;
    @NonNull
    private int idMovie;
    private String movie;
    @NonNull
    private int hall;
    @NonNull
    private String session;
    @NonNull
    private List<Integer> mySeats;
    @NonNull
    private float price;
    private String qr;
}
