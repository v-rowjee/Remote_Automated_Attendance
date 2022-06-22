package Models;

import java.util.List;

public class StudentModel {
     // id, name, modules[],
       private int id;
       private String name;

    public StudentModel(int id) {
        getData(id);
    }

    public StudentModel() {}

    public void getData(int id) {
        this.id = id;
        name = "Eric";
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
