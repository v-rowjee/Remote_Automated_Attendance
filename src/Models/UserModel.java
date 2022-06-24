package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private int id;
    private String name;
    private String type;
    private List<ModuleModel> moduleList;

    final String dbURL = "jdbc:mysql://localhost/attendance";
    final String dbUsername = "root";
    final String dbPassword = "";

    public UserModel(){}

    public UserModel(int id) {
        getData(id);
    }

    public void getData(int id) {
        try{
            Connection conn = DriverManager.getConnection(dbURL,dbUsername,dbPassword);
            String query = "SELECT * FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                this.id = id;
                this.name = rs.getString("name");
                this.type = rs.getString("type");
            }

            String query2 = "SELECT * FROM module WHERE userid = ?";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1,id);
            ResultSet rs2 = stmt2.executeQuery();

            moduleList = new ArrayList<>();
            while(rs2.next()){
                moduleList.add(new ModuleModel(rs2.getInt("id")));
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ModuleModel> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleModel> moduleList) {
        this.moduleList = moduleList;
    }
}
