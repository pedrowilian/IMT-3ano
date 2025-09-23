import java.util.Scanner;

public class Ex15 {
    public static void fibonacci(int n) {

		int primeiro = 0;
		int segundo = 1;
		int fibonacci = 0;

		System.out.print(primeiro + " ");
		System.out.print(segundo + " ");

		for (int i = 2; i <= n; i++) {
			fibonacci = primeiro + segundo;
            if(fibonacci>n)
                break;
			System.out.print(fibonacci + " ");

			primeiro = segundo;
			segundo = fibonacci;
		}
	}

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int n = sc.nextInt();
		fibonacci(n);

	}
}
