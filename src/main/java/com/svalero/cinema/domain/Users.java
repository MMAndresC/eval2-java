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
    private String phone;
    private String image;
    private String role;

}
//TODO metodos de validacion de datos, ya lo hago en el front pero por si acaso mejor en los dos sitios