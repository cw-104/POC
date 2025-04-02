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

    }

    static void setupUsers() {
        users.put("bob@student.qu.edu", "password123");
        users.put("alice@student.qu.edu", "qbayrocks");
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



}

