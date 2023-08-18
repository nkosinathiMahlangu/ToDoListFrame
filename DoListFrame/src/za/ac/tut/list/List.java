/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.list;

/**
 *
 * @author Student
 */
public class List {
    //Data mebere
    private String moduleName;
    private String desc;
    private String time;

    public List(String moduleName, String desc, String time) {
        this.moduleName = moduleName;
        this.desc = desc;
        this.time = time;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getDesc() {
        return desc;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return moduleName + "," + desc + ", will spend " + time + " on it.";
    }
    
}
