package h4;

/**
 * 不是 GoF 23 種正式模式，但實務上最常用。由一個類別負責建立所有產品 
 * 1. 建立邏輯簡單、產品種類不多。 2. 不需要子類繼承擴展。
 * 
 * 缺點
 * 違反 OCP（新增產品要改工廠類別）
 */
public class SimpleFactoryMain {
	public static void main(String[] args) {
		Product p = SimpleFactory.createProduct("A");
		p.use();
	}
}

/** 產品介面 */
interface Product {
	void use();
}

class ConcreteProductA implements Product {
	public void use() {
		System.out.println("使用產品 A");
	}
}

class ConcreteProductB implements Product {
	public void use() {
		System.out.println("使用產品 B");
	}
}

/** 簡單工廠 */
class SimpleFactory {
	public static Product createProduct(String type) {
		switch (type) {
		case "A":
			return new ConcreteProductA();
		case "B":
			return new ConcreteProductB();
		default:
			throw new IllegalArgumentException("未知產品");
		}
	}
}