package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class book_create {

    @FXML
    private TextField kitap_ad;
    @FXML
    private TextField kitap_yazar;
    @FXML
    private TextField kitap_yayinevi;
    @FXML
    private TextField kitap_basim_tarihi;
    @FXML
    private TextField kitap_tur;
    @FXML
    private ListView listview;

    @FXML
    public void initialize() throws SQLException {
            DBCrud dbCrud = new DBCrud();
            ObservableList<String> books = FXCollections.observableArrayList();

            books=dbCrud.tur_list();
            listview.setItems(books);
    }


    @FXML
    protected void kitap_ekle() throws IOException, SQLException {
        if(!kitap_ad.getText().isEmpty() && !kitap_yazar.getText().isEmpty() && !kitap_yayinevi.getText().isEmpty() && !kitap_basim_tarihi.toString().isEmpty() && !kitap_tur.getText().isEmpty()){

            DBCrud crud = new DBCrud();

            boolean sonuc=crud.book_add(kitap_ad.getText(),kitap_yazar.getText(),kitap_yayinevi.getText(),kitap_tur.getText(),kitap_basim_tarihi.getText());
            if (sonuc) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"kitap eklendi", ButtonType.OK);
                alert.show();
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"Boş Alan Bırakmayınız",ButtonType.OK);
            alert.show();

        }

    }


    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }




}
