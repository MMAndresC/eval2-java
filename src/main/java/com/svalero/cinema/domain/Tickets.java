package com.svalero.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Tickets {
    private int ticketNum;
    @NonNull
    private String email;
    @NonNull
    private int idMovie;
    @NonNull
    private int hall;
    @NonNull
    private Date date;
    @NonNull
    private String hour;
    private List<Integer> mySeats;
    @NonNull
    private float price;
    private String qr;
    private String title;
    private String formattedDate;
}
