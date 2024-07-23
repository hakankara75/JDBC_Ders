package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class JdbcUtils {

        private static Connection connection;
        private static Statement statement;
        private static ResultSet resultSet;

        /**
         * Bu metot ile bir database'e bağlanılır
         * @param hostName yerine bağlanılacak database hostu
         * @param dbName yerine sorgu yapılmak istenen DB ismi
         * @param username yerine DB kullanmaya yetkili kullanıcı adı
         * @param password yerine DB kullanmaya yetkili kişinin şifresi
         * @return tipi Connection döner
         */
        public static Connection connectToDataBase(String hostName, String dbName, String username, String password) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (connection != null) {
                System.out.println("Connection Success");
            } else {
                System.out.println("Connection Fail");
            }
            return connection;
        }

        /**
         * Bu metot ile Statement kurulur
         * @return tipi Statement dır
         */
        public static Statement createStatement() {
            try {
                statement = connection.createStatement();
                System.out.println("Statement success");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return statement;
        }

        /**
         * Bu metot ile bir DB üzerinde özel bir bilginin varlığını öğrenmek için query oluşturulur
         * @param sql yerine DB üzerinden öğrenilmek istenen query yazılır
         * @return tipi Boolean dır
         */
        public static boolean execute(String sql) {
            boolean isExecute;
            try {
                isExecute = statement.execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return isExecute;
        }

        /**
         * Bu metot ile Connection ve Statement kapatılır
         */
        public static void closeConnectionAndStatement() {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection.isClosed() && statement.isClosed()) {
                    System.out.println("Connection and statement closed!");
                } else {
                    System.out.println("Connection and statement NOT closed!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        /**
         * Bu metot ile bir DB üzerinde ismi verilen tablo oluşturulur
         * @param tableName yerine oluşturulmak istenen tablo ismi verilir
         * @param columnName_dataType yerine oluşturulan tabloda sütuna verilecek data tipi girilir
         */
        public static void createTable(String tableName, String... columnName_dataType) {
            StringBuilder columnName_dataValue = new StringBuilder("");
            for (String w : columnName_dataType) {
                columnName_dataValue.append(w).append(",");
            }
            columnName_dataValue.deleteCharAt(columnName_dataValue.length() - 1);
            try {
                statement.execute("CREATE TABLE " + tableName + "(" + columnName_dataValue + ")");
                System.out.println("Table " + tableName + " successfully created!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static ResultSet executeQuery(String query) {
            try {
                resultSet = statement.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return resultSet;
        }


        /**
         * Bu metot ile bir tabloya veri eklenir ve kaç satır veri eklendiği rakam olarak döner
         * @param query yerine eklenecek veri yazılır
         * @return tipi Integer dır.
         */
        public static int executeUpdate(String query) {
            int guncellenenSatirSayisi;
            try {
                guncellenenSatirSayisi = statement.executeUpdate(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return guncellenenSatirSayisi;
        }

        /**
         * Bu metot bir tabloya veri eklemeye yarar
         * @param tableName yerine veri eklenecek tablo adı
         * @param columnName_Value yerine veri eklenecek sütun adı ve bir boşluk bırakıp eklenecek veri yazılır
         */
        public static void insertDataIntoTable(String tableName, String... columnName_Value) {
            StringBuilder columnNames = new StringBuilder("");
            StringBuilder values = new StringBuilder("");
            for (String w : columnName_Value) {
                columnNames.append(w.split(" ")[0]).append(",");//Bir String değeri ikiye bölüp birinciyi sütun adı, ikinciyi sütun değeri olarak alıyorum.
                values.append(w.split(" ")[1]).append(",");
            }
            columnNames.deleteCharAt(columnNames.lastIndexOf(","));//En son virgülü siliyor.
            values.deleteCharAt(values.lastIndexOf(","));
            //"INSERT INTO      members     ( id, name, address ) VALUES(123, 'john', 'new york')"
            String query = "INSERT INTO " + tableName + "(" + columnNames + ") VALUES(" + values + ")";
            execute(query);//execute methodu üstte oluşturuldu, query'yi çalıştırıyor.
            System.out.println("Data " + tableName + " tablosuna girildi.");
        }

        /**
         * Bu metot ile bir sütundaki veriler List içine atılır
         * @param columnName yerine verisi çekilecek sütun adı
         * @param tableName yerine üzerinde sorgu yapılacak tablo adı
         * @return tipi Array List dir.
         */
        public static List<Object> getColumnList(String columnName, String tableName) {
            List<Object> columnData = new ArrayList<>();//ResultSet'ten alınan datanın koyulacağı List.
            //SELECT        id          FROM      students
            String query = "SELECT " + columnName + " FROM " + tableName;
            executeQuery(query);// => Bu method üstte oluşturuldu. Query'yi çalıştırıp alınan datayı 'resultSet' container'ı içine atama yapıyor.
            try {
                while (resultSet.next()) {
                    columnData.add(resultSet.getObject(columnName));//add methodu ile alınan sütun değerlerini List'e ekliyor.
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return columnData;
        }
    }