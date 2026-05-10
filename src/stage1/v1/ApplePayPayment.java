package stage1.v1;

public class ApplePayPayment implements Pay {

	@Override
	public void execute() {
		System.out.println("[Apple Pay] 呼叫 Apple 伺服器進行驗證...");
		System.out.println("[Apple Pay] 生物辨識通過，付款完成。");
	}

}
