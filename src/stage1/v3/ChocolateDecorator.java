package stage1.v3;

public class ChocolateDecorator extends DrinkDecorator {

	public ChocolateDecorator(Drinks beverage) {
		super(beverage);
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + "，加巧克力";
	}

	@Override
	public double cost() {
		return beverage.cost() + 0.5;
	}

}
