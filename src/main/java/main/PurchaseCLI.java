package main;

import entities.Purchase;
import entities.User;
import implementation.PurchaseImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            System.out.println("\n===== Purchase Management CLI =====");
            System.out.println("1. View all purchases");
            System.out.println("2. Create purchase");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> purchaseService.readAll().forEach(System.out::println);
                case 2 -> createPurchase(scanner);
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createPurchase(Scanner scanner) {
        System.out.print("Enter purchase name: ");
        String name = scanner.nextLine();

        System.out.print("Enter purchase price: ");
        int totalPrice = readInt(scanner);

        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();

        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();


        User user = new User();
        user.setName(userName);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());


        Purchase purchase = new Purchase();
        purchase.setName(name);
        purchase.setTotalAmount(totalPrice);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setUser(user);

        purchaseService.create(purchase);

        System.out.println("Purchase created successfully!");
    }

    private int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
