/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw.pkg6;

import java.util.Comparator;

/**
 *
 * @author Kyle
 */
public class SemesterComparator implements Comparator<Course>  {
        public int compare(Course course1, Course course2){
        String name1 = course1.getSemester();
        String name2 = course2.getSemester();
        return name1.compareTo(name2);
    }
}
