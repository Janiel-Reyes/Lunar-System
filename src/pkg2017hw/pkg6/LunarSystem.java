/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2017hw.pkg6;

import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Kyle
 */
public class LunarSystem {

    private static HashMap<String, Student> database = new HashMap<String, Student>();

    ;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean run = true;
        boolean loggedIn = false;
        Scanner scan = new Scanner(System.in);
        HashMap<String, Student> storage = null;
        boolean isRegistrar = false;
        //Reading the file and Writing the file variables
        FileOutputStream file = null;
        ObjectOutputStream outStream;
        FileInputStream saved;
        ObjectInputStream inStream;
        try {
            saved = new FileInputStream("database.obj");
            inStream = new ObjectInputStream(saved);
            storage = (HashMap<String, Student>) inStream.readObject();
            inStream.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (storage != null) {
            database = storage;
        }
        //Temporary Variables to make the student
        String tempWebId, tempDepartment, tempSemester;
        int tempCourseNum = 0;
        // This is for the logged in student or registrar
        Student tempStudent = null;

        //Begin the Program
        String input;
        while (run) {

            System.out.println("Welcome to the Lunar System, "
                    + "a second place course registration system for second rate courses "
                    + "at a second class school. ");
            if (storage == null) {
                System.out.println("No Previous Data Found");
            }
            System.out.println("Login Menu:\n"
                    + "L)Login\n"
                    + "S)Save state and quit\n"
                    + "Q) Quit without Saving\n");
            input = scan.nextLine();

            //INPUTs
            if (input.equalsIgnoreCase("L")) {
                System.out.println("Enter Your Web ID");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("REGISTRAR")) {
                        isRegistrar = true;
                        loggedIn=true;
                        System.out.println("LOGGED IN ");
                    }
                else if (database.get(input) == null) {
                    System.out.println("No Id with that name");
                }
                 
                else {
                    input.toLowerCase();
                    tempStudent = database.get(input);
                    loggedIn = true;
                    System.out.println("LOGGED IN ");
                   
                }
            } else if (input.equalsIgnoreCase("S")) {
                System.out.println("Good bye, please pick the right SUNY next time!");
                //Writing / saving file
                try {
                    file = new FileOutputStream("database.obj");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LunarSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    outStream = new ObjectOutputStream(file);
                    //This PART WILL SAVE THE STUFF
                    outStream.writeObject(database);
                    outStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(LunarSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                run = false;
                System.exit(0);
            } else if (input.equalsIgnoreCase("Q")) {
                System.out.println("Good bye, please pick the right SUNY next time!");
                run = false;
                System.exit(0);
            } else {
                System.out.println("Enter a Valid letter");
            }
            while (loggedIn) {
                if (isRegistrar) {
                    // This is for adding student or moving it 
                    Student tempStudent2 = null;
                    System.out.println("Registrar Menu:\n"
                            + "R - Register a Student\n"
                            + "D - De-Register a Student\n"
                            + "E - View Students enrolled in a class\n"
                            + "L - Logout");
                    input= scan.nextLine();
                    if (input.equalsIgnoreCase("R")) {
                        System.out.println("Enter WebId of student you want to Register");
                        tempWebId = scan.nextLine();
                        tempWebId.toLowerCase();
                        tempStudent2 = new Student(tempWebId);
                        if (database.containsKey(tempWebId)) {
                            System.out.println("Student already registered");
                        } else {
                            database.put(tempWebId, tempStudent2);
                            System.out.println("Student has been Registered");
                        }
                    } else if (input.equalsIgnoreCase("D")) {
                        System.out.println("Enter WebId of student you want to De-Register");
                        tempWebId = scan.nextLine();
                        tempWebId.toLowerCase();
                        database.remove(tempWebId);
                        System.out.println("Student has been removed");
                    } else if (input.equalsIgnoreCase("E")) {
                        System.out.println("Please enter a Department");
                        tempDepartment = scan.nextLine();
                        System.out.println("Please enter course number");
                        tempCourseNum = scan.nextInt();
                        System.out.println("Students Registered in " + tempDepartment + " " + tempCourseNum);
                        System.out.println("Student     Semester");
                        System.out.println("---------------------------------------");

                        // Going through the hashmap 
                        for (Student value : database.values()) {
                            for (int i = 0; i < value.getCourses().size(); i++) {
                                //Checking if the Department is equal to what is inputed
                                if (value.getCourses().get(i).getDepartment().equalsIgnoreCase(tempDepartment)) {
                                    System.out.print(value.getWebID() + "     ");
                                }
                                //Checks the course course number
                                if (value.getCourses().get(i).getCourseNumber() == tempCourseNum) {
                                    System.out.print(value.getCourses().get(i).getSemester());
                                }
                            }
                            System.out.println();
                        }

                    } else if (input.equalsIgnoreCase("L")) {
                        System.out.println("You have been logged out");
                        loggedIn = false;
                        isRegistrar= false;

                    } else {
                        System.out.println("Enter a Valid letter");
                    }
                } else {
                    System.out.println("Student Menu:\n"
                            + "A - Add a Class\n"
                            + "D - Drop a class\n"
                            + "C - List all classes by Department\n"
                            + "S - List all classes by Semester\n"
                            + "L - Logout");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("A")) {
                        System.out.println("Please Enter Department name: ");
                        tempDepartment = scan.nextLine();
                        System.out.println("Enter Course number");
                        tempCourseNum = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter a Semester");
                        tempSemester = scan.nextLine();
                        String springorFall = "";
                        Course courseBeingAdded = new Course(tempDepartment, tempCourseNum, tempSemester);
                        tempStudent.addCourses(courseBeingAdded);
                        if (tempSemester.substring(0, 1).equalsIgnoreCase("F")) {
                            springorFall = "Fall";
                        } else {
                            springorFall = "Spring";
                        }
                        System.out.println(tempDepartment + " "
                                + tempCourseNum
                                + " added in "
                                + springorFall + " "
                                + tempSemester.substring(1));
                    } else if (input.equalsIgnoreCase("D")) {

                        System.out.println("Enter Department of Course you want to drop");
                        tempDepartment = scan.nextLine();
                        System.out.println("Enter Course number");
                        tempCourseNum = scan.nextInt();
                        scan.nextLine();
                        System.out.println("Enter the Semester");
                        tempSemester = scan.nextLine();
                        for(int i =0; i< tempStudent.getCourses().size();i++){
                            if(tempStudent.getCourses().get(i).getCourseNumber()==tempCourseNum
                                    && tempDepartment.equalsIgnoreCase(tempStudent.getCourses().get(i).getDepartment())
                                    && tempSemester.equalsIgnoreCase(tempStudent.getCourses().get(i).getSemester()))
                            {
                                tempStudent.getCourses().remove(i).printString();
                            }
                        }
                        System.out.println("Class had been removed");
                        
                    } else if (input.equalsIgnoreCase("C")) {
                        tempStudent.sortListByCourseName();
                        tempStudent.printCourses();

                    } else if (input.equalsIgnoreCase("S")) {
                        tempStudent.sortListBySemester();
                        tempStudent.printCourses();
                    } else if (input.equalsIgnoreCase("L")) {
                        System.out.println("You have logged out");
                        loggedIn =false;
                    } else {
                        System.out.println("Enter a Valid letter");
                    }
                }
            }
        }
    }

}
