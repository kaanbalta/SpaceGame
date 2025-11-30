package com.database;

import java.sql.*;
import java.util.LinkedHashMap;

public class Database {

    public static final String kullaniciAdi = "root";
    public static final String parola = "";
    public static final String databaseIsmi = "demo";
    public static final String host = "localhost";
    public static final int port = 3306;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;


    public boolean kullaniciEkle(String isim,String parola){

        LinkedHashMap<String,String> kullanicilar = kullaniciGetir();

        String sorgu = "Insert into Spacegame (İsim,Şifre,MAXpuan) values (?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sorgu);
            preparedStatement.setString(1, isim);
            preparedStatement.setString(2, parola);
            preparedStatement.setInt(3,0);

            if(kullanicilar.containsKey(isim)){
                return false;
            }
            else {
                preparedStatement.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void puanGuncelle(String isim,int puan){
        String sorgu = "Update Spacegame set MAXpuan=? where İsim=? AND MAXpuan < ?";

        try {
            preparedStatement = connection.prepareStatement(sorgu);
            preparedStatement.setInt(1,puan);
            preparedStatement.setString(2,isim);
            preparedStatement.setInt(3,puan);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<String,Integer> kullaniciPuanlariniGetir(){

        LinkedHashMap<String,Integer> data = new LinkedHashMap<String,Integer>();
        try {
            statement = connection.createStatement();
            String sorgu = "select * from Spacegame ORDER BY MAXpuan DESC";
            ResultSet resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                String isim = resultSet.getString("İsim");
                int puan = resultSet.getInt("MAXpuan");
                data.put(isim,puan);
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<String,String> kullaniciGetir(){

        LinkedHashMap<String,String> data = new LinkedHashMap<String,String>();
        try {
            statement = connection.createStatement();
            String sorgu = "select * from Spacegame ORDER BY MAXpuan DESC";
            ResultSet resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                String isim = resultSet.getString("İsim");
                String şifre = resultSet.getString("Şifre");
                data.put(isim,şifre);
            }
            return data;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Database(){
        String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseIsmi + "?useUnicode=true&characterEncoding=UTF-8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver bulunamadı");
        }

        try {
            connection = DriverManager.getConnection(url,kullaniciAdi,parola);
            System.out.println("Bağlantı başarılı");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
