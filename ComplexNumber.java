
public class ComplexNumber{
	double i, r;

	public ComplexNumber(){
		i = 0;
		r = 0;
	}

	public ComplexNumber(double real, double imaginary){
		i = imaginary;
		r = real;
	}

	public ComplexNumber multiply(ComplexNumber cn){
		double newR = r*cn.getReal() - i*cn.getImaginary();
		double newI = i*cn.getReal() + r*cn.getImaginary();
		ComplexNumber c = new ComplexNumber(newR, newI);
		return c;	
	}

	public ComplexNumber add(ComplexNumber cn){
		double newR = r + cn.getReal();
		double newI = i + cn.getImaginary();
		ComplexNumber c = new ComplexNumber(newR, newI);
		return c;	
	}

	public double getReal(){
		return r;
	}

	public double getImaginary(){
		return i;
	}

	public void setReal(double real){
		r = real;
	}

	public void setImaginary(double imaginary){
		i = imaginary;
	}

	public String getNumber(){
		String s = r + " + " + i + "i";
		return s;
	}
	
	public void print(){
		System.out.println(r + " + " + i + "i");
	}
}