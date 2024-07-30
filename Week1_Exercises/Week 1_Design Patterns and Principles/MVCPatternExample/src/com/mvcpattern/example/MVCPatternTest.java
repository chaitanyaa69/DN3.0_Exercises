package com.mvcpattern.example;

import com.mvcpattern.example.controller.StudentController;
import com.mvcpattern.example.model.Student;
import com.mvcpattern.example.view.StudentView;

public class MVCPatternTest {
    public static void main(String[] args) {
        // Fetch student record based on student ID from the database
        Student model = retrieveStudentFromDatabase();

        // Create a view to write student details on console
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        // Update model data
        controller.setStudentName("John Doe");

        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        Student student = new Student();
        student.setId("1");
        student.setName("Robert");
        student.setGrade("A");
        return student;
    }
}
