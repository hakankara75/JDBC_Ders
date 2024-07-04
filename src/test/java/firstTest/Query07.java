package firstTest;

import org.testng.annotations.Test;

import java.sql.*;

public class Query07 {
    @Test
    public void testName() throws ClassNotFoundException, SQLException {

        //1. Adım: Driver Tanımla
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e Bağlan
        Connection connection= DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Deneme",
                "postgres",
                ",533Burak"

        );

        //3. Adım: Statement Oluştur
//        Statement statement=connection.createStatement();
//        statement.executeUpdate("insert into giderler values(4, 1669, 'Yemek')");

        //4. Adım: PreparedStatement Oluştur
      PreparedStatement preparedStatement= connection.prepareStatement("insert into giderler values(?, ?, ?)");
      preparedStatement.setInt(1,6);
      preparedStatement.setInt(2,456);
      preparedStatement.setString(3,"Gezi");
      preparedStatement.executeUpdate();

        connection.close();
        //statement.close();
        preparedStatement.close();


    }
}
