package main.pl.coderslab.java.database;

import main.pl.coderslab.java.view.ViewTools;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseOperations {
    public static String[][] tasksArray;
    static String filePath = "src/main/pl/coderslab/java/database/tasks.csv";
    //static String filePath = "tasks.csv";

    public static void saveChangesToFile() {
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

    public static void removeUserChoiceFromTaskList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select a number to remove");
        ViewTools.displayCurrentTaskList();
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number: ");
            scanner.nextLine();
        }
        int indexToDelete = scanner.nextInt();
        if (indexToDelete < 1 || indexToDelete > tasksArray.length) {
            System.out.println("Invalid row number. Please enter a number between 1 and " + tasksArray.length);
        } else {
            tasksArray = ArrayUtils.remove(tasksArray, (indexToDelete - 1));
            System.out.println("Task number " + indexToDelete + " deleted successfully.");
        }

    }
}
