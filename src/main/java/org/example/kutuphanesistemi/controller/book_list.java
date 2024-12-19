package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBConnector;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class book_list {

    @FXML
    private ListView<String> listview;



    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        books=dbCrud.kitap_list();
        listview.setItems(books);
    }

    @FXML
    protected void geri_gel() throws IOException, InterruptedException {
        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }



}
