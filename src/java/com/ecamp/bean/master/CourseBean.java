
package com.ecamp.bean.master;
import com.ecamp.bean.master.DegreeBean;
public class CourseBean {
    private byte courseId;
    private String courseName;
    private String FullName;
    private DegreeBean degreeBean;

    public byte getCourseId() {
        return courseId;
    }

    public void setCourseId(byte courseId) {
        this.courseId = courseId;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public DegreeBean getDegreeBean() {
        return degreeBean;
    }

    public void setDegreeBean(DegreeBean degreeBean) {
        this.degreeBean = degreeBean;
    }
}
