package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class studentSignup {

    @FXML
    private TextField ad;
    @FXML
    private TextField soyad;
    @FXML
    private TextField eposta;
    @FXML
    private TextField sifre;
    @FXML
    private TextField ogr_no;




    @FXML
    protected void uye_ol() throws SQLException, IOException {

        if(!ad.getText().isEmpty() && !soyad.getText().isEmpty() && !eposta.getText().isEmpty() && !sifre.getText().isEmpty() && !ogr_no.getText().isEmpty()){

            DBCrud crud = new DBCrud();

            boolean sonuc=crud.signUpStudent(ad.getText(),soyad.getText(),eposta.getText(),sifre.getText(),ogr_no.getText());
            if (sonuc) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Kullanıcı eklendi", ButtonType.OK);
                alert.show();
                HelloApplication.changeScene("publicLogin.fxml","Giriş sayfası");
            }


        }else{
            Alert alert=new Alert(Alert.AlertType.WARNING,"Boş Alan Bırakmayınız",ButtonType.OK);
            alert.show();

        }

    }

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("studentLogin.fxml","Giriş sayfası");
    }




}
