package firstTest;

import org.testng.annotations.Test;

import java.sql.*;

public class Query04 {

    @Test
    public void testName() throws SQLException, ClassNotFoundException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456"
        );

        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4.Adım:
        String sql="create table biskuvi (biskuvi_adi varchar (50), biskuvi_turu varchar (50))";
        statement.execute(sql);

        //NOT
       // statement.execute => bu metot DDL komutlarında kullanır
       // statement.executeQuery => sorgu yaparken kullanılır

        //5.Adım: Connection Kapat
        connection.close();
        statement.close();
    }

    @Test
    public void testName1() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456"
        );


        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();


        //4.Adım:
        String sql="alter table biskuvi add biskuvi_agirligi REAL";
        statement.execute(sql);

        //5.Adım: Connection Kapat
        connection.close();
        statement.close();


    }

    @Test
    public void testName2() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456"
        );


        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();


        //4.Adım:
        String sql="insert into biskuvi values('sütlü biskuvi','sütlü',100)";
        statement.execute(sql);

        //5.Adım: Connection Kapat
        connection.close();
        statement.close();


    }

    @Test
    public void testName3() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2.Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "123456"
        );


        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();


        //4.Adım:
        String sql="drop table biskuvi";
        statement.execute(sql);

        //5.Adım: Connection Kapat
        connection.close();
        statement.close();


    }
}
