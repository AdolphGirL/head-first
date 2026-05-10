package stage1.v1;

public class ConvenienceStorePayment implements Pay {
	
	@Override
	public void execute() {
		System.out.println("[超商代碼] 已產生繳費代碼：CVS-987654321");
		System.out.println("[超商代碼] 請於 3 天內至超商完成付款。");
	}
	
}