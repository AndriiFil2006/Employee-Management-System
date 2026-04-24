package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        String host = Config.get("DB_HOST");
        String port = Config.get("DB_PORT");
        String dbName = Config.get("DB_NAME");
        String user = Config.get("DB_USER");
        String password = Config.get("DB_PASSWORD");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=true&requireSSL=true";

        System.out.println("URL: " + url);

        return DriverManager.getConnection(url, user, password);
    }
}