package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.sql.SQLException;

public class studentLogin {


    @FXML
    private TextField eposta;
    @FXML
    private TextField sifre;
    @FXML
    private Button btn;
    @FXML
    Button uyeOl_btn;


    @FXML
    protected void loginStudent() throws IOException, SQLException {

        Login studentLogin = new student_login();

        if(studentLogin.login(eposta.getText(),sifre.getText())) {
            HelloApplication.changeScene("studentPage.fxml","Ogrenci Sayfası");
        }else{
            Alert alert= new Alert(Alert.AlertType.INFORMATION,"Geçersiz Kullanıcı", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    protected void uyeOl() throws IOException {
        HelloApplication.changeScene("studentSignup.fxml","Üye ol");
    }

    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml","Giriş sayfası");
    }


}
