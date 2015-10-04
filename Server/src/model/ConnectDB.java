package model;

import java.sql.*;

/**
 * Created by panda on 27/09/2015.
 */
public class ConnectDB {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Facebook", "root", "root");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.print("error: " + e);
            System.out.print(e);
        }
    }

    public ResultSet getUser() {
        try {
            String query = "select * from User";
            this.rs = st.executeQuery(query);

        } catch (Exception e) {
            System.out.print(e);
        }
        return this.rs;
    }


    public ResultSet getStatus(int id ) {
        try {
            String query = "select * from Status WHERE IdUser =" + id;
            this.rs = st.executeQuery(query);

        } catch (Exception e) {
            System.out.print(e);
        }
        return this.rs;
    }


    public ResultSet runQuery(String query){
        try {
            this.rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.rs;
    }

    public void runQueryUpdate(String query){
        try {
             st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectDB connectDB = new ConnectDB();
        ResultSet rs = connectDB.getUser();
        rs = connectDB.getStatus(1);
        try {
            while (rs.next()) {
                int id = rs.getInt("Id");
                int iduser = rs.getInt("IdUser");
                String content = rs.getString("Content");
                System.out.print(id+"  "+iduser+"  "+content);

//                int id = rs.getInt("Id");
//                String username = rs.getString("Username");
//                String password = rs.getString("Password");
//                String firstname = rs.getString("Firstname");
//                String lastname = rs.getString("Lastname");
//                int age = rs.getInt("Age");
//                String gender = rs.getString("Gender");
//                String phone = rs.getString("Phone");
//                String email = rs.getString("Email");
//                Boolean lock = rs.getBoolean("Lock");
//                System.out.println(username + "  " + password + "  " + firstname + "  " + lastname + "  " + age + "  " + gender + "  " + phone + "  " + email + "  " + lock);
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

}