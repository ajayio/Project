
package com.ecamp.bean.master;

public class DegreeBean {
    private byte degreeId;   
    private String degreeName; 
    private String fullName;

    public byte getDegreeId() {
        return degreeId;
    }


    public void setDegreeId(byte degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }


    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setfullName(String fullName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
