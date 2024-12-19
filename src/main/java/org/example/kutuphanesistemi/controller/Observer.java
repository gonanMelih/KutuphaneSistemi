package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public interface Observer {

    void update(String message,int ogrenci_id) throws SQLException;

}
