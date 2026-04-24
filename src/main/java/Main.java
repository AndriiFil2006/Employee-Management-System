import dao.UserDAO;
import model.User;

public class Main 
{
    public static void main(String[] args) {

        // Check DB connection
        // try {
        //     Connection conn = DBConnection.getConnection();
        //     System.out.println("Connected to the database successfully");
        //     conn.close();
        // } catch (Exception e) {
        //     System.out.println("Database connection failed.");
        //     e.printStackTrace();
        // }

        UserDAO userDao = new UserDAO();

        User user = userDao.findByUsername("admin");

        if(user != null) {
            System.out.println("User Found!");
            System.out.println("username:" + user.getUsername());
            System.out.println("roleId: " + user.getRoleId());
        }
    }
}