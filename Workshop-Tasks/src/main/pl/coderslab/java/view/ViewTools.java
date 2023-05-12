package main.pl.coderslab.java.view;

import main.pl.coderslab.java.database.DatabaseOperations;

import java.util.Arrays;

public class ViewTools {
    public static void displayByeByeMessage() {
        System.out.println(ConsoleColors.RED + "Bye, bye");
    }

    public static void displayCurrentTaskList() {
        for (int i = 0; i < DatabaseOperations.tasksArray.length; i++) {
            System.out.println((i + 1) + " : " + DatabaseOperations.tasksArray[i][0] + " " + DatabaseOperations.tasksArray[i][1] + " " + DatabaseOperations.tasksArray[i][2]);
        }
    }

    public static void mainOptions() {
        String optionHead = "Please select an option:";
        String[] optionsArray = {"add", "remove", "list", "exit"};
        menuDisplay(optionHead, optionsArray);
    }

    private static void menuDisplay(String optionHead, String[] optionsArray) {
        System.out.println(ConsoleColors.BLUE + optionHead + ConsoleColors.RESET);

        for (int i = 0; i < optionsArray.length; i++) {
            System.out.println(optionsArray[i]);
        }

    }

    public static void displayAddedTask() {
        System.out.println(Arrays.toString(DatabaseOperations.tasksArray[DatabaseOperations.tasksArray.length - 1]));
    }
}
