package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username to delete:");
        String input = scanner.nextLine();
        


        // SQL Injection vulnerability
        String query = "DELETE FROM users WHERE username = '" + input + "';";

        try {
            // Hardcoded credentials (secret leak)
            String url = "jdbc:mysql://localhost:3306/mydb";
            String user = "root";
            String password = "admin123";

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);

            System.out.println("User deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
