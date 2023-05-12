// Author: Denisha Saviela
// tenRun
public class CodingBatIndivdual {
	public int[] tenRun(int[] nums) {
		// Loop over all the elements in the values array
		for (int i=0; i<nums.length-1; i++){
			// using modulus operator
			if (nums[i] % 10 == 0){ 
				// element gets updated
				if (nums[i + 1] % 10 != 0) {
					nums[i + 1] = nums[i];
				}
			}
		}
		return nums;
	}
/*
Expected This Run
tenRun([2, 10, 3, 4, 20, 5]) → [2, 10, 10, 10, 20, 20]	[2, 10, 10, 10, 20, 20]	OK	
tenRun([10, 1, 20, 2]) → [10, 10, 20, 20]	[10, 10, 20, 20]	OK	
tenRun([10, 1, 9, 20]) → [10, 10, 10, 20]	[10, 10, 10, 20]	OK	
tenRun([1, 2, 50, 1]) → [1, 2, 50, 50]	[1, 2, 50, 50]	OK	
tenRun([1, 20, 50, 1]) → [1, 20, 50, 50]	[1, 20, 50, 50]	OK	
tenRun([10, 10]) → [10, 10]	[10, 10]	OK	
tenRun([10, 2]) → [10, 10]	[10, 10]	OK	
tenRun([0, 2]) → [0, 0]	[0, 0]	OK	
tenRun([1, 2]) → [1, 2]	[1, 2]	OK	
tenRun([1]) → [1]	[1]	OK	
tenRun([]) → []	[]	OK	
other tests
OK
*/
	
//scoresIncreasing
public boolean scoresIncreasing(int[] scores) {
	  for (int i=0; i<scores.length-1; i++){
	    if (scores[i+1] < scores[i]){
	      return false;
	    }
	  }
	  return true;
	}
/*
scoresIncreasing([1, 3, 4]) → true	true	OK	
scoresIncreasing([1, 3, 2]) → false	false	OK	
scoresIncreasing([1, 1, 4]) → true	true	OK	
scoresIncreasing([1, 1, 2, 4, 4, 7]) → true	true	OK	
scoresIncreasing([1, 1, 2, 4, 3, 7]) → false	false	OK	
scoresIncreasing([-5, 4, 11]) → true	true	OK	
*/

// repeatEnd
public String repeatEnd(String str, int n) {
	  int len = str.length();
	  String newWord = "";
	  
	  for (int i=0; i<n; i++){
	    newWord += str.substring(len-n, len);
	  }
	  return newWord;
	}
/*
repeatEnd("Hello", 3) → "llollollo"	"llollollo"	OK	
repeatEnd("Hello", 2) → "lolo"	"lolo"	OK	
repeatEnd("Hello", 1) → "o"	"o"	OK	
repeatEnd("Hello", 0) → ""	""	OK	
repeatEnd("abc", 3) → "abcabcabc"	"abcabcabc"	OK	
repeatEnd("1234", 2) → "3434"	"3434"	OK	
repeatEnd("1234", 3) → "234234234"	"234234234"	OK	
repeatEnd("", 0) → ""	""	OK	
other tests
OK
 */

// canBalance
public boolean canBalance(int[] nums) {
	  int first = 0;
	  int second = 0;
	  
	  for (int i=0; i<nums.length; i++)
	    second += nums[i];

	  for(int i=0; i<=nums.length-2; i++){
	    first += nums[i];
	    second -= nums[i];

	    if(first == second)
	      return true;
	  }
	  return false;
	}
/*
canBalance([1, 1, 1, 2, 1]) → true	true	OK	
canBalance([2, 1, 1, 2, 1]) → false	false	OK	
canBalance([10, 10]) → true	true	OK	
canBalance([10, 0, 1, -1, 10]) → true	true	OK	
canBalance([1, 1, 1, 1, 4]) → true	true	OK	
canBalance([2, 1, 1, 1, 4]) → false	false	OK	
canBalance([2, 3, 4, 1, 2]) → false	false	OK	
canBalance([1, 2, 3, 1, 0, 2, 3]) → true	true	OK	
canBalance([1, 2, 3, 1, 0, 1, 3]) → false	false	OK	
canBalance([1]) → false	false	OK	
canBalance([1, 1, 1, 2, 1]) → true	true	OK	
other tests
OK	
 */
}