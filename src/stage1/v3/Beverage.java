package stage1.v3;

public class Beverage implements Drinks {

	private String name;
	private double basePrice;

	public Beverage(String name, double basePrice) {
		this.name = name;
		this.basePrice = basePrice;
	}

	@Override
	public double cost() {
		return basePrice;
	}

	@Override
	public String getDescription() {
		return name;
	}

}
