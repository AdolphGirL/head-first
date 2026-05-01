package h3.v1;

public class V1Main {
	
	public static void main(String[] args) {
		EspressoCoffee espressoCoffee = new EspressoCoffee();
		System.out.println(espressoCoffee.getDes());
		System.out.println(espressoCoffee.getPrice());
		System.out.println(espressoCoffee.cost());
		System.out.println(" ========================= ");
		
		Milk m = new Milk(espressoCoffee);
		System.out.println(m.getDes());
		System.out.println(m.getPrice());
		System.out.println(m.cost());
		
	}
	
}
