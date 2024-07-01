package firstTest;

import org.testng.annotations.Test;

import java.sql.*;

public class Query03 {

    @Test
    public void testName() throws SQLException, ClassNotFoundException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                "123456"
        );

        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4.Adım: ResultSet Oluştur
        ResultSet resultSet=statement.executeQuery("select ogrenci_soyadi, ogrenci_adi from ogrenciler where ogrenci_soyadi='Kara'");

        while (resultSet.next()){
            System.out.println(resultSet.getString(1)
                               +resultSet.getString(2));

        }

        //5.Adım: Connection Kapat
        connection.close();
        statement.close();
        resultSet.close();
    }
}
