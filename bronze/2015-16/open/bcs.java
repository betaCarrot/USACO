import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bcs {
	private static int N;
	private static boolean[][] original;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bcs.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcs.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		original = new boolean[N][N];
		int numPositive = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int a = 0; a < N; a++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				if (line.substring(k, k + 1).equals("#")) {
					original[a][k] = true;
					numPositive++;
				}
			}
		}
		boolean verticalAlignFinished0 = false;
		while (true) {
			for (int i = 0; i < N; i++) {
				if (original[i][0])
					verticalAlignFinished0 = true;
			}
			if (verticalAlignFinished0)
				break;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < N - 1; k++) {
					original[i][k] = original[i][k + 1];
				}
			}
			for (int i = 0; i < N; i++) {
				original[i][N - 1] = false;
			}
		}
		boolean horizontalAlignFinished0 = false;
		while (true) {
			for (int k = 0; k < N; k++) {
				if (original[0][k])
					horizontalAlignFinished0 = true;
			}
			if (horizontalAlignFinished0)
				break;
			for (int i = 0; i < N - 1; i++) {
				for (int k = 0; k < N; k++) {
					original[i][k] = original[i + 1][k];
				}
			}
			for (int i = 0; i < N; i++) {
				original[N - 1][i] = false;
			}
		}
		ArrayList<boolean[][]> figures = new ArrayList<boolean[][]>();
		for (int a = 0; a < K; a++) {
			boolean[][] figure = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				String line = f.readLine();
				for (int k = 0; k < N; k++) {
					if (line.substring(k, k + 1).equals("#"))
						figure[i][k] = true;
				}
			}
			boolean verticalAlignFinished = false;
			while (true) {
				for (int i = 0; i < N; i++) {
					if (figure[i][0])
						verticalAlignFinished = true;
				}
				if (verticalAlignFinished)
					break;
				for (int i = 0; i < N; i++) {
					for (int k = 0; k < N - 1; k++) {
						figure[i][k] = figure[i][k + 1];
					}
				}
				for (int i = 0; i < N; i++) {
					figure[i][N - 1] = false;
				}
			}
			boolean horizontalAlignFinished = false;
			while (true) {
				for (int k = 0; k < N; k++) {
					if (figure[0][k])
						horizontalAlignFinished = true;
				}
				if (horizontalAlignFinished)
					break;
				for (int i = 0; i < N - 1; i++) {
					for (int k = 0; k < N; k++) {
						figure[i][k] = figure[i + 1][k];
					}
				}
				for (int i = 0; i < N; i++) {
					figure[N - 1][i] = false;
				}
			}
			figures.add(figure);
		}
		for (int i = 0; i < K; i++) {
			boolean finished3 = false;
			boolean[][] figure = figures.get(i);
			for (int j = 0; j < K; j++) {
				if (i == j)
					continue;
				int fixedNum = 0;
				boolean[][] fixed = new boolean[N][N];
				boolean[][] nextFigure = figures.get(j);
				for (int row = 0; row < N; row++) {
					boolean finished2 = false;
					for (int col = 0; col < N; col++) {
						boolean valid = true;
						for (int rf = 0; rf < N; rf++) {
							boolean finished1 = false;
							int ro = rf + row;
							if (ro >= N) {
								for (int cf = 0; cf < N; cf++) {
									if (figure[rf][cf]) {
										valid = false;
										finished1 = true;
										break;
									}
								}
								continue;
							}
							for (int cf = 0; cf < N; cf++) {
								int co = cf + col;
								if (co >= N) {
									if (figure[rf][cf]) {
										valid = false;
										finished1 = true;
										break;
									}
									continue;
								}
								if (figure[rf][cf] && (!original[ro][co])) {
									valid = false;
									finished1 = true;
									break;
								}
							}
							if (finished1)
								break;
						}
						if (valid) {
							for (int rf = 0; rf < N; rf++) {
								int ro = rf + row;
								if (ro >= N)
									continue;
								for (int cf = 0; cf < N; cf++) {
									int co = cf + col;
									if (co >= N)
										continue;
									if (figure[rf][cf] && original[ro][co]) {
										if (!fixed[ro][co])
											fixedNum++;
										fixed[ro][co] = true;
									}
								}
							}
							result.add(i + 1);
							finished2 = true;
							break;
						}
					}
					if (finished2)
						break;
				}
				for (int row = 0; row < N; row++) {
					boolean finished2 = false;
					for (int col = 0; col < N; col++) {
						boolean valid = true;
						for (int rf = 0; rf < N; rf++) {
							boolean finished1 = false;
							int ro = rf + row;
							if (ro >= N) {
								for (int cf = 0; cf < N; cf++) {
									if (nextFigure[rf][cf]) {
										valid = false;
										finished1 = true;
										break;
									}
								}
								continue;
							}
							for (int cf = 0; cf < N; cf++) {
								int co = cf + col;
								if (co >= N) {
									if (nextFigure[rf][cf]) {
										valid = false;
										finished1 = true;
										break;
									}
									continue;
								}
								if (nextFigure[rf][cf] && (!original[ro][co])) {
									valid = false;
									finished1 = true;
									break;
								}
								if (nextFigure[rf][cf] && fixed[ro][co]) {
									valid = false;
									finished1 = true;
									break;
								}
							}
							if (finished1)
								break;
						}
						if (valid) {
							for (int rf = 0; rf < N; rf++) {
								int ro = rf + row;
								if (ro >= N)
									continue;
								for (int cf = 0; cf < N; cf++) {
									int co = cf + col;
									if (co >= N)
										continue;
									if (nextFigure[rf][cf] && original[ro][co]) {
										if (!fixed[ro][co])
											fixedNum++;
										fixed[ro][co] = true;
									}
								}
							}
							result.add(i + 1);
							finished2 = true;
							break;
						}
					}
					if (finished2)
						break;
				}
				if (fixedNum == numPositive) {
					if (i <= j)
						out.println((i + 1) + " " + (j + 1));
					else
						out.println((j + 1) + " " + (i + 1));
					finished3 = true;
					break;
				}
			}
			if (finished3)
				break;
		}
		out.close();
	}
}