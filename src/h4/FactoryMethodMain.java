package h4;

/**
 * 工廠方法模式 定義一個建立物件的介面，但讓子類決定要實例化哪一個類別。
 * 工廠方法讓類別的實例化延遲到子類別。
 * 
 * 與抽象工廠模式(強調「一組」產品一起建立。)的差異
 * 
 */
public class FactoryMethodMain {
	public static void main(String[] args) {
		CreatorY creator = new CreatorAY();
		creator.someOperation();
	}
}

interface ProductY {
	void use();
}

class ConcreteProductAY implements ProductY {
	public void use() {
		System.out.println("使用產品 AY");
	}
}

class ConcreteProductBY implements ProductY {
	public void use() {
		System.out.println("使用產品 BY");
	}
}

/** 抽象工廠 */
abstract class CreatorY {

	/** 交由子類生成需要的類別 */
	public abstract ProductY factoryMethod();

	public void someOperation() {
		ProductY producty = factoryMethod();
		producty.use();
	}

}

/** 具體工廠 */
class CreatorAY extends CreatorY {
	
	@Override
	public ProductY factoryMethod() {
		return new ConcreteProductAY();
	}
}

class CreatorBY extends CreatorY {
	
	@Override
	public ProductY factoryMethod() {
		return new ConcreteProductBY();
	}
}