package main;

import entities.Purchase;
import entities.User;
import implementation.PurchaseImplementation;
import implementation.UserImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class PurchaseCLI implements CommandLineRunner {

    private final PurchaseImplementation purchaseService;
    private final UserImplementation userService;

    public PurchaseCLI(PurchaseImplementation purchaseService, UserImplementation userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Purchase Management CLI =====");
            System.out.println("1. View all purchases");
            System.out.println("2. Create purchase");
            System.out.println("3. Update purchase");
            System.out.println("4. Read by Id purchase");
            System.out.println("5. Delete purchase");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> purchaseService.readAll().forEach(System.out::println);
                case 2 -> createPurchase(scanner);
                case 3 -> updatePurchase(scanner);
                case 4 -> readById(scanner);
                case 5 -> deleteById(scanner);
                case 6 -> {
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
        userService.create(user);

        Purchase purchase = new Purchase();
        purchase.setName(name);
        purchase.setTotalAmount(totalPrice);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setUser(user);

        purchaseService.create(purchase);

        System.out.println("Purchase created successfully!");
    }

    private void updatePurchase(Scanner scanner) {
        System.out.print("Enter purchase ID to update: ");
        Long id = readLong(scanner);

        Purchase purchase = purchaseService.readById(id).orElse(null);
        if (purchase == null) {
            System.out.println("Purchase not found!");
            return;
        }

        System.out.print("Enter new purchase name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new purchase price: ");
        int totalPrice = readInt(scanner);

        System.out.print("Enter new payment method: ");
        String paymentMethod = scanner.nextLine();

        System.out.println("Enter new user name: ");
        String userName = scanner.nextLine();

        User user = new User();
        user.setName(userName);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userService.create(user);

        purchase.setName(name);
        purchase.setTotalAmount(totalPrice);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setUser(user);

        purchaseService.update(purchase);

        System.out.println("Purchase updated successfully!");
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

    private Long readLong(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private void readById(Scanner scanner){
        System.out.print("Enter purchase ID to get the data: ");
        Long id = readLong(scanner);

        Purchase purchase = purchaseService.readById(id).orElse(null);
        if (purchase == null) {
            System.out.println("Purchase not found!");
        }

        System.out.println(purchase);
    }

    private void deleteById(Scanner scanner){
        System.out.print("Enter purchase ID to delete: ");
        Long id = readLong(scanner);

        boolean purchase = purchaseService.deleteById(id);
        if (!purchase) {
            System.out.println("Purchase not found!");
        }

        System.out.println("purchase with id " + id + " was deleted");
    }
}