package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class studentPage {

    @FXML
   private Label lblWelcome;

    @FXML
    private Button btnBook;
    @FXML
    private Button btnAnnounce;

    @FXML
    private Button btnSuggestion;

    @FXML
    private Button btnHasBook;

    @FXML
    private Button btnReadBook;

    @FXML
    private Button btnExit;


    public void exit() throws IOException {

        HelloApplication.changeScene("publicLogin.fxml","Ana Sayfa");
    }

    public void book_list_btn() throws IOException {
         HelloApplication.changeScene("studentBookList.fxml","Kitap Sayfası");
    }
    public void btnHasBook() throws IOException {
        HelloApplication.changeScene("studentHasBook.fxml","Alınan Kitaplar");
    }
    public void btnReadBook() throws IOException {
        HelloApplication.changeScene("studentReadWasBook.fxml","Önceki Aldığım kitaplar Sayfası");
    }

    public void announce_btn() throws IOException {
        HelloApplication.changeScene("studentAnnounce.fxml","Önceki Aldığım kitaplar Sayfası");
    }

    public void initialize() throws SQLException {
        DBCrud crud = new DBCrud();

        lblWelcome.setText(crud.get_ogr_name());
    }







}
