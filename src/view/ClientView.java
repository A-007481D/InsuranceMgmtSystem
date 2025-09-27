package view;
import util.IDGenerator;

import model.Client;
import controller.ClientController;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ClientView {
    private static final ClientController controller = new ClientController();
    private static final Scanner SC = new Scanner(System.in);


    public static void showClientMenu() {
        while (true) {
            System.out.println("\n--- Clients ---");
            System.out.println("1. Ajouter client");
            System.out.println("2. Supprimer client par ID");
            System.out.println("3. Rechercher client par nom (tri alphabetique)");
            System.out.println("4. Rechercher client par ID (Optional)");
            System.out.println("5. Afficher clients d'un conseiller par ID");
            System.out.println("0. Retour");
            System.out.print("Choix: ");
            String c = SC.nextLine();
            switch (c) {
                case "1": addClient(); break;
                case "2": deleteClient(); break;
                case "3": findByNom(); break;
                case "4": findById(); break;
                case "5": findByConseiller(); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }


    private static void addClient() {
        System.out.print("Nom: "); String nom = SC.nextLine();
        System.out.print("Prenom: "); String prenom = SC.nextLine();
        System.out.print("Email: "); String email = SC.nextLine();
        System.out.print("Conseiller ID: "); Long consId = Long.parseLong(SC.nextLine());
        Client cl = new Client(null, nom, prenom, email, consId);
        controller.create(cl);
        System.out.println("Client cree: " + cl);
    }


    private static void deleteClient() {
        System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
        boolean ok = controller.delete(id);
        System.out.println(ok ? "Supprime" : "Non trouve");
    }


    private static void findByNom() {
        System.out.print("Nom: "); String nom = SC.nextLine();
        List<Client> res = controller.findByNomSorted(nom);
        res.forEach(System.out::println);
    }


    private static void findById() {
        System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
        Optional<Client> c = controller.findById(id);
        if (c.isPresent()) {
            System.out.println(c.get());
        } else {
            System.out.println("Non trouve");
        }
    }


    private static void findByConseiller() {
        System.out.print("Conseiller ID: "); Long id = Long.parseLong(SC.nextLine());
        List<Client> res = controller.findByConseillerId(id);
        res.forEach(System.out::println);
    }
}