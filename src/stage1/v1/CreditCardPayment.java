package stage1.v1;

public class CreditCardPayment implements Pay {

	@Override
	public void execute() {
		System.out.println("[信用卡] 連線發卡銀行進行授權...");
		System.out.println("[信用卡] 授權成功！扣款完成。");
	}

}
