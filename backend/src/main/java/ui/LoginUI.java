package ui;

import dao.UserDAO;
import java.util.Scanner;
import model.User;
import util.PasswordHasher;

public class LoginUI {
    private final Scanner scanner;
    private final UserDAO userDao;

    public LoginUI()
    {
        this.scanner = new Scanner(System.in);
        this.userDao = new UserDAO();
    }

    public User login()
    {
        System.out.println("=== Emloyee Managment System ===\n");

        System.out.println("Username: ");
        String username = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();


        User user = userDao.findByUsername(username);

        if (user == null) 
        {
            System.out.println("Invalid username or password.");
            return null;
        }

        if(!user.getIsActive())
        {
            System.out.println("Account is deactivated. Please contact administrator.");
            return null;
        }

        if(!PasswordHasher.verifyPassword(password, user.getPasswordHash()))
        {
            System.out.println("Invalid username or password.");
            return null;
        }

        userDao.updateLastLogin(user.getUserId());

        System.out.println("Login successfull!\n Welcome, " + user.getUsername() + "!");
        return user;
    }
}