package org.example.kutuphanesistemi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.kutuphanesistemi.HelloApplication;
import org.example.kutuphanesistemi.model.DBCrud;

import java.io.IOException;
import java.util.List;

public class personPage {

    @FXML
    private Button kitap_listele_btn;
    @FXML
    private Button kitap_ekle_btn;
    @FXML
    private Button kitap_sil_btn;
    @FXML
    private Button kitap_guncelle_btn;
    @FXML
    private Button tur_ekle_btn;
    @FXML
    private Button odunc_ver_btn;
    @FXML
    private Button kitap_iade_btn;
    @FXML
    private Button kitap_nerede_btn;
    @FXML
    private Button duyurular_btn;



    @FXML
    protected void book_liste() throws IOException {
        HelloApplication.changeScene("book_list.fxml","Kitap Listeşeme Sayfası");
    }
    @FXML
    protected void kitap_ekle() throws IOException {
        HelloApplication.changeScene("book_create.fxml","Kitap Ekleme Sayfası");
    }
    @FXML
    protected void tur_ekle() throws IOException {
        HelloApplication.changeScene("typeAdd.fxml","Tür Ekleme Sayfası");
    }

    @FXML
    protected void cikis() throws IOException {
        HelloApplication.changeScene("publicLogin.fxml","Giriş Sayfası");
    }

    @FXML
    protected void kitap_sil() throws IOException {
        HelloApplication.changeScene("book_remove.fxml","Kitap Silme Sayfası");
    }


    @FXML
    protected void kitap_guncelle() throws IOException {
        HelloApplication.changeScene("book_update.fxml","Kitap Güncelleme Sayfası");
    }

    @FXML
    protected void odunc_iade_btn() throws IOException {
        HelloApplication.changeScene("bookLend.fxml","Ödünç-İade Sayfası");
    }

    @FXML
    protected void kitap_nerede_btn() throws IOException {
        HelloApplication.changeScene("whereIsBook.fxml","Kitap Nerede Sayfası");
    }




}
