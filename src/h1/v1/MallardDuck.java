package h1.v1;

public class MallardDuck extends Duck {

	/** 此處後續可以再進行設計，綁定了物件的實例化 new FlyBehaviorV1(), new QuackBehaviorV1() */
	public MallardDuck() {
		super(new FlyBehaviorV1(), new QuackBehaviorV1());
	}

	@Override
	public void display() {
		System.out.println("[+] MallardDuck display ");
	}
	
	
}
