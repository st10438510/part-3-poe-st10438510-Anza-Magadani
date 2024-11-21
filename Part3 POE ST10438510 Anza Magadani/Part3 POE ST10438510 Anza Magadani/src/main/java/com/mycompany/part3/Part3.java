/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part3;

import com.mycompany.part3.Login;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Part3 {

     private static ArrayList<Task> tasks = new ArrayList<>();
 

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        if (loginUser(scanner)) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
            runMenu();
        }
    }

 

    private static void runMenu() {
        String[] options = {"1. Add Tasks", "2. Show Report", "3. Search Task", "4. Delete Task", "5. Quit"};
        boolean running = true;

        while (running) {
            String choice = (String) JOptionPane.showInputDialog(null, "Choose an option:", "Menu",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case "1. Add Tasks":
                    addTasks();
                    break;
                case "2. Show Report":
                    displayTaskReport();
                    break;
                case "3. Search Task":
                    searchTask();
                    break;
                case "4. Delete Task":
                    deleteTask();
                    break;
                case "5. Quit":
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Try again.");
            }
        }
    }
    private static boolean loginUser(Scanner scanner) {
        
   System.out.println("Register a new account:");
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        
         Login login = new Login(username, password, firstName, lastName);
        String registrationResult = login.registerUser();
        System.out.println(registrationResult);
       Boolean  LoginStatus=false; 
    
        if (registrationResult.equals("The user has been registered successfully")) {
            System.out.println("\nLogin to your account:");
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
           System.out.println(loginStatus);
             
               LoginStatus = login.loginUser(username, password);
               
        }
     return LoginStatus;
 
        
    
}
    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you want to add?"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog("Enter task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");

            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
            }

            String developerName = JOptionPane.showInputDialog("Enter developer name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
            String[] statusOptions = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

           
            Task task = new Task(taskName, taskDescription, developerName, taskDuration, taskStatus, tasks.size());

     
            tasks.add(task);

         
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

      
        JOptionPane.showMessageDialog(null, "Total task duration across all tasks: " + Task.returnTotalHours() + " hours");
    }

    private static void searchTask() {
        String taskName = JOptionPane.showInputDialog("Enter task name to search:");
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails());
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    private static void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter task name to delete:");
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskName)) {
                tasks.remove(i);  
                JOptionPane.showMessageDialog(null, "Task " + taskName + " deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    private static void displayTaskReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");

        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }

   
}