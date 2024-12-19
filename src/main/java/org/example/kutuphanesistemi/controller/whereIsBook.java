package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class whereIsBook {


    @FXML
    ListView<String> book_list;

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Giriş Sayfası");
    }


    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        books=dbCrud.get_whereIsBook();
        book_list.setItems(books);

    }




}