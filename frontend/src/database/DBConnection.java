package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/hospital_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "root123";

    public static Connection connect() {

        try {

            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println(
                    "Database Connected!"
            );

            return con;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}