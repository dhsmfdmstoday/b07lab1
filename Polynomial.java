public class Polynomial{
	double [] poly= new double[100];

	
	public Polynomial(){
		for(int i =0;i<100;i++){
			this.poly[i]=0;
		}
	}
	public Polynomial(double [] poly){
		for(int i =0;i<poly.length;i++){
			this.poly[i]=poly[i];
		}

	}
	public Polynomial add(Polynomial argument){
		for(int i=0;i<100;i++){
			argument.poly[i]= poly[i]+(argument.poly)[i];
		}
		return argument;
	}
	public double evaluate(double x){
		double result =0;
		for(int i=0;i<100;i++){
			result = result + (poly[i]*Math.pow(x,i));
		}
		return result;
	}

	

	public boolean hasRoot(double x){
		if(evaluate(x)==0){
			return true;
		}
		return false;

	}




}