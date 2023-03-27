public class p20 {
	public static void main(String[] args) {
		double[] nums =  new double[10];
		double max = 0, min = 1;
		for (int i = 0; i < 10; i++) {
			nums[i] = Math.random();
			if ( nums[i] > max )
				max = nums[i];
			if ( nums[i] < min )
				min = nums[i];
		}
		System.out.print("El arreglo es... [");
		for ( double num : nums )
			System.out.printf("%.5f, ", num);
		System.out.println("\033[2D]");
		System.out.println("El minimo es --> " + min);
		System.out.println("El maximo es --> " + max);
	}
}
