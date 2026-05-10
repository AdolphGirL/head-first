package stage1.v1;

public class Main {

	public static void main(String[] args) {
		Bill bill = new Bill();

		// 切換不同付款方式
		bill.setPaymentStrategy(new CreditCardPayment());
		bill.executePay();

		bill.setPaymentStrategy(new ApplePayPayment());
		bill.executePay();

		bill.setPaymentStrategy(new ConvenienceStorePayment());
		bill.executePay();
	}

}
