package org.example.kutuphanesistemi.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class book_update {

    @FXML
    private ListView<String> book_list;
    @FXML
    private ListView<String> tur_list;
    @FXML
    private TextField book_search;
    @FXML
    private TextField kitap_adi;
    @FXML
    private TextField kitap_yazari;
    @FXML
    private TextField kitap_yayinevi;
    @FXML
    private TextField kitap_basim_tarihi;
    @FXML
    private TextField kitap_tur;

    private static int kitap_id;

    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        books=dbCrud.kitap_list();
        book_list.setItems(books);

        book_list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                String[] parts = newValue.split("  ");
                int kitap_tur_id;
                try {
                    kitap_tur_id = dbCrud.tur_getir(parts[5]);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                kitap_id = Integer.valueOf(parts[0]);
                kitap_adi.setText(parts[1]);
                kitap_yazari.setText(parts[2]);
                kitap_yayinevi.setText(parts[3]);
                kitap_basim_tarihi.setText(parts[4]);
                try {
                    kitap_tur.setText(dbCrud.get_kitap_ad(kitap_tur_id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    @FXML
    protected void kitap_ara() throws IOException {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        try {
            books = dbCrud.kitap_ara(book_search.getText());
            book_list.setItems(FXCollections.observableArrayList(books));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml", "Personel Sayfası");
    }

    @FXML
    protected void kitap_guncelle() throws SQLException, IOException {

        if(!kitap_adi.getText().isEmpty() && !kitap_yazari.getText().isEmpty() && !kitap_basim_tarihi.getText().isEmpty() && !kitap_tur.getText().isEmpty()) {

            int kitap_tur_id=Integer.parseInt(kitap_tur.getText());

            DBCrud dbCrud = new DBCrud();
            if(dbCrud.updateBook(kitap_id,kitap_adi.getText(),kitap_yazari.getText(),kitap_yayinevi.getText(),kitap_basim_tarihi.getText(),kitap_tur_id)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Kitap Başrıyla Güncellendi",ButtonType.OK);
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Kitap güncelleme başarısız",ButtonType.OK);
                alert.show();
            }
            HelloApplication.changeScene("book_update.fxml", "Kitap Güncelleme Sayfası");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Alanları boş bırakma",ButtonType.OK);
            alert.show();
        }


    }







}
