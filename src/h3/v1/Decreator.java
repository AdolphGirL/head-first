package h3.v1;

public class Decreator extends Drink {
	
	private Drink coffee;
	
	public Decreator (Drink coffee) {
		this.coffee = coffee;
	}
	
	@Override
	public float cost() {
		return coffee.cost() + getPrice();
	}

	@Override
	public String getDes() {
		return coffee.getDes() + ", milk";
	}
	
	

}
