package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.ItypeAdd;
import org.example.kutuphanesistemi.model.typeAddModel;

import java.sql.SQLException;

public class typeFactory implements ItypeAddFactory{


    @Override
    public ItypeAdd tur_ekle() throws SQLException {
        return new typeAddModel();
    }
}
