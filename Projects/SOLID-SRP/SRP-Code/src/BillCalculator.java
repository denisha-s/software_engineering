import java.util.ArrayList;

public class BillCalculator {

    public static long calculateBill(ArrayList<Item> items, long tax) {
        long bill = 0;
        for (Item item : items) {
            bill += item.getPrice();
        }
        return bill + tax;
    }
}

