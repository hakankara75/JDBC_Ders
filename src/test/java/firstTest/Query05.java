package firstTest;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Query05 {


    @Test
    public void testName() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                "123456"
        );


        //3.Adım: Statement Oluştur
        Statement statement=connection.createStatement();


        //4. Adım: Tek veri ekleme
        statement.executeUpdate("insert into ogrenci_bilgiler values(34,58,'Gitar','Kadın','Matematik')");


        connection.close();
        statement.close();


    }

    @Test
    public void testName1() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                "123456"
        );


        //3.Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4. Adım: Çoklu veri ekleme
        String [] veri={
                "insert into ogrenci_bilgiler values(40,79,'Yazılım','Erkek','Coğrafta Öğretmeneliği')",
                "insert into ogrenci_bilgiler values(34,58,'Yazılım','Kadın','Tarih Öğretmeneliği')",
                "insert into ogrenci_bilgiler values(34,58,'Yazılım','Erkek','Yaşlı Bakım')"
                
        };

        int counter=0;
        for (String each: veri){
            counter=counter+statement.executeUpdate(each);
        }

        System.out.println(counter+ " adet veri ogrenci_bilgiler tablosuna eklenmiştir");

        connection.close();
        statement.close();

    }

    @Test
    public void testName2() throws ClassNotFoundException, SQLException {
        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                "123456"
        );


        //3.Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4. Adım: Çoklu veri ekleme
        String [] veri={
                "insert into ogrenci_bilgiler values(19,91,'Satranç','Kadın','Kimya')",
                "insert into ogrenci_bilgiler values(50,47,'Satranç','Erkek','Rehberlik')",
                "insert into ogrenci_bilgiler values(33,40,'Satranç','Kadın','Turizm')"

        };

        for (String each:veri){
            statement.addBatch(each); //dataları loop içinde bir araya getirir ve tek seferde ekler
        }

        statement.executeBatch(); //birden fazla sql komutunu tek seferde gönderir

        connection.close();
        statement.close();
    }
}
