import task1.BimBam;
import task2.CustomMapImpl;
import task3.SplitXML;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static void HashMapTask(){
        CustomMapImpl myMap = new CustomMapImpl();
        System.out.println("\nMap size after init: " + myMap.size());

        myMap.put(1,2);
        System.out.println("Map size after myMap.put(1,2): " + myMap.size());

        System.out.println("Map return after myMap.get(1): " + myMap.get(1));

        System.out.println("Map return after myMap.remove(1): " + myMap.remove(1));
        System.out.println("Map size after myMap.remove(1): " + myMap.size());

    }

    public static void main(String[] args){
        boolean EXIT = false;
        Scanner input = new Scanner(System.in);

        while(!EXIT){
            int enteredValue;
            System.out.println("Welcome to the Verifone task list!\n" +
                    "Please select from the list below by entering the number on the left. To exit type 0:\n" +
                    "1. BimBam\n2. HashMap\n3. XML\n");
            System.out.print("Your Entry: ");
            try {
                enteredValue = input.nextInt();

                if(enteredValue == 1){
                    BimBam bimBam = new BimBam();
                    bimBam.BimBam();
                    System.out.println("\n");
                } else if(enteredValue == 2){
                    HashMapTask();
                    System.out.println("\nThe CustomMapImpl tests cover more.\n");
                } else if(enteredValue == 3){
                    try {
                        int numRecords = 1;
                        boolean CORRECT = false;
                        while(!CORRECT) {
                            System.out.print("Enter max number of records to exist (between 1 and 30): ");
                             numRecords = input.nextInt();
                            if (numRecords < 1 || numRecords > 30) {
                                System.out.println("Entry must be between 1 and 30");
                            } else{
                                CORRECT = true;
                            }
                        }

                        System.out.print("Enter max size per file in bytes (recommended at least 3000): ");
                        long maxFileSize = input.nextLong();
                        input.nextLine(); //consume newline character

                        System.out.println("Current directory path: " + System.getProperty("user.dir"));
                        System.out.print("Enter path to save files to: ");
                        String directoryPath = input.nextLine();

                        SplitXML.splitting(numRecords, maxFileSize, directoryPath);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\n");
                } else if(enteredValue == 0){
                    EXIT = true;
                    input.close();
                } else{
                    System.out.println("This " + "'" + enteredValue + "'" + " isn't an option");
                }
            } catch (InputMismatchException ime){
                System.out.println("\nWe don't accept characters here!\n");
                input.nextLine();
            }
        }
    }
}
