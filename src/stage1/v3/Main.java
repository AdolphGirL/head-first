package stage1.v3;

public class Main {
	
	public static void main(String[] args) {
		Drinks drink = new Beverage("咖啡", 120);
		drink = new ChocolateDecorator(drink);
		
		System.out.println(drink.getDescription());
		System.out.println("總價: " + drink.cost() + " 元");
	}
	
}
