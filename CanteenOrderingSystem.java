import java.util.Scanner;

public class CanteenOrderingSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] foodItems = {
            "Burger",
            "Pizza",
            "Pasta",
            "Sandwich",
            "Milk Tea"
        };

        double[] prices = {
            80.00,
            120.00,
            100.00,
            70.00,
            90.00
        };

        int totalQuantity = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;
        double finalAmount = 0.0;

        char orderAgain = 'Y';

        // Display the menu once at the beginning
        System.out.println("===== M E N U =====");

        for (int i = 0; i < foodItems.length; i++) {
            System.out.printf("%d. %-10s - P%.2f%n",
                    i + 1, foodItems[i], prices[i]);
        }

        System.out.println();

        while (orderAgain == 'Y') {
            int itemNumber;
            int quantity;
            char studentStatus;

            System.out.print("Enter item number: ");
            itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            studentStatus = Character.toUpperCase(input.next().charAt(0));

            System.out.println();

            // Validate the order information
            boolean validItem =
                    itemNumber >= 1 && itemNumber <= foodItems.length;

            boolean validQuantity =
                    quantity >= 1 && quantity <= 10;

            boolean validStudentStatus =
                    studentStatus == 'Y' || studentStatus == 'N';

            if (!validItem || !validQuantity || !validStudentStatus) {
                System.out.println(
                    "Invalid order! Please enter a valid item and quantity."
                );
                System.out.println();

                System.out.print("Do you want to order again? (Y/N): ");
                orderAgain =
                        Character.toUpperCase(input.next().charAt(0));

                System.out.println();
                continue;
            }

            double subtotal = prices[itemNumber - 1] * quantity;
            double discountRate = 0.0;

            /*
             * Discount rules:
             * Student below P500        = 10%
             * Non-student P500 or more  = 5%
             * Student P500 or more      = 15%
             */
            if (studentStatus == 'Y' && subtotal >= 500.00) {
                discountRate = 0.15;
            } else if (studentStatus == 'Y') {
                discountRate = 0.10;
            } else if (subtotal >= 500.00) {
                discountRate = 0.05;
            }

            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            System.out.printf("Subtotal: P%.2f%n", subtotal);
            System.out.printf("Discount: P%.2f%n", discount);
            System.out.printf("Order total: P%.2f%n", orderTotal);
            System.out.println();

            // Add only valid orders to the transaction totals
            totalQuantity += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;
            finalAmount += orderTotal;

            System.out.print("Do you want to order again? (Y/N): ");
            orderAgain = Character.toUpperCase(input.next().charAt(0));
            System.out.println();
        }

        // Final transaction summary
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf(
                "Total before discount: P%.2f%n",
                totalBeforeDiscount
        );
        System.out.printf(
                "Total discount: P%.2f%n",
                totalDiscount
        );
        System.out.printf(
                "Final amount: P%.2f%n",
                finalAmount
        );
        System.out.println("Thank you for ordering!");

        input.close();
    }
}
