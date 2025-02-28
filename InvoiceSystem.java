import java.sql.*;
import java.util.Scanner;

public class InvoiceSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/invoice_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        do {
            System.out.println("=== Virtual Assistant Invoice System ===");
            System.out.println("1. Client Management");
            System.out.println("2. Service Management");
            System.out.println("3. Invoice Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            System.out.println("DEBUG: User selected option " + choice);
            
            switch (choice) {
                case 1:
                    clientManagement(scanner);
                    break;
                case 2:
                    serviceManagement(scanner);
                    break;
                case 3:
                    invoiceManagement(scanner);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
        
        scanner.close();
    }
    
    private static void clientManagement(Scanner scanner) {
        System.out.println("=== Client Management ===");
        System.out.println("1. Add Client");
        System.out.println("2. View Clients");
        System.out.println("3. Update Client");
        System.out.println("4. Delete Client");
        System.out.print("Enter your choice: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println("Adding a new client...");
                break;
            case 2:
                System.out.println("Viewing clients...");
                break;
            case 3:
                System.out.println("Updating client...");
                break;
            case 4:
                System.out.println("Deleting client...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
        
    
    private static void serviceManagement(Scanner scanner) {
        System.out.println("=== Service Management ===");
        System.out.println("1. Add Service");
        System.out.println("2. View Services");
        System.out.println("3. Update Service");
        System.out.println("4. Delete Service");
        System.out.print("Enter your choice: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println("Adding a new service...");
                break;
            case 2:
                System.out.println("Viewing services...");
                break;
            case 3:
                System.out.println("Updating service...");
                break;
            case 4:
                System.out.println("Deleting service...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
        
    
    private static void invoiceManagement(Scanner scanner) {
        System.out.println("=== Invoice Management ===");
        System.out.println("1. Create New Invoice");
        System.out.println("2. View All Invoices");
        System.out.println("3. View Invoice by Client");
        System.out.print("Enter your choice: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("DEBUG: Invoice management option " + choice);
        
        switch (choice) {
            case 1:
                createInvoice(scanner);
                break;
            case 2:
                viewAllInvoices();
                break;
            case 3:
                viewInvoiceByClient(scanner);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
    
    private static void createInvoice(Scanner scanner) {
        System.out.println("DEBUG: Creating new invoice");
        System.out.println("Select client:");
        System.out.println("1. Tech Solutions Inc.");
        System.out.println("2. Marketing Wizards");
        System.out.println("3. Green Earth Foundation");
        System.out.print("Enter client number: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            return;
        }
        
        int clientNumber = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("DEBUG: Selected client " + clientNumber);
        
        System.out.println("Available services:");
        System.out.println("1. Social Media Management ($25/hr)");
        System.out.println("2. Email Management ($20/hr)");
        
        double subtotal = 0;
        boolean addingServices = true;
        
        while (addingServices) {
            System.out.println("Add services to invoice:");
            System.out.println("1. Add service");
            System.out.println("2. Finish invoice");
            System.out.print("Enter choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            int serviceChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (serviceChoice == 1) {
                System.out.print("Select service number: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid service number.");
                    scanner.next();
                    continue;
                }
                int serviceNumber = scanner.nextInt();
                System.out.print("Enter hours: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    continue;
                }
                int hours = scanner.nextInt();
                
                double rate = (serviceNumber == 1) ? 25 : 20;
                double cost = hours * rate;
                subtotal += cost;
                
                System.out.println("Service added to invoice.");
            } else {
                addingServices = false;
            }
        }
        
        double tax = subtotal * 0.10;
        double total = subtotal + tax;
        
        System.out.println("...Creating Invoice...");
        System.out.println("Invoice - MW-003 (February 2025)");
        String clientName = (clientNumber == 1) ? "Tech Solutions Inc." : (clientNumber == 2) ? "Marketing Wizards" : "Green Earth Foundation";
        System.out.println("Client: " + clientName);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Tax (10%): $" + tax + " (Optional)");
        System.out.println("Total: $" + total);
        System.out.println("Invoice created successfully!");
    }
    
    private static void viewAllInvoices() {
        System.out.println("DEBUG: Viewing all invoices");
    }
    
    private static void viewInvoiceByClient(Scanner scanner) {
        System.out.println("DEBUG: Viewing invoices by client");
    }
}
