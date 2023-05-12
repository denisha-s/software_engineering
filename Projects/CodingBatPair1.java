//Authors: Patrick Maes and Denisha Saviela
import java.lang.Math; 
 
public class CodingBatPair1 {
	// Authors: Patrick Maes and Denisha Saviela
	 // Problem: evenlySpaced
	public static boolean evenlySpaced(int a, int b, int c) {
		int maximum = Math.max(a, Math.max(b, c));
		int minimum = Math.min(a, Math.min(b, c));
		int mid = 0;
		if(a != maximum && a != minimum) {
		  mid = a;
		}
		if(b != maximum && b != minimum) {
		  mid = b;
		}
		if(c != maximum && c != minimum) {
		  mid = c;
		}
		if(a == b && b == c) {
		  mid = a;
		}
		int dist1 = maximum - mid;
		int dist2 = mid - minimum;
		
		if(dist1 == dist2) {
		  return true;
		} else {
		  return false;
		}
	}
	/*
			 * Expected	Run		
		evenlySpaced(2, 4, 6) → true	true	OK	
		evenlySpaced(4, 6, 2) → true	true	OK	
		evenlySpaced(4, 6, 3) → false	false	OK	
		evenlySpaced(6, 2, 4) → true	true	OK	
		evenlySpaced(6, 2, 8) → false	false	OK	
		evenlySpaced(2, 2, 2) → true	true	OK	
		evenlySpaced(2, 2, 3) → false	false	OK	
		evenlySpaced(9, 10, 11) → true	true	OK	
		evenlySpaced(10, 9, 11) → true	true	OK	
		evenlySpaced(10, 9, 9) → false	false	OK	
		evenlySpaced(2, 4, 4) → false	false	OK	
		evenlySpaced(2, 2, 4) → false	false	OK	
		evenlySpaced(3, 6, 12) → false	false	OK	
		evenlySpaced(12, 3, 6) → false	false	OK	
		other tests
		OK	

	 */
	
	
	 // Authors: Patrick Maes and Denisha Saviela
	 // Problem: plusOut 
	public static String plusOut(String str, String word) {
		int slength = str.length();
		int wlength = word.length();
		int difference = slength - wlength;
		String updated = "";
		for(int i = 0; i < slength; i++) {
			if(i <= difference) {
				String sub = str.substring(i, i+wlength);
				if(sub.equals(word)) {
					updated += word;
					i += wlength -1;
				} else {
					updated += '+';
				}
			} else {
				updated += '+';
			}
		}
		return updated;
	}
	/*
			 * Expected	Run		
		plusOut("12xy34", "xy") → "++xy++"	"++xy++"	OK	
		plusOut("12xy34", "1") → "1+++++"	"1+++++"	OK	
		plusOut("12xy34xyabcxy", "xy") → "++xy++xy+++xy"	"++xy++xy+++xy"	OK	
		plusOut("abXYabcXYZ", "ab") → "ab++ab++++"	"ab++ab++++"	OK	
		plusOut("abXYabcXYZ", "abc") → "++++abc+++"	"++++abc+++"	OK	
		plusOut("abXYabcXYZ", "XY") → "++XY+++XY+"	"++XY+++XY+"	OK	
		plusOut("abXYxyzXYZ", "XYZ") → "+++++++XYZ"	"+++++++XYZ"	OK	
		plusOut("--++ab", "++") → "++++++"	"++++++"	OK	
		plusOut("aaxxxxbb", "xx") → "++xxxx++"	"++xxxx++"	OK	
		plusOut("123123", "3") → "++3++3"	"++3++3"	OK	
		other tests
		OK	
	 */
	
	// Authors: Patrick Maes and Denisha Saviela
	// Problem: countClumps 
	public static int countClumps(int[] nums) {
		boolean match = false;
		int count = 0;
		for(int i = 0; i < nums.length-1; i++) {
			if(nums[i] == nums[i+1] && !match) {
				match = true;
				count++;
			} else if(nums[i] != nums[i+1]) {
				match = false;
			}
		}
		return count;
	}
	/*
			 * Expected	Run		
		countClumps([1, 2, 2, 3, 4, 4]) → 2	2	OK	
		countClumps([1, 1, 2, 1, 1]) → 2	2	OK	
		countClumps([1, 1, 1, 1, 1]) → 1	1	OK	
		countClumps([1, 2, 3]) → 0	0	OK	
		countClumps([2, 2, 1, 1, 1, 2, 1, 1, 2, 2]) → 4	4	OK	
		countClumps([0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]) → 4	4	OK	
		countClumps([0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]) → 5	5	OK	
		countClumps([0, 0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2]) → 5	5	OK	
		countClumps([]) → 0	0	OK	
		other tests
		OK	

	 */
	
	//Authors: Patrick Maes and Denisha Saviela
	// Problem: fix34
	public int[] fix34(int[] nums) {
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 3) {
				int temp = nums[i + 1];
				nums[i+1] = 4;
				for(int j = i + 2; j < nums.length; j++) {
					if(nums[j] ==4) {
						nums[j] = temp;
					}
				}
			}
		}
		return nums;
	}
	/*
			 * Expected	Run		
		fix34([1, 3, 1, 4]) → [1, 3, 4, 1]	[1, 3, 4, 1]	OK	
		fix34([1, 3, 1, 4, 4, 3, 1]) → [1, 3, 4, 1, 1, 3, 4]	[1, 3, 4, 1, 1, 3, 4]	OK	
		fix34([3, 2, 2, 4]) → [3, 4, 2, 2]	[3, 4, 2, 2]	OK	
		fix34([3, 2, 3, 2, 4, 4]) → [3, 4, 3, 4, 2, 2]	[3, 4, 3, 4, 2, 2]	OK	
		fix34([2, 3, 2, 3, 2, 4, 4]) → [2, 3, 4, 3, 4, 2, 2]	[2, 3, 4, 3, 4, 2, 2]	OK	
		fix34([5, 3, 5, 4, 5, 4, 5, 4, 3, 5, 3, 5]) → [5, 3, 4, 5, 5, 5, 5, 5, 3, 4, 3, 4]	[5, 3, 4, 5, 5, 5, 5, 5, 3, 4, 3, 4]	OK	
		fix34([3, 1, 4]) → [3, 4, 1]	[3, 4, 1]	OK	
		fix34([3, 4, 1]) → [3, 4, 1]	[3, 4, 1]	OK	
		fix34([1, 1, 1]) → [1, 1, 1]	[1, 1, 1]	OK	
		fix34([1]) → [1]	[1]	OK	
		fix34([]) → []	[]	OK	
		fix34([7, 3, 7, 7, 4]) → [7, 3, 4, 7, 7]	[7, 3, 4, 7, 7]	OK	
		fix34([3, 1, 4, 3, 1, 4]) → [3, 4, 1, 3, 4, 1]	[3, 4, 1, 3, 4, 1]	OK	
		fix34([3, 1, 1, 3, 4, 4]) → [3, 4, 1, 3, 4, 1]	[3, 4, 1, 3, 4, 1]	OK	
		other tests
		OK	
	 */




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(evenlySpaced(2,2,5));
		System.out.println(plusOut("12xy34", "xy"));
		
	}

}
