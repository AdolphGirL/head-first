package stage1.v2;

public class Order {

	private DiscountStrategy DiscountStrategy;
	private double orgPay;

	public DiscountStrategy getDiscountStrategy() {
		return DiscountStrategy;
	}

	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		DiscountStrategy = discountStrategy;
	}

	public double getOrgPay() {
		return orgPay;
	}

	public void setOrgPay(double orgPay) {
		this.orgPay = orgPay;
	}

	public double getPay() {
		return this.DiscountStrategy.calculate(getOrgPay());
	}

}
