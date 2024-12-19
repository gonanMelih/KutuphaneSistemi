package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public abstract class Login {


    public abstract Boolean login(String email,String password) throws SQLException;


}
