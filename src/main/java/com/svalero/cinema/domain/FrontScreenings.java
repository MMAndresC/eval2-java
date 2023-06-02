package com.svalero.cinema.domain;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;



@Setter
@Getter
public class FrontScreenings {
    private LocalDate date;
    private ArrayList<String> hallHour = new ArrayList<>();
    private ArrayList<Integer> id = new ArrayList<>();


    public void addHallHour(String item) {
        this.hallHour.add(item);
    }

    public void addId(int id){
        this.id.add(id);
    }

    public void clearHallHour() {
        this.hallHour.clear();
    }

    public void clearId() {
        this.id.clear();
    }


}
