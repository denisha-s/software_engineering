//
//import java.util.ArrayList;
//
//public class Customer {
//
//	String name;
//	int age;
//	long bill;
//	ArrayList<Item> listsOfItems;
//
//	Customer(String name, int age) {
//		this.name = name;
//		this.age = age;
//	}
//
//	public long calculateBill(long tax) {
//		for (Item item : listsOfItems) {
//			bill += item.getPrice();
//		}
//		bill += tax;
//		this.setBill(bill);
//		return bill;
//	}
//
//	public void generateReport(String reportType) {
//		if (reportType.equalsIgnoreCase("CSV")) {
//			System.out.println("CSV Report: " + name + "'s bill is " + bill);
//		}
//		if (reportType.equalsIgnoreCase("XML")) {
//			System.out.println("XML Report: " + name + "'s bill is " + bill);
//		}
//
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public long getBill() {
//		return bill;
//	}
//
//	public void setBill(long bill) {
//		this.bill = bill;
//	}
//
//	public ArrayList<Item> getListsOfItems() {
//		return listsOfItems;
//	}
//
//	public void setListsOfItems(ArrayList<Item> listsOfItems) {
//		this.listsOfItems = listsOfItems;
//	}
//
//	public static void main(String[] args) {
//		ArrayList<Item> items = new ArrayList<Item>();
//
//		items.add(new Item(25));
//		items.add(new Item(45));
//
//		Customer fred = new Customer("Fred", 25);
//		fred.setListsOfItems(items);
//		fred.calculateBill(10);
//		fred.generateReport("CSV");
//		
//		Customer karen = new Customer( "Karen", 30 ) ;
//		items.clear() ;
//		items.add(new Item(10));
//		items.add(new Item(10));
//		karen.setListsOfItems(items);
//		karen.calculateBill(20);
//		karen.generateReport("XML");
//		
//
//	}
//
//}



import java.util.ArrayList;

public class Customer {

    private String name;
    private int age;
    private ArrayList<Item> listsOfItems;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
        this.listsOfItems = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        this.listsOfItems.add(item);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Item> getListsOfItems() {
        return listsOfItems;
    }
    
    
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<Item>();

        items.add(new Item(25));
        items.add(new Item(45));

        Customer fred = new Customer("Fred", 25);
        fred.addItem(new Item(25));
        fred.addItem(new Item(45));

        long bill = BillCalculator.calculateBill(fred.getListsOfItems(), 10);
        ReportGenerator.generateReport("CSV", fred, bill);

        Customer karen = new Customer("Karen", 30);
        karen.addItem(new Item(10));
        karen.addItem(new Item(10));

        bill = BillCalculator.calculateBill(karen.getListsOfItems(), 20);
        ReportGenerator.generateReport("XML", karen, bill);
    }
}



