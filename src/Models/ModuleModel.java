package Models;
import java.util.ArrayList;
import java.util.List;

public class ModuleModel {
    private int id;
    private String name;
    private List<StudentModel> studentList;

    public ModuleModel(){}

    public ModuleModel(int id) {
        getData(id);
    }

    public void getData(int id) {
        this.id = id;
        name = "WEB";
        studentList = new ArrayList<>(){{
            new StudentModel(1);
            new StudentModel(2);
        }};
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
