package com.abb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class GetBooks {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/vindb";
        String username = "postgres";
        String password = "Welcome#123";

        String sql = "select * from books where id = ?";

        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter book id to search: ");
            int id = scanner.nextInt();
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    var title = rs.getString("title");
                    var author = rs.getString("author");
                    var price = rs.getDouble("price");

                    System.out.println("Title     : " + title);
                    System.out.println("Author    : " + author);
                    System.out.println("Price     : " + price);
                }
                else{
                    System.out.println("Invalid ID. No data found");
                }

            } // rs.close() called here

        } // all resources created in the try() block will be closed here
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
