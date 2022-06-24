package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentModel {
    private int id;
    private String name;

    final String dbURL = "jdbc:mysql://localhost/attendance";
    final String dbUsername = "root";
    final String dbPassword = "";

    public StudentModel(int id) {
        getData(id);
    }

    public void getData(int id) {
        try{
            Connection conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
            String query = "SELECT * FROM student WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                this.id = id;
                this.name = rs.getString("name");
            }

            stmt.close();
            conn.close();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
