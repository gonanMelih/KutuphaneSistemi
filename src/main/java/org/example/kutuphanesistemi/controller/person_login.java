package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.DBCrud;

public class person_login extends Login {


    @Override
    public Boolean login(String email, String password) {
        DBCrud crud = new DBCrud();
        return crud.loginPerson(email, password);
    }

}
