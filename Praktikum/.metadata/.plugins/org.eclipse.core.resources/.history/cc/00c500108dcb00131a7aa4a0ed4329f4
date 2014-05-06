
public class Circle {
	public double cx;
	public double cy;
	public double radius;
	
	//Konstukrtor:
	Circle(double Icx, double Icy, double Iradius){
		cx = Icx;
		cy = Icy;
		radius = Iradius;
		
	}
	//Fläche des Kreises 2pi r2
public double computeArea(){
	double ergebnis; ergebnis = 2*Math.PI*radius;
	return ergebnis;
}


	//Abstand des Kreismittelpunktes zum Mittelpunkt eines anderen Kreises(that)
	//
public double computeDistanceOfCircles(Circle that){
	return Math.sqrt(Math.pow(this.cx - that.cx, 2.0) + Math.pow(this.cy - that.cy, 2.0));
}
//ob der vorliegende Kreis (this) von einem andern Kreis berührt bzw. überlappt wird
public boolean touches(Circle that){
	if (this.computeDistanceOfCircles(that) <= this.radius + that.radius){
		return true;
	}else {
	return false;
	}
}
}
