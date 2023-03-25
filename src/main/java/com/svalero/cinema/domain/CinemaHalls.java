package com.svalero.cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CinemaHalls {
    private int id;
    private int cols;
    private int lines;
}
