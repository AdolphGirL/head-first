package h1.v1;

public class Duck {

	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;

	public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		this.flyBehavior = flyBehavior;
		this.quackBehavior = quackBehavior;
	}

	/** 此處，就不再需要子類自行實現，而是透過屬性介面進行實作 */
	public void performFly() {
		flyBehavior.fly();
	}

	public void performQuack() {
		quackBehavior.quack();
	}

	public void display() {

	}

}
