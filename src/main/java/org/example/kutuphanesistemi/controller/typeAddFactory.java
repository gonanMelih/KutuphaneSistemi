package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.ItypeAdd;
import org.example.kutuphanesistemi.model.typeAddModel;

import java.io.IOException;
import java.sql.SQLException;

public class typeAddFactory  {


    @FXML
    private TextField tur_adi;

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
    }


    private final ItypeAddFactory model = new typeFactory();
    @FXML
    protected void tur_ekle(){

        if(!tur_adi.getText().isEmpty()){
            String tur=tur_adi.getText();
            try{
                ItypeAdd typeAdd= model.tur_ekle();


                if(typeAdd.typeAdd(tur)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Tur eklendi", ButtonType.OK);
                    alert.show();

                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Tur Eklenemedi", ButtonType.OK);
                    alert.show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Tur Boş olmaz", ButtonType.OK);
            alert.show();
        }
    }


}
