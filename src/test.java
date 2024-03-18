import java.util.Scanner;

// package com.company;

public class test {

  public static void main(String[] args) {
    // Declare the variables
    int num = 0;

    // Input the integer
    System.out.println("Enter the integer: ");

    try (// Create Scanner object
    Scanner s = new Scanner(System.in)) {
      // Read the next integer from the screen
      num = s.nextInt();
    }

    // Display the integer
    System.out.println("Entered integer is: " + num);
  }
}



    // //  for creteing table of Confrence
    // try {
    //       Statement stmt = c.createStatement();
    //       String sql =
    //         "CREATE TABLE Confrence " +
    //         "(id INTEGER not NULL, " +
    //         " venue TEXT, " +
    //         " PRIMARY KEY ( id ))";
    //       stmt.executeUpdate(sql);
    //       System.out.println("Created Confrence table in given database...");
    //     } catch (SQLException e) {
    //       e.printStackTrace();
    //     }

   //  //  for creteing table of Author
   //  try {
   //    Statement stmt = c.createStatement();
   //    String sql =
   //      "CREATE TABLE Author " +
   //      "(id INTEGER not NULL, " +
   //      " first_Name VARCHAR(255) not NULL, " +
   //      " middle_Name VARCHAR(255), " +
   //      " last_Name VARCHAR(255), " +
   //      " PRIMARY KEY ( id ))";
   //    stmt.executeUpdate(sql);
   //    System.out.println("Created Confrence table in given database...");
   //  } catch (SQLException e) {
   //    e.printStackTrace();
   //  }

   //  for creteing table of main_author
   //  try {
   //    Statement stmt = c.createStatement();
   //    String sql =
   //      "CREATE TABLE main_Author " +
   //      "(author id, " +
   //      " first_Name VARCHAR(255) not NULL, " +
   //      " middle_Name VARCHAR(255), " +
   //      " last_Name VARCHAR(255), " +
   //      " PRIMARY KEY ( id ))";
   //    stmt.executeUpdate(sql);
   //    System.out.println("Created Confrence table in given database...");
   //  } catch (SQLException e) {
   //    e.printStackTrace();
   //  }