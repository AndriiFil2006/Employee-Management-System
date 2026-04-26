package util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

// will offer server a more secure way to store passwords

public class PasswordHashing {
    public static void main(String[] args) {
        Argon2 argon2 = Argon2Factory.create(
                Argon2Factory.Argon2Types.ARGON2id
        );

       // Admin

        System.out.println("ADMIN (admin123):");

        System.out.println(argon2.hash(3, 65536, 1, "admin123".toCharArray()));

        System.out.println("--------------------------------------------------");

        // Employees list

        String[][] employees = {

                {"Bruce", "Banner"},

                {"Kelly", "Clarkson"},

                {"Michael", "Jackson"},

                {"Ali", "Wong"},

                {"Daniel", "Wilson"},

                {"Terrell", "Whiting"},

                {"Andrii", "Fil"},

                {"Ammanuel", "Roberts"},

                {"Warren", "Buffet"},

                {"Ada", "Lovelace"}

            };

        String employeePassword = "pass123";

        // Generates hash for each employee

        for (String[] emp : employees) {

            String fullName = emp[0] + " " + emp[1];

            System.out.println(fullName + " (pass123):");

            System.out.println(

                    argon2.hash(3, 65536, 1, employeePassword.toCharArray())

            );

            System.out.println("--------------------------------------------------");

        }
    }
}