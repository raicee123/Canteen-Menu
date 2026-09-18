import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] itemNames = {"Fries Overload", "Pancit", "Roasted Chiken", "Beefsandwich", "Coca Cola"};
        double[] itemPrices = {75.00, 90.00, 150.00, 70.00, 35.00};

        int totalQuantity = 0;
        double totalBeforeDeductions = 0.0;
        double totalDeduction = 0.0;
        double finalAmountToPay = 0.0;

        System.out.println("=====  M E N U  =====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }

        char orderAgain = 'Y';

        do {
            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = input.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();

            System.out.print("Are you a student? (Y/N): ");
            char studentAnswer = input.next().charAt(0);
            boolean isStudent = (studentAnswer == 'Y' || studentAnswer == 'y');

            boolean isValidItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean isValidQuantity = (quantity >= 1 && quantity <= 10);

            if (!isValidItem || !isValidQuantity) {
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");
            } else {
                double pricePerItem = itemPrices[itemNumber - 1];
                double subtotal = pricePerItem * quantity;

                double discountRate;
                if (isStudent && subtotal >= 500.0) {
                    discountRate = 0.15;
                } else if (subtotal >= 500.0) {
                    discountRate = 0.05;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else {
                    discountRate = 0.0;
                }

                double discountAmount = subtotal * discountRate;
                double orderTotal = subtotal - discountAmount;

                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discountAmount);
                System.out.printf("Order total: $%.2f%n", orderTotal);

                totalQuantity += quantity;
                totalBeforeDeductions += subtotal;
                totalDeduction += discountAmount;
                finalAmountToPay += orderTotal;
            }

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            orderAgain = input.next().charAt(0);

        } while (orderAgain == 'Y' || orderAgain == 'y');

        System.out.println();
        System.out.println("=====  ORDER SUMMARY  =====");
        System.out.println("Total quantity purchased: " + totalQuantity);
        System.out.printf("Total amount before deductions: $%.2f%n", totalBeforeDeductions);
        System.out.printf("Total deduction: $%.2f%n", totalDeduction);
        System.out.printf("Final amount to pay: $%.2f%n", finalAmountToPay);

        input.close();
    }
}