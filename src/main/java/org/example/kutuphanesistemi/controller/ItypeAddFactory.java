package org.example.kutuphanesistemi.controller;

import org.example.kutuphanesistemi.model.ItypeAdd;

import java.sql.SQLException;

public interface ItypeAddFactory {
    ItypeAdd tur_ekle() throws SQLException;
}
