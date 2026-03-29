import java.util.*;

// Menu Item Class
class MenuItem {
    int id;
    String name;
    double price;

    MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

// Order Item Class
class OrderItem {
    MenuItem item;
    int quantity;

    OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

// Main Class (IMPORTANT)
public class RestaurantBillingSystem {

    static ArrayList<MenuItem> menu = new ArrayList<>();
    static ArrayList<OrderItem> order = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add Menu Item
    static void addMenuItem() {
        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        menu.add(new MenuItem(id, name, price));
        System.out.println("✅ Item Added!");
    }

    // Display Menu
    static void displayMenu() {
        System.out.println("\n--- MENU ---");
        for (MenuItem m : menu) {
            System.out.println(m.id + ". " + m.name + " - ₹" + m.price);
        }
    }

    // Add Order
    static void addOrder() {
        displayMenu();

        System.out.print("Enter Item ID: ");
        int id = sc.nextInt();

        MenuItem selected = null;

        for (MenuItem m : menu) {
            if (m.id == id) {
                selected = m;
                break;
            }
        }

        if (selected == null) {
            System.out.println("❌ Item not found!");
            return;
        }

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        order.add(new OrderItem(selected, qty));
        System.out.println("✅ Added to order!");
    }

    // Remove Order
    static void removeOrder() {
        System.out.print("Enter Item ID to remove: ");
        int id = sc.nextInt();

        boolean removed = order.removeIf(o -> o.item.id == id);

        if (removed)
            System.out.println("✅ Item removed!");
        else
            System.out.println("❌ Item not found in order!");
    }

    // Generate Bill
    static void generateBill() {
        double total = 0;

        System.out.println("\n======= BILL =======");

        for (OrderItem o : order) {
            double itemTotal = o.getTotal();
            total += itemTotal;

            System.out.println(o.item.name + " x " + o.quantity + " = ₹" + itemTotal);
        }

        double gst = total * 0.18;
        double finalAmount = total + gst;

        System.out.println("-------------------");
        System.out.println("Subtotal: ₹" + total);
        System.out.println("GST (18%): ₹" + gst);
        System.out.println("Total: ₹" + finalAmount);
        System.out.println("===================");
    }

    // MAIN METHOD (ENTRY POINT)
    public static void main(String[] args) {

        // Default Menu
        menu.add(new MenuItem(1, "Pizza", 200));
        menu.add(new MenuItem(2, "Burger", 100));
        menu.add(new MenuItem(3, "Pasta", 150));

        while (true) {
            System.out.println("\n===== RESTAURANT SYSTEM =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Menu");
            System.out.println("3. Add Order");
            System.out.println("4. Remove Order");
            System.out.println("5. Generate Bill");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addMenuItem(); break;
                case 2: displayMenu(); break;
                case 3: addOrder(); break;
                case 4: removeOrder(); break;
                case 5: generateBill(); break;
                case 6:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}