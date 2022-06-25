package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModuleModel {
    private int id;
    private String name;
    private List<StudentModel> studentList;

    final String dbURL = "jdbc:mysql://localhost/attendance";
    final String dbUsername = "root";
    final String dbPassword = "";

    public ModuleModel(int id) {
        getData(id);
    }

    public void getData(int id) {
        try{
            Connection conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
            String query = "SELECT * FROM module WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                this.id = id;
                this.name = rs.getString("name");
            }

            String query2 = "SELECT * FROM studentmodule WHERE mid = ?";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1,id);
            ResultSet rs2 = stmt2.executeQuery();

            studentList = new ArrayList<>();
            while(rs2.next()){
                studentList.add(new StudentModel(rs2.getInt("sid")));
            }

            stmt.close();
            stmt2.close();
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

    public List<StudentModel> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentModel> studentList) {
        this.studentList = studentList;
    }
}
