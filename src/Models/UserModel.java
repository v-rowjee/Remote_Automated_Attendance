package Models;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private int id;
    private String name;
    private String type;
    private List<ModuleModel> moduleList;

    public UserModel(){}

    public UserModel(int id) {
        getData(id);
    }

    public void getData(int id) {
        this.id = id;
        name = "Bikash";
        type = "Lecturer";
        moduleList = new ArrayList<>(){{
            new ModuleModel(2000);
            new ModuleModel(3000);
            new ModuleModel(4000);
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
