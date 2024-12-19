package org.example.kutuphanesistemi.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.kutuphanesistemi.controller.student_login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBCrud {


    public boolean loginPerson(String eposta, String sifre) {
        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "select * from tbl_personel where personel_eposta=? and personel_sifre=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginStudent(String eposta, String sifre) {
        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "select * from tbl_ogrenci where ogrenci_eposta=? and ogrenci_sifre=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, eposta);
            ps.setString(2, sifre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else
                return false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }


    }

    public boolean signUpStudent(String ad, String soyad, String eposta, String sifre, String ogrNo) {

        try {
            Connection connection = DBConnector.getInstance().getConnection();
            String query = "INSERT into tbl_ogrenci values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setString(3, eposta);
            ps.setString(4, sifre);
            ps.setString(5, ogrNo);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<String> kitap_list() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi,kitap_rafta_mi from tbl_kitap,tbl_tur where tbl_kitap.kitap_tur_id=tbl_tur.tur_id";

        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");
                boolean kitap_rafta_mi = resultSet.getBoolean("kitap_rafta_mi");

                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_adi + "  " + kitap_rafta_mi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public ObservableList<String> tur_list() throws SQLException {
        String selectQuery = "SELECT * from tbl_tur";

        ObservableList<String> tur = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                int tur_id = resultSet.getInt("tur_id");
                String tur_adi = resultSet.getString("tur_adi");


                // Veriyi listeye ekle
                tur.add(tur_id + " - " + tur_adi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tur;

    }

    public boolean book_add(String kitap_ad, String kitap_yazar, String kitap_yayinevi, String kitap_tur, String kitap_basim_tarihi) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String query = "insert into tbl_kitap (kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,kitap_tur_id) values (?,?,?,?,?)";

        try {
            int kitapTur = Integer.parseInt(kitap_tur);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, kitap_ad);
            ps.setString(2, kitap_yazar);
            ps.setString(3, kitap_yayinevi);
            ps.setString(4, kitap_basim_tarihi);
            ps.setInt(5, kitapTur);

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Geçerli bir tur id'si giriniz", ButtonType.OK);
            alert.show();
            return false;
        }


    }

    public ObservableList<String> kitap_ara(String kitap_ad) throws SQLException {

        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi,kitap_rafta_mi from tbl_kitap,tbl_tur where tbl_kitap.kitap_tur_id=tbl_tur.tur_id and kitap_adi like (?)";

        ObservableList<String> books = FXCollections.observableArrayList();


        try {
            Connection connection = DBConnector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, "%" + kitap_ad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");
                boolean kitap_rafta_mi = resultSet.getBoolean("kitap_rafta_mi");

                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_adi + "  " + kitap_rafta_mi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public boolean kitap_sil(String kitap_id) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String query = "delete from tbl_kitap where kitap_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, kitap_id);
        if (preparedStatement.executeUpdate() > 0) {
            return true;
        }
        return false;



    }

    public boolean updateBook(int kitap_id,String kitap_adi,String kitap_yazari,String kitap_yayinevi,String kitap_basim_yili, int kitap_tur_id) throws SQLException {

        Connection connection = DBConnector.getInstance().getConnection();
        String query = "update tbl_kitap set kitap_adi=(?),kitap_yazari=(?),kitap_yayinevi=(?),kitap_basim_tarihi=(?),kitap_tur_id=? where kitap_id=?";
        PreparedStatement ps = connection.prepareStatement(query);

        try {
            ps.setString(1, kitap_adi);
            ps.setString(2, kitap_yazari);
            ps.setString(3, kitap_yayinevi);
            ps.setString(4, kitap_basim_yili);
            ps.setInt(5, kitap_tur_id);
            ps.setInt(6, kitap_id);

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Geçerli bir tur id'si giriniz", ButtonType.OK);
            alert.show();
            return false;
        }


    }

    public int tur_getir(String tur_adi) throws SQLException {
        String query="select tur_id from tbl_tur where tur_adi=?";
        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, tur_adi);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("tur_id");
        }
        return 0;

    }

    public ObservableList<String> book_shelf() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi from tbl_kitap,tbl_tur where tbl_kitap.kitap_tur_id=tbl_tur.tur_id and kitap_rafta_mi=1";

        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");




                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_adi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public ObservableList<String> book_notShelf() throws SQLException {
        String selectQuery = "SELECT kitap_id,kitap_adi,kitap_yazari,kitap_yayinevi,kitap_basim_tarihi,tur_adi from tbl_kitap,tbl_tur where tbl_kitap.kitap_tur_id=tbl_tur.tur_id and kitap_rafta_mi=0";

        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                int kitap_id = resultSet.getInt("kitap_id");
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");


                // Veriyi listeye ekle
                books.add(kitap_id + "  " + kitap_adi + "  " + kitap_yazari + "  " + kitap_yayinevi + "  " + kitap_basim_tarihi + "  " + tur_adi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public boolean odunc_ver(int kitap_id,int ogr_id) throws SQLException {


            LocalDateTime now=LocalDateTime.now();
            Connection connection = DBConnector.getInstance().getConnection();
            String query1 = "update  tbl_kitap set kitap_rafta_mi=0 where kitap_id=?";
            String query2="insert into tbl_odunc (ogrenci_id,kitap_id,odunc_tarihi) values (?,?,?) ";
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setInt(1, kitap_id);
            PreparedStatement ps2=connection.prepareStatement(query2);
            ps2.setInt(1, ogr_id);
            ps2.setInt(2, kitap_id);
            ps2.setString(3,now.toString());

            if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0 ) {
                return true;
            }


        return false;

    }

    public boolean iade_al(int kitap_id) throws SQLException {

        LocalDateTime now=LocalDateTime.now();
        Connection connection = DBConnector.getInstance().getConnection();
        String query1 = "update  tbl_kitap set kitap_rafta_mi=1 where kitap_id=?";
        String query2="update  tbl_odunc set iade_tarihi=? where kitap_id=? ";

        PreparedStatement ps2=connection.prepareStatement(query2);
        ps2.setString(1, now.toString());
        ps2.setInt(2, kitap_id);
        PreparedStatement ps = connection.prepareStatement(query1);
        ps.setInt(1, kitap_id);




        if (ps.executeUpdate() > 0 && ps2.executeUpdate() > 0 ) {
            return true;
        }
        return false;
    }

    public boolean rafta_mi(int kitap_id) throws SQLException {
        String query = "SELECT kitap_rafta_mi FROM tbl_kitap WHERE kitap_id = ?";

       Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query) ;

            ps.setInt(1, kitap_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("kitap_rafta_mi");
            } else {
                throw new SQLException("Kitap bulunamadı: " + kitap_id);
            }

    }

    public int get_ogr_id(String ogr_no) throws SQLException {
        String query = "select ogrenci_id from tbl_ogrenci where ogrenci_no=?";
        Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, ogr_no);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // Kayıt bulunduysa
                    return rs.getInt("ogrenci_id");  // ogrenci_id'yi döner
                } else {
                    return -1;  // Kayıt bulunamadıysa -1 döner
                }
            }

    }

    public int get_ogr_id2(int kitap_id) throws SQLException {
        String query = "select ogrenci_id from tbl_odunc where kitap_id=?";
        Connection connection = DBConnector.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, kitap_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // ResultSet'te veri varsa
                    return rs.getInt("ogrenci_id");
                } else {
                    throw new SQLException("Kayıt bulunamadı: Kitap ID " + kitap_id);
                }
            }

    }

    public String get_ogrenci_no(int ogr_id) throws SQLException {
        Connection connection = DBConnector.getInstance().getConnection();
        String query="select ogrenci_no from tbl_ogrenci where ogrenci_id=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, ogr_id);

        try(ResultSet rs=ps.executeQuery()){
            if (rs.next()) {
                return rs.getString("ogrenci_no");
            }
            return null;
        }

    }

    public ObservableList<String> get_whereIsBook() throws SQLException {
        Connection connection=DBConnector.getInstance().getConnection();
        String query="select tbl_kitap.kitap_adi,tbl_kitap.kitap_yazari,tbl_ogrenci.ogrenci_adi,tbl_ogrenci.ogrenci_soyadi,tbl_ogrenci.ogrenci_no,odunc_tarihi from tbl_ogrenci inner join tbl_odunc on tbl_ogrenci.ogrenci_id=tbl_odunc.ogrenci_id inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where tbl_odunc.iade_tarihi is null";

        ObservableList<String> books=FXCollections.observableArrayList();
        PreparedStatement pd=connection.prepareStatement(query);
        ResultSet rs=pd.executeQuery();
        while(rs.next()){

            String kitap_adi=rs.getString("kitap_adi");
            String yazari=rs.getString("kitap_yazari");
            String ogrenci_adi=rs.getString("ogrenci_adi");
            String ogrenci_soyadi=rs.getString("ogrenci_soyadi");
            String ogrenci_no=rs.getString("ogrenci_no");
            String odunc_tarihi=rs.getString("odunc_tarihi");
            books.add("Kitap adı: "+kitap_adi + "  Kitap yazarı: " + yazari + "  Ögrenci adı: " + ogrenci_adi +" "+ogrenci_soyadi +"  Öğrenci no: " + ogrenci_no + "  Ödünç tarihi: " + odunc_tarihi );
        }
        return books;
    }

    public ObservableList<String> kitap_list2() throws SQLException {
        String selectQuery = "SELECT " +
                "    kitap_adi," +
                "    kitap_yazari," +
                "    kitap_yayinevi," +
                "    kitap_basim_tarihi," +
                "    tbl_tur.tur_adi," +
                "    CASE kitap_rafta_mi" +
                "        WHEN 1  THEN 'Rafta'" +
                "        ELSE 'Rafta değil'" +
                "end as 'kitap_durum' "+
                "FROM " +
                "    tbl_kitap " +
                "INNER JOIN " +
                "    tbl_tur " +
                "ON " +
                "    tbl_kitap.kitap_tur_id = tbl_tur.tur_id;";

        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {

            while (resultSet.next()) {
                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_yazari = resultSet.getString("kitap_yazari");
                String kitap_yayinevi = resultSet.getString("kitap_yayinevi");
                String kitap_basim_tarihi = resultSet.getString("kitap_basim_tarihi");
                String tur_adi = resultSet.getString("tur_adi");
                String kitap_rafta_mi = resultSet.getString("kitap_durum");

                // Veriyi listeye ekle
                books.add("Kitap Adi: " + kitap_adi + "  Kitap yazarı: " + kitap_yazari + "  Kitap yayınevi: " + kitap_yayinevi + "  Basım Tarihi:" + kitap_basim_tarihi + "  Litap Türü:" + tur_adi + "  Rafta Mı: " + kitap_rafta_mi);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public ObservableList<String> listBooksStudent() throws SQLException {
        String selectQuery = "SELECT kitap_adi ,odunc_tarihi, DATEADD(DAY,15,odunc_tarihi) as 'iade' FROM tbl_odunc inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where ogrenci_id=? and iade_tarihi is null";
        ObservableList<String> books = FXCollections.observableArrayList();
        Connection connection = DBConnector.getInstance().getConnection();



        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1,student_login.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_odunc_tarih = resultSet.getString("odunc_tarihi");
                String kitap_teslim_tarih = resultSet.getString("iade");


                // Veriyi listeye ekle
                books.add("Kitap Adı: "+kitap_adi + "     " + "Kitap Alınan Tarih: "+kitap_odunc_tarih + "      "+"Kitap İade Edilmesi Gereken Tarih:  "+kitap_teslim_tarih );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int get_ogr_id3(String email,String pass) throws SQLException {
        Connection connection= DBConnector.getInstance().getConnection();
        String query="Select ogrenci_id from tbl_ogrenci where ogrenci_eposta=? and ogrenci_sifre=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,pass);

        ResultSet rs= preparedStatement.executeQuery();
        if(rs.next()){
            return rs.getInt("ogrenci_id");
        }
        else{
            return 0;
        }
    }

    public ObservableList<String> listWasReadBooksStudent() throws SQLException {
        String selectQuery = "SELECT kitap_adi ,odunc_tarihi, iade_tarihi FROM tbl_odunc inner join tbl_kitap on tbl_odunc.kitap_id=tbl_kitap.kitap_id where ogrenci_id=? and iade_tarihi is not null";
        ObservableList<String> books = FXCollections.observableArrayList();
        Connection connection = DBConnector.getInstance().getConnection();


        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1,student_login.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {

                String kitap_adi = resultSet.getString("kitap_adi");
                String kitap_odunc_tarih = resultSet.getString("odunc_tarihi");
                String kitap_teslim_tarih = resultSet.getString("iade_tarihi");


                // Veriyi listeye ekle
                books.add("Kitap Adı: "+kitap_adi + "     " + "Kitap Alınan Tarih: "+kitap_odunc_tarih + "      "+"Kitap İade Edilen Tarih:  "+kitap_teslim_tarih );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public String get_kitap_ad(int kitap_id) throws SQLException {
        Connection connection= DBConnector.getInstance().getConnection();
        String query="Select kitap_adi from tbl_kitap where kitap_id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,kitap_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("kitap_adi");
        }
        else{
            return null;
        }
    }

    public boolean duyuru_ekle(String message,int ogrenci_id) throws SQLException {
        Connection connection= DBConnector.getInstance().getConnection();
        String query="insert into tbl_duyuru (duyuru_icerik,ogrenci_id) values (?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,message);
        preparedStatement.setInt(2,ogrenci_id);
        ResultSet resultSet;
        if(preparedStatement.execute()){
            return true;
        }
        else{
            return false;
        }
    }

    public ObservableList<String> announce_list() throws SQLException {
        String selectQuery = "SELECT duyuru_icerik from tbl_duyuru where ogrenci_id=?";


        ObservableList<String> books = FXCollections.observableArrayList();

        Connection connection = DBConnector.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1,student_login.ogr_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            int i=1;
            while (resultSet.next()) {
                String duyuru = resultSet.getString("duyuru_icerik");
                books.add(i+". duyuru: "+duyuru);
                i+=1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return books;
    }

    public String get_ogr_name() throws SQLException {
        Connection connection=DBConnector.getInstance().getConnection();
        String query="select ogrenci_adi,ogrenci_soyadi from tbl_ogrenci where ogrenci_id= (?)";
        PreparedStatement ps=connection.prepareStatement(query);
        ps.setInt(1,student_login.ogr_id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()){
            return rs.getString("ogrenci_adi")+" "+rs.getString("ogrenci_soyadi");
        }
        return "";
    }












}
