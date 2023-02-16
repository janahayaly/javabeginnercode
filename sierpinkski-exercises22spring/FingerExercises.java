public class FingerExercises {

    /*
    1
    Inputs: Two integers
    Output: One integer
    Description: takes in two ints to return
     their greatest common denominator
    */
    public static int gcd(int a, int b) {
        if (b == 0 || a == b) {
            return a;
        } else if (a < b) {
            return gcd(b, a);
        } else {
            return gcd(b, a % b);
        }
    }

    /*
    2
    Inputs: two integers
    Output: one integer
    Description: takes in two ints and sums 
    all the numbs between them
    */
    public static int sumBetween(int a, int b) {
        if (a == b) {
            return b;
        } else {
            return a + sumBetween(a + 1, b);
        }
    }

    /*
    Inputs: one integer
    Output: one integer
    Description: sums all the numbers from 1 up
    to an integer (using previous function)
    */
    public static int sumTo(int x) {
        return sumBetween(1, x);
    }

    /*
    3
    Inputs: largest and 2nd largest variable, index variable,
    and array of integers
    Output: one integer
    Description: finds the second largest in an array of integers
    */
    public static int findSecondLargestHelper(int largest, 
    int secondLargest, int index, int[] nums) {
        if (index == nums.length) {
            return secondLargest;
        } else {
            if (nums[index] > largest) {
                return findSecondLargestHelper(nums[index], largest, 
                index + 1, nums);
            } else if (nums[index] > secondLargest) {
                return findSecondLargestHelper(largest, nums[index], 
                index + 1, nums);
            } else {
                return findSecondLargestHelper(largest, secondLargest, 
                index + 1, nums);
            }
        }
    }

    public static int findSecondLargest(int[] nums) {
        return findSecondLargestHelper(Integer.MIN_VALUE, 
        Integer.MIN_VALUE, 0, nums);
    }

    /*
    4
    Inputs: one integer
    Output: one integer
    Description: takes a number and sums all its digits
    */
    public static int sumOfDigits(int x) {
        String s = Integer.toString(x);
        int i = Integer.parseInt(Character.toString(s.charAt(0)));
        if (s.length() == 1) {
            return i;
        } else {
            return i + sumOfDigits(Integer.parseInt(s.substring(1)));
        }
    }

    /*
    5
    Inputs: one integer
    Output: one integer
    Description: counts number of ways to sum a number using 1 and 2
    */
    public static int countWaysToClimb(int stairs) {
        if (stairs == 0 || stairs == 1) {
            return 1;
        } else {
            return countWaysToClimb(stairs - 1) + 
            countWaysToClimb(stairs - 2);
        }
    }

    /*
    6
    Inputs: base integer and number integer
    Output: exponent integer
    Description: takes the log of a number 'n' with base 'base'
    */
    public static int log(int base, int n) {
        if (n == 1) {
            return 0;
        } else {
            return 1 + log(base, n/base);
        }
    }

    /*
    7
    Inputs: one string word and one string sequence
    Output: one integer
    Description: counts how many times a sequence appears in a string
    */
    public static int countSubstrings(String sequence, String word) {
        if (word.length() < sequence.length()) {
            return 0;
        } else {
            if ((word.substring(0, sequence.length())).equals(sequence)) {
                return 1 + countSubstrings(sequence, word.substring(1));
            } else {
                return countSubstrings(sequence, word.substring(1));
            }
        }
    }

}