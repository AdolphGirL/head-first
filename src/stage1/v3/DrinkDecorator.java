package stage1.v3;

public abstract class DrinkDecorator implements Drinks {

	/** 包裹住原本的飲料 */
	protected Drinks beverage;

	public DrinkDecorator(Drinks beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription();
	}

	@Override
	public double cost() {
		return beverage.cost();
	}

}
