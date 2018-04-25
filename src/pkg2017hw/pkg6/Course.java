/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw.pkg6;

import java.io.Serializable;

/**
 *
 * @author Kyle
 */
public class Course implements Serializable {

    private String department;
    private int courseNumber;
    private String semester;

    /**
     * Initializes the course to these three parameters
     *
     * @param department
     * @param courseNumber
     * @param semester
     */
    public Course(String department, int courseNumber, String semester) {
        this.department = department;
        this.courseNumber = courseNumber;
        this.semester = semester;
    }
    
    /*
    Getters
    */
    public String getDepartment() {
        return department;
    }
    public int getCourseNumber() {
        return courseNumber;
    }
    public String getSemester() {
        return semester;
    }
    /*
    Setters
    */
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public void printString(){
        System.out.println(department+"    "+ courseNumber+"      "+ semester);
    }
}
