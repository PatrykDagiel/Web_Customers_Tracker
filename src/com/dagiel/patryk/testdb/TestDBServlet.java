package com.dagiel.patryk.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // setup connection variables
        String user = "hbstudent";
        String pass = "hbstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useUnicode=true&amp&useJDBCCompliantTimezoneShift=true&amp&useLegacyDatetimeCode=false&amp&serverTimezone=UTC ";
        String driver = "com.mysql.cj.jdbc.Driver";

        // get connection to database

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);

            // load DB driver
            Class.forName(driver);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection succesful");
            out.println("Success");

            myConn.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }
}
