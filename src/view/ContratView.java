package view;


import controller.ContratController;
import model.Contrat;
import enums.TypeContrat;
import util.IDGenerator;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ContratView {
    private static final ContratController controller = new ContratController();
    private static final Scanner SC = new Scanner(System.in);


    public static void showContratMenu() {
        while (true) {
            System.out.println("\n--- Contrats ---");
            System.out.println("1. Ajouter contrat");
            System.out.println("2. Supprimer contrat par ID");
            System.out.println("3. Afficher contrat par ID (Optional)");
            System.out.println("4. Afficher contrats d'un client");
            System.out.println("0. Retour");
            System.out.print("Choix: ");
            String c = SC.nextLine();
            switch (c) {
                case "1": addContrat(); break;
                case "2": deleteContrat(); break;
                case "3": findContratById(); break;
                case "4": findContratsByClient(); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }

    private static void addContrat() {
        System.out.print("Type (AUTOMOBILE/MAISON/MALADIE): "); TypeContrat type = TypeContrat.valueOf(SC.nextLine().toUpperCase());
        System.out.print("Date Debut (YYYY-MM-DD): "); LocalDate debut = LocalDate.parse(SC.nextLine());
        System.out.print("Date Fin (YYYY-MM-DD): "); LocalDate fin = LocalDate.parse(SC.nextLine());
        System.out.print("Client ID: "); Long clientId = Long.parseLong(SC.nextLine());
        Contrat co = new Contrat(IDGenerator.next(), type, debut, fin, clientId);
        controller.create(co);
        System.out.println("Contrat cree: " + co);
    }


    private static void deleteContrat() {
        System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
        boolean ok = controller.delete(id);
        System.out.println(ok ? "Supprime" : "Non trouve");
    }


    private static void findContratById() {
        System.out.print("ID: "); Long id = Long.parseLong(SC.nextLine());
        Optional<Contrat> c = controller.findById(id);
        if (c.isPresent()) {
            System.out.println(c.get());
        } else {
            System.out.println("Non trouve");
        }

    }


    private static void findContratsByClient() {
        System.out.print("Client ID: "); Long id = Long.parseLong(SC.nextLine());
        List<Contrat> res = controller.findByClientId(id);
        res.forEach(System.out::println);
    }
}