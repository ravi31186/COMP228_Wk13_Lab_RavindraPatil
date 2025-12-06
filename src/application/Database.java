package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/wk13_lab_RavindraPatil",
            "root",
            "myTest1234"
        );
    }

    public static void save(int run, int number) {
        try {
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO lotto_results(run_no, number_val) VALUES(?, ?)"
            );
            ps.setInt(1, run);
            ps.setInt(2, number);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println("DB Save Error: " + e.getMessage());
        }
    }
}
