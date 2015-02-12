/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package things;

import java.util.ArrayList;

/**
 *
 * @author AceTalibong
 */
public class User {
    String username;
    private String password;
    String name;
    ArrayList projects;
    ArrayList areaOfResponsibilities;
    
    public User(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        projects = new ArrayList <Project>();
        areaOfResponsibilities = new ArrayList <AreaOfResponsibility>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addProject(Project project){
        projects.add(project);
    }
    
    public void addAreaOfResponsibility(AreaOfResponsibility aor){
        areaOfResponsibilities.add(aor);
    }
}
