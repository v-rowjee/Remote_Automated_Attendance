package Models;

import java.util.List;

public class StudentModel {
     // id, name, modules[],
       private int id;
       private String name;
       private List<ModuleModel> moduleList;

    public StudentModel(int id, String name, List<ModuleModel> moduleList) {
        this.id = id;
        this.name = name;
        this.moduleList = moduleList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ModuleModel> getModuleList() {
        return moduleList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModuleList(List<ModuleModel> moduleList) {
        this.moduleList = moduleList;
    }
}
