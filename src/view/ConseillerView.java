package view;

import controller.ConseillerController;
import model.Conseiller;
import util.IDGenerator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConseillerView {
    private static final ConseillerController controller = new ConseillerController();
    private static final Scanner SC = new Scanner(System.in);

    public static void showConseillerMenu() {
        while (true) {
            System.out.println("\n--- Conseillers ---");
            System.out.println("1. Ajouter conseiller");
            System.out.println("2. Supprimer conseiller par ID");
            System.out.println("3. Rechercher conseiller par ID");
            System.out.println("4. Lister tous les conseillers");
            System.out.println("0. Retour");
            System.out.print("Choix: ");
            String c = SC.nextLine();
            switch (c) {
                case "1": addConseiller(); break;
                case "2": deleteConseiller(); break;
                case "3": findConseiller(); break;
                case "4": listConseillers(); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }

    private static void addConseiller() {
        System.out.print("Nom: "); String nom = SC.nextLine();
        System.out.print("Prenom: "); String prenom = SC.nextLine();
        System.out.print("Email: "); String email = SC.nextLine();
        Conseiller c = new Conseiller(null, nom, prenom, email);
        controller.create(c);
        System.out.println("Conseiller cree: " + c);
    }

    private static void deleteConseiller() {
        try {
            System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
            boolean ok = controller.delete(id);
            System.out.println(ok ? "Supprime" : "Non trouve");
        } catch (NumberFormatException e) {
            System.out.println("ID invalide");
        }
    }

    private static void findConseiller() {
        try {
            System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
            Optional<Conseiller> c = controller.findById(id);
            System.out.println(c.map(Object::toString).orElse("Non trouve"));
        } catch (NumberFormatException e) {
            System.out.println("ID invalide");
        }
    }

    private static void listConseillers() {
        List<Conseiller> all = controller.findAll();
        all.forEach(System.out::println);
    }
}
