import model.User;
import ui.LoginUI;
import ui.HRAdminMenuUI;
import ui.EmployeeMenuUI;

public class Main {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        User loggedInUser = loginUI.login();

        if (loggedInUser == null) {
            System.out.println("Login failed.");
            return;
        }

        if (loggedInUser.getRoleId() == 1) {
            new HRAdminMenuUI().showMenu();
        } else {
            new EmployeeMenuUI(loggedInUser).showMenu();
        }
    }
}