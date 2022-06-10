package Models;
import java.util.List;

public class ModuleModel {
    private UserModel user;
    private List<StudentModel> studentList;

    public ModuleModel(){}

    public ModuleModel(UserModel user, List<StudentModel> studentList) {
        this.user = user;
        this.studentList = studentList;
    }

    public UserModel getUser() {
        return user;
    }

    public List<StudentModel> getStudentList() {
        return studentList;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setStudentList(List<StudentModel> studentList) {
        this.studentList = studentList;
    }
}
