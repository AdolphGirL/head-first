package stage1.v2;

public interface DiscountStrategy {

	double calculate(double originalPrice);

	String getDescription();

}
