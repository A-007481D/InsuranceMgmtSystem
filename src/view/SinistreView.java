package view;

import controller.SinistreController;
import model.Sinistre;
import util.IDGenerator;
import controller.ClientController;
import enums.TypeSinistre;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class SinistreView {
    private static final SinistreController controller = new SinistreController();
    private static final Scanner SC = new Scanner(System.in);

    public static void showSinistreMenu() {
        while (true) {
            System.out.println("\n--- Menu Sinistre ---");
            System.out.println("1. Ajouter");
            System.out.println("2. Supprimer");
            System.out.println("3. Rechercher");
            System.out.println("4. Lister");
            System.out.println("5. Lister par Contrat");
            System.out.println("6. Lister par Client");
            System.out.println("7. Lister triés par coût");
            System.out.println("8. Total coût par Client");
            System.out.println("9. Lister avant une Date");
            System.out.println("10. Lister par coût > valeur");
            System.out.println("0. Retour");
            System.out.print("Choix: ");

            String choix = SC.nextLine();
            switch (choix) {
                case "1": add(); break;
                case "2": delete(); break;
                case "3": find(); break;
                case "4": list(); break;
                case "5": listByContrat(); break;
                case "6": listByClient(); break;
                case "7": listSortedByCout(); break;
                case "8": totalCoutByClient(); break;
                case "9": listBeforeDate(); break;
                case "10": listByCoutGreater(); break;
                case "0": return;
                default: System.out.println("Choix invalide");
            }
        }
    }

    private static void add() {
        System.out.print("Description: ");
        String desc = SC.nextLine();
        System.out.print("Coût: ");
        double cout = Double.parseDouble(SC.nextLine());
        System.out.print("Date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(SC.nextLine());
        System.out.print("Type de sinistre (ACCIDENT_VOITURE, ACCIDENT_MAISON, MALADIE): ");
        TypeSinistre type = TypeSinistre.valueOf(SC.nextLine().toUpperCase());
        System.out.print("Contrat ID: ");
        Long contratId = Long.parseLong(SC.nextLine());

        Sinistre s = new Sinistre(null, type, date.atStartOfDay(), cout, desc, contratId);
        controller.create(s);
        System.out.println("Sinistre créé: " + s);
    }

    private static void delete() {
        System.out.print("ID: ");
        Long id = Long.parseLong(SC.nextLine());
        boolean ok = controller.delete(id);
        System.out.println(ok ? "Supprimé" : "Non trouvé");
    }

    private static void find() {
        System.out.print("ID: ");
        Long id = Long.parseLong(SC.nextLine());
        Optional<Sinistre> s = controller.findById(id);

        if (s.isPresent()) {
            System.out.println(s.get());
        } else {
            System.out.println("Non trouvé");
        }
    }

    private static void list() {
        List<Sinistre> all = controller.findAll();
        all.forEach(System.out::println);
    }

    private static void listByContrat() {
        System.out.print("Contrat ID: ");
        Long id = Long.parseLong(SC.nextLine());
        List<Sinistre> res = controller.findByContratId(id);
        res.forEach(System.out::println);
    }

    private static void listByClient() {
        System.out.print("Client ID: ");
        Long id = Long.parseLong(SC.nextLine());
        List<Sinistre> res = controller.findByClientId(id);
        res.forEach(System.out::println);
    }

    private static void listSortedByCout() {
        List<Sinistre> res = controller.findSortedByCout();
        res.forEach(System.out::println);
    }

    private static void totalCoutByClient() {
        System.out.print("Client ID: ");
        Long id = Long.parseLong(SC.nextLine());
        double total = controller.totalCoutByClient(id);
        System.out.println("Total coût: " + total);
    }

    private static void listBeforeDate() {
        System.out.print("Date limite (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(SC.nextLine());
        List<Sinistre> res = controller.findBeforeDate(date);
        res.forEach(System.out::println);
    }

    private static void listByCoutGreater() {
        System.out.print("Valeur min: ");
        double val = Double.parseDouble(SC.nextLine());
        List<Sinistre> res = controller.findByCoutGreaterThan(val);
        res.forEach(System.out::println);
    }
}
