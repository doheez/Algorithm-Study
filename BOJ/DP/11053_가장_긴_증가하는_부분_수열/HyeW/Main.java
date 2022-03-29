import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr, mem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		arr = new int[n];
		mem = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		for (int i = 0; i < n; i++) {
			mem[i] = 1;
			for (int j = 0; j < i; j++) {
				if( arr[j] < arr[i] && mem[i] < mem[j] + 1) {
					mem[i] = mem[j] + 1;
				}
			}
		}
		
		int max = -1;
		for(int i = 0; i < n; i++) {
			max = mem[i] > max ? mem[i] : max;
		}
		System.out.println(max);
	}
}
