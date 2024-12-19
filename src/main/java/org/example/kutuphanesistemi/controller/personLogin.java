
package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;

public class personLogin {


    @FXML
    private TextField eposta;
    @FXML
    private TextField sifre;
    @FXML
    private Button btn;




    @FXML
    protected void loginPerson() throws IOException {


        person_login login = new person_login();

        if(login.login(eposta.getText(), sifre.getText())) {
            HelloApplication.changeScene("personPage.fxml","Personel Sayfası");
        }else{
            Alert alert= new Alert(Alert.AlertType.INFORMATION,"Geçersiz Kullanıcı", ButtonType.OK);
            alert.show();
        }
    }

    
    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml","Giriş Sayfası");
    }


}