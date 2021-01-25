import task1.BimBam;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

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
                    System.out.println("\n");
                } else if(enteredValue == 3){
                    System.out.println("\n");
                } else if(enteredValue == 0){
                    EXIT = true;
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
