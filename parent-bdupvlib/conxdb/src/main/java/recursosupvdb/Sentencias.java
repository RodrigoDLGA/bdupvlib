package main.java.recursosupvdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sentencias {
    public static boolean flagSentencias = true;
    protected static void selectExecute(String sql) {
        System.out.println("\nselectExecute(): ");
        Connection conn = ConnectDB.getConnection();
        try(conn; Statement st = conn.createStatement()) {
            System.out.println(st.execute(sql));
            ResultSet rs = st.getResultSet();
            System.out.println(rs == null);
            System.out.println(st.getUpdateCount());
            while (rs.next()) {
                System.out.println(rs.getString(1) + " ");
            }
            flagSentencias = false;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void generalExecute(String sql){
        System.out.println("\ngeneralExecute()");
        Connection conn = ConnectDB.getConnection();
        try(conn; Statement st = conn.createStatement()){
            System.out.println(st.execute(sql));
            System.out.println(st.getResultSet() == null);
            System.out.println(st.getUpdateCount());
            flagSentencias = false;
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
