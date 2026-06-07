package h7;

/**
 * 配器模式将一个类的接口,转换成客户期望的另一 个接口。适配器让原本接口不兼容的类可以合作无间。
 */
public class Main {
	
	public static void main (String[] args) {
		MallardDuck duck = new MallardDuck();
		
		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		
		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();
		
		System.out.println("\nThe Duck says...");
		testDuck (duck);
		System.out.println("\nThe TurkeyAdapter says...");
		testDuck (turkeyAdapter);
	}
	
	static void testDuck (Duck duck) {
		duck.quack();
		duck.fly () ;
	}
}

interface Duck {
	public void quack();

	public void fly();
}

class MallardDuck implements Duck {
	public void quack() {
		System.out.println("Quack");
	}

	public void fly() {
		System.out.println("I'm flying");
	}
}

interface Turkey {
	public void gobble();

	public void fly();
}

class WildTurkey implements Turkey {
	public void gobble() {
		System.out.println("Gobble gobble");
	}

	public void fly() {
		System.out.println("I'm flying a short distance");
	}
}

class TurkeyAdapter implements Duck {
	private Turkey turkey;
	
	public TurkeyAdapter (Turkey turkey) {
		this.turkey = turkey;
	}

	public void quack() {
		turkey.gobble();
	}
	
	/** 雖然个接口都具备了bly()方法，火鸡
	的飞行距离很短,不像鸭子可以长途飞
	行。要让鸭子的飞行和火鸡的飞行能够
	对应,必须连续五次调用火鸡的y()来
	完成。*/
	public void fly () {
		for (int i=0; i<5; i++) {
			turkey.fly();
		}
	}
}