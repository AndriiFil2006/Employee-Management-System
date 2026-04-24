import java.sql.Connection;
import util.DBConnection;

public class Main 
{
    public static void main(String[] args) {
        {
            try {
                Connection conn = DBConnection.getConnection();
                System.out.println("Connected to the database successfully");
                conn.close();
            } catch (Exception e) {
                System.out.println("Database connection failed.");
                e.printStackTrace();
            }
        }
    }
}