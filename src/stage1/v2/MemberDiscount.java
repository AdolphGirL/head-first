package stage1.v2;

/**
 * 透過建構子參數，組合出不同的折扣；不進行多種策略的實現， 使用參數化策略，後續有任何折扣的新增或修改，只需要改此
 */
public class MemberDiscount implements DiscountStrategy {

	private final double discountRate; // 折扣率 (ex: 0.95 = 95折)
	private final double threshold; // 滿額門檻 (ex: 1000)
	private final double cashback; // 滿額折抵金額
	private final String description;

	public MemberDiscount(double discountRate, double threshold, double cashback, String description) {
		this.discountRate = discountRate;
		this.threshold = threshold;
		this.cashback = cashback;
		this.description = description;
	}

	@Override
	public double calculate(double originalPrice) {
		double price = originalPrice * discountRate;

		// 滿額折抵
		if (originalPrice >= threshold && cashback > 0) {
			price -= cashback;
		}

		return Math.max(price, 0);
	}

	@Override
	public String getDescription() {
		return description;
	}

}
