package main.pl.coderslab.java;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

public class TaskManager {

    static String[][] tasksArray;
    static String filePath = "src/main/pl.coderslab/java/tasks.csv";

    static boolean operating =true;

    public static void main(String[] args) {

        createTaskArrayFromFile();
        while(operating){
        userInputOne();}
    }

    private static void userInputOne() {
        mainOptions();
        //ask user for his choice
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        // String choice = scanner.nextLine();
        String[] validChoices = {"add", "remove", "list", "exit"};
        boolean isUserChoiceValid = false;
        while(!isUserChoiceValid){
//            System.out.println("Please choose an option: ");
//                for(String str : validChoices){
//                    System.out.println(str);
//                }
                choice = scanner.nextLine().toLowerCase();

               for(String validChoice : validChoices){
                   if(choice.equalsIgnoreCase(validChoice)){
                       isUserChoiceValid = true;
                   }
               }
               if(!isUserChoiceValid) {
                    System.out.println("mismatch please type again");
                }
        }
        //check if user typed correct string

        switch (choice){
            case "add":
                System.out.println("add");
                tasksArray = Arrays.copyOf(tasksArray, tasksArray.length+1);
                tasksArray[tasksArray.length-1] = userInputChoiceAdd();
                System.out.println(Arrays.toString(tasksArray[tasksArray.length-1]));

                break;
            case "remove":
                System.out.println("remove");
                removeUserChoiceFromTaskList();
                break;
            case "list":
                System.out.println("list");
                displayCurrentTaskList();
                break;
            case "exit":
                System.out.println("exit");
                saveAndExitProgramm();

                break;
        }
    }

    private static void saveAndExitProgramm() {

        saveChangesToFile();

        displayByeByeMessage();
        operating = false;

    }

    private static void saveChangesToFile() {
            //
        Path path = Paths.get(filePath);

        try{
            StringBuilder sb = new StringBuilder();
            for(String[] row : tasksArray){
                for(int i = 0; i < row.length; i++){
                    sb.append(row[i]);
                    if(i< row.length -1){
                        sb.append(",");
                    }
                }
                sb.append(System.lineSeparator());
            }
            Files.writeString(path, sb.toString());
          //  System.out.println(sb.toString());
        }catch(IOException e){
            System.out.println("Error while writing data" + e);
        }

    }

    private static void displayByeByeMessage() {
        System.out.println("Bye, bye");
    }

    private static void removeUserChoiceFromTaskList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a number to remove");
        displayCurrentTaskList();
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number: ");
            scanner.nextLine();
        }
        int indexToDelete = scanner.nextInt();
        if (indexToDelete < 1 ||indexToDelete > tasksArray.length) {
            System.out.println("Invalid row number. Please enter a number between 1 and " + tasksArray.length);
        }
        else{
            tasksArray = ArrayUtils.remove(tasksArray, (indexToDelete-1));
            System.out.println("Task number " + indexToDelete + " deleted successfully.");
        }

    }

    private static void displayCurrentTaskList() {
        for (int i = 0; i < tasksArray.length; i++){
            System.out.println((i+1) + " : " + tasksArray[i][0] + " " + tasksArray[i][1] + " " + tasksArray[i][2]);
        }
    }

    private static String[] userInputChoiceAdd() {
        String[] newTask = new String[3];
        String[] questions = {"Please add task description", "Please add task due date", "Is your task important: true/false"};
        addNewTaskElement(newTask, 0, questions[0]);

        addNewTaskElement(newTask, 1, questions[1]);

        addNewTaskElement(newTask, 2, questions[2]);

        return  newTask;

    }

    private static void addNewTaskElement(String[] newTask, int x, String title) {
        System.out.println(title);
        Scanner scanner1 = new Scanner(System.in);
        newTask[x] = scanner1.nextLine();
    }

    public static void createTaskArrayFromFile(){
        //reading CSV file
        File file = new File(filePath);
        //creating a List for lines
        List<String> stringList = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
               //populating list with tasksLines
                stringList.add(scan.nextLine());

            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
       tasksArray = populateTaskArray(stringList);

    }

    private static String[][] populateTaskArray(List<String> stringList) {
        int numRows = stringList.size();
        int numCols = 0;
        for (String line : stringList) {
            String[] parts = line.split(",");
            numCols = Math.max(numCols, parts.length);
        }
        String[][] array = new String[numRows][numCols];
        for (int i = 0; i < stringList.size(); i++) {
            String line = stringList.get(i);
            String[] parts = line.split(",");
            for (int j = 0; j < parts.length; j++) {
                array[i][j] = parts[j];
            }
        }
        return array;
    }

    public static void mainOptions(){
        String optionHead = "Please select an option:";
        String[] optionsArray = {"add", "remove", "list", "exit"};
        menuDisplay(optionHead, optionsArray);
    }

    private static void menuDisplay(String optionHead, String[] optionsArray) {
        System.out.println(pl.coderslab.ConsoleColors.BLUE + optionHead + pl.coderslab.ConsoleColors.RESET);

        for(int i = 0; i < optionsArray.length; i++){
            System.out.println(optionsArray[i]);
        }

    }




}







//pierwszy krok to wczytywanie i wyswietlanie danych pliku
//Pierwsza metoda ma wyświetlać dostępne w programie opcje do wykonania.