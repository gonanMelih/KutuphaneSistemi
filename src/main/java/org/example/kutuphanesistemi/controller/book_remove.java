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
import java.util.ResourceBundle;

public class book_remove {


    @FXML
    private TextField kitap_adi;
    @FXML
    private TextField kitap_id;
    @FXML
    private ListView<String> book_list;

    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        books = dbCrud.kitap_list();
        book_list.setItems(books);

    }


    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml", "Personel Sayfası");
    }

    @FXML
    protected void kitap_ara() {

        DBCrud dbCrud = new DBCrud();
        ObservableList<String> books = FXCollections.observableArrayList();

        try {
            books = dbCrud.kitap_ara(kitap_adi.getText().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        book_list.setItems(books);
    }

    @FXML
    protected void kitap_sil() throws IOException, SQLException {

        DBCrud dbCrud = new DBCrud();
        if (dbCrud.kitap_sil(kitap_id.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Kitap başarıyla silindi.", ButtonType.OK);
            HelloApplication.changeScene("book_remove.fxml", "Kitap silme Sayfası");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"kitap id yanlış!!");
            alert.show();
        }



    }


}
