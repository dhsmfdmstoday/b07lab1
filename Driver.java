import java.io.*;
import java.util.*;

public class Driver { 
 public static void main(String [] args)throws Exception { 
    File aa = new File("/Users/kimtaeya/Downloads/test.txt");
 Polynomial a= new Polynomial(aa);
  System.out.println(Arrays.toString(a.coefficient));
    System.out.println(Arrays.toString(a.exponent));
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {5,0,6,0}; 
  int [] e1= {3,1,0,2};
  Polynomial p1 = new Polynomial(c1,e1); 
  double [] c2 = {0,-2,0,0,-9}; 
  int [] e2={0,1,2,3,4};
  Polynomial p2 = new Polynomial(c2,e2); 
  Polynomial s = p1.add(p2); 
  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 

    Polynomial u = p1.multiply(p2);

    System.out.println(Arrays.toString(u.coefficient));
    System.out.println(Arrays.toString(u.exponent));
    u.saveToFile("/Users/kimtaeya/Downloads/test.txt");
 } 
} 