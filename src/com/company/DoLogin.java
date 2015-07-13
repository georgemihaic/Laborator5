package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

/**
 * Created by Mihai on 6/13/2015.
 */
public class DoLogin {


    List<Login> loginList = new ArrayList<>();

    DoLogin() {
        try {
            dbRead();
        } catch (ClassNotFoundException e) {
           //  e.printStackTrace();
            System.err.println("ClassNotFoundException in constructorul clasei DoLogin!");
            System.exit(1);
        } catch (SQLException e) {
           //  e.printStackTrace();
            System.err.println("SQLException in constructorul clasei DoLogin!");
            System.exit(2);
        }
    }



    private void dbRead() throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/anemes_agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT nume,parola FROM \"Persoana\"");

        // 6. iterate the result set and print the values
        int i=0; // index
        while (rs.next()) {
            Login user=new Login(rs.getString("nume").trim(),rs.getString("parola").trim() );
            loginList.add(i++, user); // metoda void .add() a interfetei List, necesita un index (i, );
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }


    public boolean login() {
      return loginUntilSuccess(0);
   // emuleaza functionalitatea metodei login() originale,
   // prin ocolirea buclei while() din metoda loginUntilSuccess(int);
    }

    public boolean loginUntilSuccess() {

        return loginUntilSuccess(10); // Numarul maxim de logari esuate permise default=10;
    }

    public boolean loginUntilSuccess(int maxLogins) {

        boolean found=false;

        do {
            System.out.print("Introdu userul: ");
            String user = rKbd();
            System.out.print("Introdu parola: ");
            String pwd = rKbd();
            // create a Login object with the user input;
            Login object = new Login(user, pwd);

            if (loginList.contains(object)) { // see class Login equals(Object) method;
                found = true;
                System.out.println("Succes :)");
                break;
            }
            else { System.out.println("Mai incearca ... "); }
            maxLogins = maxLogins -1; // Ai mai gresit o incercare;
        }  while(maxLogins > 0);
        System.err.println("HINT!  Adi pass.");
        return found;

    }

private String rKbd() { // Citeste tastatura;
    String citesc;
    do {
        Scanner sc = new Scanner(System.in);
        citesc = sc.nextLine();
    } while (citesc.isEmpty());
    return citesc.trim();
}

}
