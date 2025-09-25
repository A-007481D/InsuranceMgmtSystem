package view;


import java.util.Scanner;
import static view.ClientView.*;
import static view.ContratView.*;
import static view.SinistreView.*;
import static view.ConseillerView.*;


public class MainView {
    private static final Scanner SC = new Scanner(System.in);


    public static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Assurance Console App ===");
            System.out.println("1. Gerer Conseillers");
            System.out.println("2. Gerer Clients");
            System.out.println("3. Gerer Contrats");
            System.out.println("4. Gerer Sinistres");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");
            String choice = SC.nextLine();
            switch (choice) {
//                case "1": showConseillerMenu(); break;
//                case "2": showClientMenu(); break;
//                case "3": showContratMenu(); break;
//                case "4": showSinistreMenu(); break;
                case "0": System.out.println("Au revoir"); return;
                default: System.out.println("Choix invalide");
            }
        }
    }
}