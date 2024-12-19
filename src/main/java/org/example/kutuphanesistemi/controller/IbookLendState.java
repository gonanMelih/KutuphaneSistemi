package org.example.kutuphanesistemi.controller;

import java.sql.SQLException;

public interface IbookLendState {

    void odunc_ver(int id,String ogr_no) throws SQLException;
    void iade_al(int id) throws SQLException;

}
