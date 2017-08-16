import java.util.Scanner;

public class FiveNumberSummary {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Q1: ");
		double q1 = in.nextDouble();
		System.out.println("Q3: ");
		double q3 = in.nextDouble();
		System.out.println("X: ");
		double x = in.nextDouble();
		double inner = 1.5 * (q3 - q1);
		double outer = inner * 2;
		if (x < q1 - outer || x > q3 + outer)
			System.out.print("Extreme Outlier" + (x < q1 - outer));
		
		else if (x < q1 - inner || x > q3 + inner)
			System.out.print("Mild Outlier");
		else
			System.out.print("Neither");

	}
}
