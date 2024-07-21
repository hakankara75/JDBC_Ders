package firstTest;

import org.junit.Test;
import utilities.JdbcUtils;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static utilities.DatabaseUtilty.*;
import static utilities.JdbcUtils.*;

public class Query10 {
    @Test
    public void positiveTest1() {
        createConnection();
        String sql = "select * from ogrenci_bilgiler";
        String columnName = "bolum";

        List<String> columnList = getColumnNames(sql);

        assertTrue("Tabloda " + columnName + " isimli sütun bulunamadı", columnList.contains(columnName));

        closeConnection();

    }

    @Test
    public void positiveTest2() {
        String hostName = "localhost";
        String databaseName = "Deneme";
        String userName = "postgres";
        String password = "123456";

        connectToDataBase(hostName, databaseName, userName, password);

        createStatement();

        String tableName = "deneme";
        String dataType = "name varchar (20)";

        createTable(tableName, dataType);

        String query = "INSERT INTO deneme (name) VALUES ('Havacılık')";
        executeUpdate(query);

        String query2 = "select * from deneme";

        ResultSet resultSet = JdbcUtils.executeQuery(query2);

        try {
            while (resultSet.next()) {
                resultSet.getString(1);
            }
        } catch (Exception exception) {

        }

        closeConnectionAndStatement();

    }

    @Test
    public void positiveTest3() {
        String hostName = "localhost";
        String databaseName = "Deneme";
        String userName = "postgres";
        String password = "123456";

        connectToDataBase(hostName, databaseName, userName, password);

        createStatement();

        String tableName = "deneme";
        String columnName = "name";
        List<Object> columnData = getColumnList(columnName, tableName);

        for (Object data : columnData) {
            System.out.println(columnName + " sütunundaki data = " + data);
        }

        String valueToCheck = "Havacılık";
        assertTrue("Database'de " + valueToCheck + " verisi bulunamadı", columnData.contains(valueToCheck));

        closeConnectionAndStatement();

    }

    @Test
    public void positiveTest4() {
        String hostName = "localhost";
        String databaseName = "Deneme";
        String userName = "postgres";
        String password = "123456";

        connectToDataBase(hostName, databaseName, userName, password);

        createStatement();

        String tableName = "deneme";
        String data = "name 'Uzay'";
        insertDataIntoTable(tableName, data);

        String columnName = "name";
        List<Object> columnData = getColumnList(columnName, tableName);

        for (Object datum : columnData) {
            System.out.println(columnName + " sütunundaki veriler = " + datum);
        }

        String valueToCheck = "Uzay";
        assertTrue("Database'de " + valueToCheck + " verisi bulunamadı", columnData.contains(valueToCheck));

        closeConnectionAndStatement();

    }
}
