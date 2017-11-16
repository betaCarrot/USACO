import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class bteams {
	private static int result = Integer.MAX_VALUE;
	private static int[] skills = new int[12];
	private static int[] teamCount = new int[4];
	private static int[] playerTeam = new int[12];
	private static int first, second, third, fourth, total;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bteams.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));
		long startTime = System.currentTimeMillis();
		total = 0;
		for (int i = 0; i < 12; i++) {
			int temp = Integer.parseInt(f.readLine());
			skills[i] = temp;
			total += temp;
		}
		fillArray(0);
		out.println(result);
		out.close();
		System.out.println(System.currentTimeMillis() - startTime);
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}

	public static boolean better1(int[] temp) {
		first = 0;
		second = 0;
		for (int i = 0; i < 3; i++) {
			first += skills[temp[i]];
		}
		for (int i = 3; i < 6; i++) {
			second += skills[temp[i]];
		}
		return Math.abs(first - second) < result;
	}

	public static boolean better2(int[] temp) {
		third = 0;
		for (int i = 6; i < 9; i++) {
			third += skills[temp[i]];
		}
		return Math.abs(first - third) < result && Math.abs(second - third) < result;
	}

	public static boolean valid(int n, int[] array, int index) {
		for (int i = 0; i < index; i++) {
			if (array[i] == n)
				return false;
		}
		return true;
	}

	public static int max(int a, int b, int c, int d) {
		return Math.max(a, Math.max(b, Math.max(c, d)));
	}

	public static int min(int a, int b, int c, int d) {
		return Math.min(a, Math.min(b, Math.min(c, d)));
	}

	public static void fillArray(int count) {
		if (count == 12) {
			int[] temp = new int[4];
			for (int i = 0; i < 12; i++) {
				temp[playerTeam[i]] += skills[i];
			}
			int max = max(temp[0], temp[1], temp[2], temp[3]);
			int min = min(temp[0], temp[1], temp[2], temp[3]);
			result = Math.min(result, max - min);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (teamCount[i] < 3) {
				teamCount[i]++;
				playerTeam[count] = i;
				fillArray(count + 1);
				teamCount[i]--;
			}
		}
	}
}
