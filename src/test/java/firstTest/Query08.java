package firstTest;

import org.testng.annotations.Test;
import pojo.Angaralilar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query08 {
    @Test
    public void testName() throws ClassNotFoundException, SQLException {
        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım:
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                "123456"
        );

        //3.Adım: Statement Oluştur
        Statement statement=connection.createStatement();
        statement.execute("create table angaralilar (isim varchar(50), soyisim varchar (50))");

        //4.Adım: Çok sayıda veri ekle
        List<Angaralilar> angaralilar=new ArrayList<>();
        angaralilar.add(new Angaralilar("Hakan", "Tanrıverdi"));
        angaralilar.add(new Angaralilar("Azra", "Can"));
        angaralilar.add(new Angaralilar("Yaşar", "Yılmaz"));
        angaralilar.add(new Angaralilar("Mert", "Şenses"));

        PreparedStatement preparedStatement= connection.prepareStatement("insert into angaralilar values (?,?)");
        for (Angaralilar each:angaralilar){
            preparedStatement.setString(1, each.getIsim());
            preparedStatement.setString(2, each.getSoyisim());

            preparedStatement.addBatch(); //tüm verileri birleştirir
        }

        preparedStatement.executeBatch(); //tüm verileri tek seferde işleme koyar

        connection.close();
        statement.close();
        preparedStatement.close();


    }
}
