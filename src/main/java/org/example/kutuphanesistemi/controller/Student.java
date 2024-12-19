package org.example.kutuphanesistemi.controller;

import javafx.scene.control.Alert;
import org.example.kutuphanesistemi.model.DBCrud;

import java.sql.SQLException;

public class Student implements Observer{


    @Override
    public void update(String message,int ogrenci_id) throws SQLException {
        DBCrud dbCrud = new DBCrud();
        dbCrud.duyuru_ekle(message,ogrenci_id);

    }


}
