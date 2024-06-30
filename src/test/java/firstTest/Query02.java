package firstTest;

import org.postgresql.Driver;
import org.testng.annotations.Test;

import java.sql.*;

public class Query02 {

    @Test
    public void testName() throws ClassNotFoundException, SQLException {
        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e Bağlanma
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456"
        );

        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4.Adım: ResulSet Oluştur
        ResultSet resultSet= statement.executeQuery("select * from personel");

        //5. Adım: Yazdır
        while (resultSet.next()){
            System.out.println( + resultSet.getInt(1)
                    + resultSet.getString(2)
                    + resultSet.getString(3)
                    + resultSet.getString(4)
                    + resultSet.getString(5)
                    + resultSet.getString(6)
                    + resultSet.getString(7)
                    + resultSet.getString(8)
                    + resultSet.getString(9)
                    + resultSet.getString(10)
                    + resultSet.getString(11)
                    + resultSet.getInt(12)
                    + resultSet.getInt(13)
                    + resultSet.getInt(14));
        }

        System.out.println("-------------------------------");

        //6. Adım: 2. Sorgu
        ResultSet resultSet1= statement.executeQuery("select * from personel where cinsiyet='E'");
        while (resultSet1.next()){
            System.out.println(  resultSet1.getString(6));

        }

        //7. Adım: Connection Kapat
        connection.close();
        statement.close();
        resultSet.close();
        resultSet1.close();



    }
}
