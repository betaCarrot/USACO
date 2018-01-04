
/*
 ID: majesti2
 LANG: JAVA
 TASK: shopping
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class shopping {
	private static int S, B;
	private static int[][] offerIDs;
	private static int[][] offerQuantities;
	private static int[] offerPrices;
	private static int[] offerSizes;
	private static boolean[] validOffers;
	private static int[] targets;
	private static int[] itemIDs;
	private static int[] itemPrices;
	private static ArrayList<Integer> interestingIDs = new ArrayList<Integer>();
	private static int[][][][][] dp = new int[6][6][6][6][6];
	private static final int MAX = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		S = Integer.parseInt(f.readLine());
		offerIDs = new int[S][5];
		offerQuantities = new int[S][5];
		offerPrices = new int[S];
		offerSizes = new int[S];
		for (int i = 0; i < S; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int N = Integer.parseInt(st.nextToken());
			offerSizes[i] = N;
			for (int k = 0; k < N; k++) {
				offerIDs[i][k] = Integer.parseInt(st.nextToken());
				offerQuantities[i][k] = Integer.parseInt(st.nextToken());
			}
			offerPrices[i] = Integer.parseInt(st.nextToken());
		}
		B = Integer.parseInt(f.readLine());
		targets = new int[5];
		itemIDs = new int[B];
		itemPrices = new int[B];
		for (int i = 0; i < B; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int ID = Integer.parseInt(st.nextToken());
			interestingIDs.add(ID);
			itemIDs[i] = ID;
			targets[i] = Integer.parseInt(st.nextToken());
			itemPrices[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < B; i++) {
			int target = interestingIDs.get(i);
			for (int row = 0; row < S; row++) {
				for (int col = 0; col < 5; col++) {
					if (offerIDs[row][col] == target) {
						offerIDs[row][col] = i;
					}
				}
			}
			itemIDs[i] = i;
		}
		validOffers = new boolean[S];
		for (

				int i = 0; i < S; i++) {
			validOffers[i] = checkOffer(offerIDs[i]);
		}
		for (int a = 0; a <= 5; a++) {
			for (int b = 0; b <= 5; b++) {
				for (int c = 0; c <= 5; c++) {
					for (int d = 0; d <= 5; d++) {
						for (int e = 0; e <= 5; e++) {
							dp[a][b][c][d][e] = MAX;
						}
					}
				}
			}
		}
		dp[0][0][0][0][0] = 0;
		for (int a = 0; a <= 5; a++) {
			for (int b = 0; b <= 5; b++) {
				for (int c = 0; c <= 5; c++) {
					for (int d = 0; d <= 5; d++) {
						for (int e = 0; e <= 5; e++) {
							int curr = dp[a][b][c][d][e];
							for (int i = 0; i < S; i++) {
								if (!validOffers[i])
									continue;
								int nextA = a;
								int nextB = b;
								int nextC = c;
								int nextD = d;
								int nextE = e;
								for (int k = 0; k < offerSizes[i]; k++) {
									int ID = offerIDs[i][k];
									if (ID == 0)
										nextA += offerQuantities[i][k];
									if (ID == 1)
										nextB += offerQuantities[i][k];
									if (ID == 2)
										nextC += offerQuantities[i][k];
									if (ID == 3)
										nextD += offerQuantities[i][k];
									if (ID == 4)
										nextE += offerQuantities[i][k];
								}
								if (nextA > 5 || nextB > 5 || nextC > 5 || nextD > 5 || nextE > 5)
									continue;
								int price = offerPrices[i];
								dp[nextA][nextB][nextC][nextD][nextE] = Math.min(dp[nextA][nextB][nextC][nextD][nextE],
										curr + price);
							}
							for (int i = 0; i < B; i++) {
								int ID = itemIDs[i];
								if (ID == 0) {
									if (a == 5)
										continue;
									int price = itemPrices[i];
									dp[a + 1][b][c][d][e] = Math.min(dp[a + 1][b][c][d][e], curr + price);
								}
								if (ID == 1) {
									if (b == 5)
										continue;
									int price = itemPrices[i];
									dp[a][b + 1][c][d][e] = Math.min(dp[a][b + 1][c][d][e], curr + price);
								}
								if (ID == 2) {
									if (c == 5)
										continue;
									int price = itemPrices[i];
									dp[a][b][c + 1][d][e] = Math.min(dp[a][b][c + 1][d][e], curr + price);
								}
								if (ID == 3) {
									if (d == 5)
										continue;
									int price = itemPrices[i];
									dp[a][b][c][d + 1][e] = Math.min(dp[a][b][c][d + 1][e], curr + price);
								}
								if (ID == 4) {
									if (e == 5)
										continue;
									int price = itemPrices[i];
									dp[a][b][c][d][e + 1] = Math.min(dp[a][b][c][d][e + 1], curr + price);
								}
							}
						}
					}
				}
			}
		}
		int targetA = targets[0];
		int targetB = targets[1];
		int targetC = targets[2];
		int targetD = targets[3];
		int targetE = targets[4];
		out.println(dp[targetA][targetB][targetC][targetD][targetE]);
		out.close();
	}

	public static boolean checkOffer(int[] array) {
		for (int i : array) {
			if (i >= B)
				return false;
		}
		return true;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
