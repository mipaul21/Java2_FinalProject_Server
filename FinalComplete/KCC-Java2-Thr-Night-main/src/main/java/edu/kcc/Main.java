/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc;

import edu.kcc.ui.UIUtility;
import edu.kcc.animal.Animal;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author k0519415
 * @author Nathaniel Webber
 */
public class Main {

    /**
     * @param args the command line arguments
     * 
     */
    private static final int PORT = 5555;
    private static final String HOST_NAME = "localhost";
  
  private static String getAnimalFromServer(String response) throws UnknownHostException, IOException {
        Socket socket = new Socket(HOST_NAME, PORT);
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF(response);
        outputStream.flush();
        String animalInfo = inputStream.readUTF();
        System.out.println(animalInfo);
        inputStream.close();
        outputStream.close();
        return animalInfo;
    }
  
  	
    private static String getUserInput(String prompt){
        System.out.print(prompt + " ");
        return new Scanner(System.in).nextLine();
    }
    
    
    public static void main(String[] args) throws IOException {
        
        UIUtility.showMessage("Program starting...");
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Find An Animal",
            "2) Show lookup history",
            "3) Exit"
        };
        String prompt = "Your choice:";
        String errorMessage = "Invalid option.  Please try again.";
        String userChoice;
        // AnimalDAO dao = AnimalDAO.getAnimalDAO();
        
        boolean working = true;
        while(working) {
            userChoice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions);
            switch(userChoice) {
                case "1":
                    String response;
                    String promptName = "Enter the name of the animal:";

                    // Find an Animal
                    System.out.println("Please enter the name of the animal you would like to see the details on.");
                    response = getUserInput(promptName);
                    getAnimalFromServer(response);
                    
                case "2":
                    // Lookup history
                    

                    break;
                case "3":
                    working = false;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);  
            }
        }
        UIUtility.showMessage("\nProgram complete.");
        
        
    }

    

}
