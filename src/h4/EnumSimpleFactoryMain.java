package h4;

import java.util.function.Supplier;

/**
 * Enum + Supplier 的簡化版 Simple Factory
 * 
 */
public class EnumSimpleFactoryMain {
	
	public static void main(String[] args) {
		
		/** 方式1：直接使用 Enum */
		ProductX p1 = ProductType.AX.create();
		ProductX p2 = ProductType.BX.create();

		p1.use();
		p2.use();
		
		/** 方式2：根據字串動態建立（最常用） */
		ProductX dynamicProduct = ProductType.fromString("CX").create();
		dynamicProduct.use();
		
		/** 方式3：更靈活的寫法 */
		ProductType.valueOf("AX").create().use();
	}
}

/** 產品介面 */
interface ProductX {
	void use();
}

class ConcreteProductAX implements ProductX {
	public void use() {
		System.out.println("使用產品 AX");
	}
}

class ConcreteProductBX implements ProductX {
	public void use() {
		System.out.println("使用產品 BX");
	}
}

class ConcreteProductCX implements ProductX {
	public void use() {
		System.out.println("使用產品 CX");
	}
}

enum ProductType {
	
	AX(ConcreteProductAX::new),
	BX(() -> new ConcreteProductBX()),
	CX(() -> new ConcreteProductCX())
	;
	
	private final Supplier<ProductX> supplier;
	
	ProductType(Supplier<ProductX> supplier) {
		this.supplier = supplier;
	}
	
	public ProductX create() {
		return supplier.get();
	}
	
	/** 可選：根據字串取得（支援外部傳入） */
	public static ProductType fromString(String type) {
		try {
			return ProductType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("未知的產品類型: " + type);
		}
	}
}


