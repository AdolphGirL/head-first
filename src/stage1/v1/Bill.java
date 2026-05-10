package stage1.v1;

public class Bill {

	private Pay paymentStrategy;
	
	/** 推薦加上建構子，讓帳單可以隨意變化付款方式 */
	public Bill() {
	}

	public Bill(Pay paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void setPaymentStrategy(Pay paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void executePay() {
		if (paymentStrategy == null) {
			throw new IllegalStateException("尚未設定付款方式！");
		}
		System.out.println("=== 開始執行付款 ===");
		paymentStrategy.execute();
		System.out.println("=== 付款流程結束 ===\n");
	}

}
