/*
* first --> First number
* second --> Second number
* First step is to find the GCD of the 2 numbers, recursive or iterative any method can be used
* use gcd to find the lcm. Formula --> LCM(a,b)=a*b/GCD(a,b) .
* time complexity = time complexity to find gcd= O(log(min(A,B))) .
*/

public class LCM_using_GCD {


    static long euclideanGCDRecursive(long first, long second) {
      if (second==0)
        return first;                 // First becomes GCD when second becomes zero
      return euclideanGCDRecursive(second , first%second);


    }
    static long LCMbyGCD(long first, long second){
      return (first*second)/euclideanGCDRecursive(first,second);      //lcm is product of the 2 numbers devided by their respective greatest common divisor.
    }
    public static void main(String[] args) {
        long first = 55;
        long second = 5;
        System.out.printf("LCM of %d and %d is : %d.\n",first,second,LCMbyGCD(first,second));
    }
}
