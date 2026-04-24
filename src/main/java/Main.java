import dao.UserDAO;
import model.User;
import ui.LoginUI;
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

        // User newUser = new User(6, 2, "newUser", "passwordHash");
        // boolean successInsertUser = userDao.insertUser(newUser);
        // System.out.println("User inserted: " + successInsertUser);

        boolean successUpdatingLogin = userDao.updateLastLogin(2);
        System.out.println("Last login updated: " + successUpdatingLogin);

        boolean successDeactivatingUser = userDao.deactivateUser(5);
        System.out.println("User deactivated: " + successDeactivatingUser);

        boolean userNameExists = userDao.usernameExists("olivia.d");
        System.out.println("Username exists: " + userNameExists);
        userNameExists = userDao.usernameExists("charlie.kirk77");
        System.out.println("Username exists: " + userNameExists);

        LoginUI loginUI = new LoginUI();
        User loggedInUser = loginUI.login();

        if(loggedInUser != null) {
            System.out.println("Logged in user: " + loggedInUser.getUsername());
        }
        else {
            System.out.println("Login failed.");
        }
    }
}