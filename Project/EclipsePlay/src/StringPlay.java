import java.awt.Point;

public class StringPlay {
	public static void main(String[] args) {
		System.out.println("C++ is cool");
		System.out.println("I love Java");

		String input = "I Love Java";

		StringBuilder input1 = new StringBuilder();

		input1.append(input);
		input1 = input1.reverse();

		System.out.println("Reverse string: " +input1);

		// test on points
		Point aPoint = new Point(50, 25);
		Point bPoint = new Point(50, 25);
		System.out.println(aPoint == bPoint);
		boolean temp = aPoint.equals(bPoint);
		System.out.println(temp);

		String str1 = "TeSt sTrinG";
		String str2 = "test StRINg";
		String str3 = "bESt kInG";

		boolean result1 = str2.equalsIgnoreCase(str1);
		System.out.println(result1);

		boolean result2 = str3.equalsIgnoreCase(str1);
		System.out.println(result2);

		System.out.println(str1 == str3);
		System.out.println(str1.equals(str3));
	}


}
