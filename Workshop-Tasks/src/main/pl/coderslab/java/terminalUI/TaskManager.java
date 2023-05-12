package main.pl.coderslab.java.terminalUI;


import main.pl.coderslab.java.database.DatabaseOperations;
import main.pl.coderslab.java.database.MenuChoices;
import main.pl.coderslab.java.view.ViewTools;

import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {

        while (true) {
            run();
        }
    }

    public static void run() {

        DatabaseOperations.createTaskArrayFromFile();

        while (true) {
            ViewTools.mainOptions();

            Scanner scanner = new Scanner(System.in);
            String choice = "";
            choice = scanner.nextLine().toLowerCase();

            if (MenuChoices.isValidChoice(choice)) {
                MenuChoices menuChoice = MenuChoices.valueOf(choice.toUpperCase());
                switch (choice) {
                    case "add":
                        add();
                        break;
                    case "remove":
                        remove();
                        break;
                    case "list":
                        list();
                        break;
                    case "exit":
                        exit();
                        break;
                }

            } else {
                System.out.println("Please type the correct option. Try again");
            }
        }
    }


    public static void add() {
        System.out.println("add");
        addToDatabase();
    }


    public static void remove() {
        System.out.println("remove");
        DatabaseOperations.removeUserChoiceFromTaskList();

    }


    public static void list() {
        System.out.println("list");
        ViewTools.displayCurrentTaskList();
    }


    public static void exit() {
        System.out.println("exit");
        saveAndExitProgramm();
    }


    private static void addToDatabase() {
        DatabaseOperations.tasksArray = Arrays.copyOf(DatabaseOperations.tasksArray, DatabaseOperations.tasksArray.length + 1);
        DatabaseOperations.tasksArray[DatabaseOperations.tasksArray.length - 1] = userInputChoiceAdd();
        ViewTools.displayAddedTask();
    }

    private static void saveAndExitProgramm() {
        DatabaseOperations.saveChangesToFile();
        ViewTools.displayByeByeMessage();
        System.exit(0);
    }

    private static String[] userInputChoiceAdd() {
        String[] newTask = new String[3];
        String[] questions = {"Please add task description", "Please add task due date", "Is your task important: true/false"};
        addNewTaskElement(newTask, 0, questions[0]);
        addNewTaskElement(newTask, 1, questions[1]);
        addNewTaskElement(newTask, 2, questions[2]);
        return newTask;
    }

    private static void addNewTaskElement(String[] newTask, int x, String title) {
        System.out.println(title);
        Scanner scanner1 = new Scanner(System.in);
        newTask[x] = scanner1.nextLine();
    }

}
