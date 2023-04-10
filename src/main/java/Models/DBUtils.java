package Models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "hospital";
        String databaseUser = "root";
        String databasePassword = "bhallagena123";
        String url = "jdbc:mysql://localhost/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);


        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
