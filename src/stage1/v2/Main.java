package stage1.v2;

public class Main {
	
	public static void main(String[] args) {
		Order order = new Order();
		order.setOrgPay(100);
		
		DiscountStrategy discountStrategy1 = new MemberDiscount(0, 1, 2, "測試折扣方式一");
		DiscountStrategy discountStrategy2 = new MemberDiscount(4, 5, 6, "測試折扣方式二");
		
		order.setDiscountStrategy(discountStrategy1);
		System.out.println("[+] 第一種測試，原金額" + order.getOrgPay() + "，扣後金額: " + 
				order.getPay());
		
		
		order.setDiscountStrategy(discountStrategy2);
		System.out.println("[+] 第二種測試，原金額" + order.getOrgPay() + "，扣後金額: " + 
				order.getPay());
		
	}
	
}
