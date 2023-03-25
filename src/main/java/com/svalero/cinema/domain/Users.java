package com.svalero.cinema.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Users {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private int phone;
    private String image;
    private String role;

    public Users(String name, String image, int phone){
        this.name = name;
        this.image = image;
        this.phone = phone;
    }
}
