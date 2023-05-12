public class ReportGenerator {

    public static void generateReport(String reportType, Customer customer, long bill) {
        if (reportType.equalsIgnoreCase("CSV")) {
            System.out.println("CSV Report: " + customer.getName() + "'s bill is " + bill);
        }
        if (reportType.equalsIgnoreCase("XML")) {
            System.out.println("XML Report: " + customer.getName() + "'s bill is " + bill);
        }
    }
}
