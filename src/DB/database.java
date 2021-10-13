package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    Connection con;
    String db = "hospital";
    String url="jdbc:mysql://localhost:3306/" + db;
    String user="root";
    String pass="";
    
    
    public Connection conectardb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return con;
    }
    
}
