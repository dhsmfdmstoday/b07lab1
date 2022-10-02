import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintStream;
import java.io.*;
import java.util.*;


public class Polynomial{
	double [] coefficient;
	int [] exponent;
	
	public Polynomial(){
		this.coefficient = new double [1];
		this.exponent = new int [1];
		this.coefficient[0]=0;
		this.exponent[0]=0;
		
	}
	public Polynomial(double [] poly,int [] expo){
		this.coefficient = poly;
		this.exponent = expo;
	}		

	public Polynomial(File file) throws Exception{
		BufferedReader b = new BufferedReader(new FileReader(file));
		String line = b.readLine();
		String [] temp = line.split(("([+])|(?=[-])"));
		coefficient = new double [temp.length];
		exponent= new int[temp.length];int count=0;
		for (String s : temp){
		 	String [] a = s.split("x");
			if(a.length<1){
				coefficient[count]= Double.parseDouble(a[0]);
				exponent[count]= 0;
			}
			else{
				coefficient[count]= Double.parseDouble(a[0]);
				exponent[count]= Integer.parseInt(a[1]);
			}
			count++;

		 }
        b.close();
    }
    public Polynomial reduc(Polynomial argument){
    	int count=0;
    	for(int i=0; i< argument.exponent.length;i++){
    		if(argument.coefficient[i]==0){
    			count++;
    		}
    	}
    	double []temp_co = new double[argument.coefficient.length-count];
    	int []temp_ex = new int[argument.exponent.length-count];
    	int j=0;
    	for(int i =0;i<argument.exponent.length;i++){
    		if(argument.coefficient[i]!= 0){
    			temp_co[j]= argument.coefficient[i];
    			temp_ex[j]= argument.exponent[i];
    			j++;
    		}
    	}

    	return new Polynomial(temp_co,temp_ex);

    }
    public Polynomial add_2(Polynomial argument,int i){
    	double []temp_co = new double[argument.coefficient.length+1];
    	int []temp_ex = new int[argument.exponent.length+1];

    	temp_co[0]= this.coefficient[i];
    	temp_ex[0]= this.exponent[i];
    	for(int j=1;j<argument.exponent.length+1;j++){
    		temp_co[j]= argument.coefficient[j-1];
    		temp_ex[j]= argument.exponent[j-1];
    	}
    	return new Polynomial(temp_co,temp_ex);
    }
	public Polynomial add(Polynomial argument){
		Polynomial temp;double []temp_co;int []temp_ex;

		if(argument.coefficient.length >= coefficient.length){
			temp_co = new double[argument.coefficient.length];temp_ex = new int[argument.exponent.length];
			for(int i =0; i< argument.coefficient.length;i++){
				temp_co[i]= argument.coefficient[i];
				temp_ex[i]= argument.exponent[i];
			}
			temp= new Polynomial(temp_co,temp_ex);
			for(int i=0; i< coefficient.length;i++){
				for(int j=0;j<argument.coefficient.length;j++){
					if(exponent[i]==argument.exponent[j]){
						temp.coefficient[j]+= coefficient[i];
						j=argument.coefficient.length+1;
					}
					else if(exponent[i]!= argument.exponent[j]&&j ==argument.coefficient.length-1){
						temp=add_2(temp,i);
					}
				}
			}

		}
		else{
			temp_co = new double[coefficient.length];temp_ex = new int[exponent.length];
			for(int i =0; i< coefficient.length;i++){
				temp_co[i]= coefficient[i];
				temp_ex[i]= exponent[i];
			}
			temp= new Polynomial(temp_co,temp_ex);
				for(int i=0; i< argument.coefficient.length;i++){
					for(int j=0;j<coefficient.length;j++){
						if(exponent[j]==argument.exponent[i]){
							temp.coefficient[i]+= argument.coefficient[j];
							j=coefficient.length+1;
						}
						else if(exponent[j]!= argument.exponent[i]&&j ==coefficient.length-1){
							temp=add_2(temp,i);
						}
				}
			}
		}
		return reduc(temp);
	}


	public double evaluate(double x){
		double result =0;
		for(int i=0;i<coefficient.length;i++){
			result = result + (coefficient[i]*Math.pow(x,exponent[i]));
		}
		return result;
	}

	
	public boolean hasRoot(double x){
		if(evaluate(x)==0){
			return true;
		}
		return false;

	}
	public Polynomial multiply(Polynomial argument){
		double []temp_co = new double[argument.coefficient.length+coefficient.length-1];
    	int []temp_ex = new int[argument.exponent.length+coefficient.length-1];
		for (int i = 0; i < temp_co.length; i++) {
            temp_co[i] = 0;
            temp_ex[i]=i;
        }
        for (int i = 0; i < coefficient.length; i++) 
        {
            for (int j = 0; j < argument.coefficient.length; j++) {
                temp_co[exponent[i] + argument.exponent[j]] += coefficient[i] * argument.coefficient[j];
            }
        }
        Polynomial temp = new Polynomial(temp_co,temp_ex);
  
        return reduc(temp);

	}
	public void saveToFile(String file) throws Exception{
		PrintStream ps = new PrintStream(file);
		  for (int i = 0; i < exponent.length; i++) {
		  	ps.print((int)coefficient[i]);
		  	if(exponent[i] !=0){
		  		ps.print("x"+exponent[i]);
		  	}
            if (i != exponent.length - 1 && coefficient[i]>=0) 
            {
                ps.print("+");
            }
        }
		ps.close();

	}
}
