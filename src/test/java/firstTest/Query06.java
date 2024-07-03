package firstTest;

import org.testng.annotations.Test;

import java.sql.*;

public class Query06 {
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

        //3. Adım: Statement Oluştur
        Statement statement=connection.createStatement();

        //4. Adım:
        ResultSet resultSet =statement.executeQuery("select * from ogrenciler");

        //5. Adım: Metadata Al
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        System.out.println("Tablonun ismi = " + resultSetMetaData.getTableName(1).toUpperCase());
        System.out.println("2. sütunun ismi = " + resultSetMetaData.getColumnName(2));

        connection.close();
        statement.close();
        resultSet.close();

    }
}
