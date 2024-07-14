package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtilty {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Bu metot ile database ile connection kurulur
     */
    public static void createConnection() {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "12345";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Bu metot ile database ile kurulan connection kapatılır
     */
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bu metot ile bir tablodaki veriler select ile ResultSet içine atılır.
     * @param query yerine select ile başlayan ve hangi sütun ve tablonun verilerinin çekileceğini belirten SQL query yazılır.
     */
    public static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * Bu metot ile bir tablodaki sütun isimleri bir List içine alınır
     * @param query yerine select ile başlayan ve hangi sütun ve tablonun verilerinin çekileceğini belirten SQL query yazılır.
     * @return Array List döner
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }
    /**
     * Bu metot ile bir sütundaki veriler alınır ve bir list içine atılır
     * @param query yerine yapılacak sorgu kodu yazılır
     * @param column yerine hangi sütundan veri alınacaksa o sütunun ismi verilir
     * @return bir List döner.
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

}