package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public class duyuru implements Subject{

    private String duyuru;
    private int ogrenci_id;

    public void setDuyuru(String duyuru,int ogrenci_id) throws SQLException {
        this.duyuru = duyuru;
        this.ogrenci_id = ogrenci_id;
        notifyObservers();
    }


    @Override
    public void notifyObservers() throws SQLException {
        Student student=new Student();
        student.update(duyuru,ogrenci_id);
    }



}
