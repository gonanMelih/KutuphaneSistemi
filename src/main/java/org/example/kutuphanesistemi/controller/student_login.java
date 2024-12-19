package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class student_login extends Login {

    public static int ogr_id;

    @Override
    public Boolean login(String email, String password) throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ogr_id= dbCrud.get_ogr_id3(email,password);
        return dbCrud.loginStudent(email, password);
    }

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml","Giriş sayfası");
    }
}
