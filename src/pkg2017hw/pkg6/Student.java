/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw.pkg6;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
/**
 *
 * @author Kyle
 */
public class Student implements Serializable{
    private String webID;
    private ArrayList<Course> courses;
    
    public Student(String webId){
        this.webID= webId;
        courses = new ArrayList<Course>();
        
    }
    public void sortListBySemester(){
        Collections.sort(courses ,new SemesterComparator());
    }
    public void sortListByCourseName(){
        Collections.sort(courses ,new CourseNameComparator());
    }
    public void printCourses(){
        System.out.println("Deptt.   Course   Semester");
        System.out.println("------------------------------------");
        for(int i = 0; i< courses.size(); i ++){
            courses.get(i).printString();
        }
    }
            
    public String getWebID() {
        return webID;
    }
      
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setWebID(String webID) {
        this.webID = webID;
    }

    public void addCourses(Course courseBeingAdded) {
        courses.add(courseBeingAdded);
    }
    
}
