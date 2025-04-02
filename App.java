import java.util.*; 

public class App{
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static Map<String, List<Item>> itemsForSale = new HashMap<>();
    static List<Item> itemList = new ArrayList<>();
    static List<Item> cart = new ArrayList<>();
    static String loggedInUser = null;

    public static void main(String[] args) {

        setupUsers();
        setupSampleItems();
        showWelcome();
        login();
        mainMenu();

    }

    static void setupUsers() {
        users.put("bob@student.qu.edu", "password123");
        users.put("alice@student.qu.edu", "qurocks");
    }

    static void setupSampleItems() {
        itemList.add(new Item("Textbook", "Books", "Bob", "Smith", "bob@student.qu.edu", 50));
        itemList.add(new Item("Mini Fridge", "Appliances", "Alice", "Jones", "alice@student.qu.edu", 75));
        itemList.add(new Item("Desk Lamp", "Electronics", "Bob", "Smith", "bob@student.qu.edu", 20));
        itemList.add(new Item("Sweatshirt", "Clothing", "Alice", "Jones", "alice@student.qu.edu", 15));
        itemList.add(new Item("Backpack", "Accessories", "Bob", "Smith", "bob@student.qu.edu", 30));
    }

    static void showWelcome() {
        System.out.println(" _    _      _                          ");
        System.out.println("| |  | |    | |                         ");
        System.out.println("| |  | | ___| | ___ ___  _ __ ___   ___ ");
      
        System.out.println("\\  /\\  /  __/ | (_| (_) | | | | | |  __/");
        System.out.println(" \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
        System.out.println();
    }

    static void login() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter Quinnipiac email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (users.containsKey(email) && users.get(email).equals(password)) {
                loggedInUser = email;
                System.out.println("Login successful. Welcome, " + email + "!");
                return;
            } else {
                System.out.println("Incorrect email or password. Try again.");
                attempts++;
            }
        }
        System.out.println("Account locked due to suspicious activity.");
        System.exit(0);
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\nMain Menu: buy | sell | cart | logout");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "buy":
                    buyMenu();
                    break;
                case "sell":
                    sellMenu();
                    break;
                case "cart":
                    cartMenu();
                    break;
                case "logout":
                    System.out.println("Thank you for using the QU Store. You are now logged out.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void buyMenu() {
        while (true) {
            System.out.println("\nItems for Sale:");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i).name);
            }
            System.out.print("Select item number to view/add to cart or type 'back': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("back")) break;
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < itemList.size()) {
                    Item item = itemList.get(index);
                    System.out.println(item);
                    System.out.print("Add to cart? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        cart.add(item);
                        System.out.println("Item added to cart.");
                    }
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    static void sellMenu() {
        while (true) {
            System.out.println("\nSell Menu: view | add | back");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("view")) {
                for (Item item : itemList) {
                    if (item.sellerEmail.equals(loggedInUser)) {
                        System.out.println(item.name + " | " + item.category + " | $" + item.price);
                    }
                }
            } else if (choice.equals("add")) {
                System.out.print("Item name: ");
                String name = scanner.nextLine();
                System.out.print("Category: ");
                String category = scanner.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(scanner.nextLine());
                String[] nameParts = loggedInUser.split("@")[0].split("\\.");
                String firstName = Character.toUpperCase(nameParts[0].charAt(0)) + nameParts[0].substring(1);
                String lastName = "Student";
                Item newItem = new Item(name, category, firstName, lastName, loggedInUser, price);
                itemList.add(newItem);
                System.out.println("Item added.");
            } else if (choice.equals("back")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    static void cartMenu() {
        double total = 0;
        System.out.println("\nCart:");
        for (Item item : cart) {
            System.out.println(item.name + " - $" + item.price);
            total += item.price;
        }
        System.out.println("Total: $" + total);
        System.out.print("Checkout? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            for (Item item : cart) {
                itemList.remove(item);
            }
            cart.clear();
            System.out.println("Purchase complete. Cart cleared.");
        }
    }


}

