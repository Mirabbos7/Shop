package main;

import entities.Purchase;
import implementation.PurchaseImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PurchaseCLI implements CommandLineRunner {

    private final PurchaseImplementation purchaseService;

    public PurchaseCLI(PurchaseImplementation purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View all purchases");
            System.out.println("2. Create purchase");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> purchaseService.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Enter purchase name: ");
                    String name = scanner.nextLine();

                    Purchase purchase = new Purchase();
                    purchase.setName(name);
                    purchaseService.create(purchase);

                    System.out.println("Purchase created!");
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
