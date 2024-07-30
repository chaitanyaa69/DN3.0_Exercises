package com.management.list;

import com.management.model.Task;

public class ListTest {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        Task t1 = new Task("T001", "Task 1", "Pending");
        Task t2 = new Task("T002", "Task 2", "In Progress");
        Task t3 = new Task("T003", "Task 3", "Completed");

        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        System.out.println("All Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearching for Task with ID T002:");
        Task found = taskList.searchTask("T002");
        if (found != null) {
            System.out.println(found.getTaskName() + " - " + found.getStatus());
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting Task with ID T002:");
        taskList.deleteTask("T002");

        System.out.println("\nAll Tasks after deletion:");
        taskList.traverseTasks();
    }
}
