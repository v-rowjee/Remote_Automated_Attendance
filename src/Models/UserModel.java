package Models;

import java.util.List;

public class UserModel {
    private int id;
    private String name;
    private String type;
    private List<ModuleModel> moduleList;

    public UserModel(){}

    public UserModel(int id, String name, String type, List<ModuleModel> moduleList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.moduleList = moduleList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setModuleList(List<ModuleModel> moduleList) {
        this.moduleList = moduleList;
    }
}
