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

public class bookLend {

    IbookLendState iBookLendState;

    @FXML
    TextField ogr_no;
    @FXML
    TextField kitap_id;
    @FXML
    ListView<String> book_list_1;
    @FXML
    ListView<String> book_list_2;


    @FXML
    protected void geri_gel() throws IOException {
        HelloApplication.changeScene("personPage.fxml","Giriş Sayfası");
    }

    @FXML
    public void initialize() throws SQLException {
        DBCrud dbCrud = new DBCrud();

        ObservableList<String> books_shelf = FXCollections.observableArrayList();
        books_shelf=dbCrud.book_shelf();
        book_list_1.setItems(books_shelf);

        ObservableList<String> book_notShelf = FXCollections.observableArrayList();
        book_notShelf=dbCrud.book_notShelf();
        book_list_2.setItems(book_notShelf);


        book_list_1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String[] parts = newValue.split("  ");
                kitap_id.setText(parts[0]);

                Integer kitapID=Integer.parseInt(parts[0]);
                boolean rafta_mi= true;
                try {
                    rafta_mi = dbCrud.rafta_mi(kitapID);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                if(rafta_mi){
                    iBookLendState=new Lend();

                }else {
                    iBookLendState=new LendReturn();

                }
            }
        });

        book_list_2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String[] parts = newValue.split("  ");
                kitap_id.setText(parts[0]);
                int kitapID=Integer.parseInt(parts[0]);

                try {

                    ogr_no.setText(dbCrud.get_ogrenci_no((dbCrud.get_ogr_id2(kitapID))));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                boolean rafta_mi= false;
                try {
                    rafta_mi = dbCrud.rafta_mi(kitapID);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                if(rafta_mi){
                    iBookLendState=new Lend();

                }else {
                    iBookLendState=new LendReturn();

                }
            }
        });



    }


    public void odunc_ver_btn() throws SQLException, IOException {
        DBCrud dbCrud = new DBCrud();

        if(dbCrud.get_ogr_id(ogr_no.getText())!=-1) {
            if(!ogr_no.getText().isEmpty() && !kitap_id.getText().isEmpty()){
                Integer kitap_id_int = Integer.parseInt(kitap_id.getText());
                iBookLendState.odunc_ver(kitap_id_int,ogr_no.getText());
                duyuru yeniDuyuru = new duyuru();
                yeniDuyuru.setDuyuru("Kitap ödünç verildi: "+dbCrud.get_kitap_ad(kitap_id_int),dbCrud.get_ogr_id(ogr_no.getText()));
                HelloApplication.changeScene("bookLend.fxml", "Ödünç-İade Sayfası Sayfası");

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"Lütfen Alanları Boş Bırakmayınız",ButtonType.OK);
                alert.show();
            }
        }else{

            Alert alert=new Alert(Alert.AlertType.ERROR,"Öğrenci bulunamadı",ButtonType.OK);
            alert.show();
        }



    }


    public void iade_al_btn() throws SQLException, IOException {
        if(!ogr_no.getText().isEmpty() && !kitap_id.getText().isEmpty()){
            DBCrud dbCrud = new DBCrud();
            Integer kitap_id_int = Integer.parseInt(kitap_id.getText());
            iBookLendState.iade_al(kitap_id_int);
            duyuru yeniDuyuru = new duyuru();
            yeniDuyuru.setDuyuru("Kitap iade alındı: "+dbCrud.get_kitap_ad(kitap_id_int),dbCrud.get_ogr_id(ogr_no.getText()));
            HelloApplication.changeScene("bookLend.fxml", "Ödünç-İade Sayfası Sayfası");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Lütfen Alanları Boş Bırakmayınız", ButtonType.OK);
            alert.show();
        }

    }












}
