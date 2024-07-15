package firstTest;

import org.junit.Test;
import org.testng.Assert;
import utilities.JdbcUtils;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static utilities.DatabaseUtilty.*;
import static utilities.JdbcUtils.connectToDataBase;
import static utilities.JdbcUtils.createStatement;

public class Query09 {

    @Test
    public void positiveTest() {
        createConnection();
        String sql = "select * from ogrenci_bilgiler";
        String column = "kulup_calismasi";
        String query = "Havacılık";

        List<Object> kulupCalismasiColumn = getColumnData(sql, column);

        Assert.assertTrue(kulupCalismasiColumn.contains(query), "Deneme database'inde " + query + " verisi yoktur");

        closeConnection();

    }

    @Test
    public void name() {

        String hostName="localhost";
        String dbName="Deneme";
        String username="postgres";
        String password= "123456";
        connectToDataBase(hostName,dbName,username, password);
        createStatement();

        String sql="select * from ogrenci_bilgiler";

        ResultSet resultSet = JdbcUtils.executeQuery(sql);

        int counter=0;

        try{
        while (resultSet.next()) {

            if (resultSet.getInt(1) == 15){
               counter++;
            }
        }
        }catch (Exception exception){

        }


        assertTrue("Deneme databesinde aranan değer bulunamadı",counter>0);

    }
}
