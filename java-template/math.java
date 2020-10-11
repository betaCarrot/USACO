import java.util.HashMap;

public class math {
	public static int[] v, primes, phi, miu;
	public static int[] p, cs;
	public static int m;
	public static final int MAX = 100;
	public static long x, y;
	public static int N;
	public static double[][] c;
	public static double[] b;

	public static void main(String[] args) {
		long curr = 1;
		for (int i = 0; i < 10; i++) {
			curr *= 100;
		}
		System.out.println(curr);
	}

	public static void sieve() {
		int p = 0;
		phi = new int[MAX + 1];
		v = new int[MAX + 1];
		primes = new int[MAX + 1];
		phi[1] = 1;
		miu = new int[MAX + 1];
		for (int i = 1; i <= MAX; i++) {
			miu[i] = 1;
		}
		for (int i = 2; i <= MAX; i++) {
			if (v[i] == 0) {
				v[i] = i;
				primes[++p] = i;
				phi[i] = i - 1;
				miu[i] = -1;
				for (int j = 2 * i; j <= MAX; j += i) {
					v[j] = 1;
					if ((j / i) % i == 0)
						miu[j] = 0;
					else
						miu[j] *= -1;
				}
			}
			for (int j = 1; j <= p; j++) {
				if (primes[j] > v[i] || primes[j] > MAX / i) {
					break;
				}
				v[i * primes[j]] = primes[j];
				phi[i * primes[j]] = phi[i] * ((i % primes[j]) == 0 ? primes[j] : primes[j] - 1);
			}
		}
	}

	public static long phi(long n) {
		long res = n;
		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				res = res / i * (i - 1);
				while (n % i == 0)
					n /= i;
			}
		}
		if (n > 1)
			res = res / n * (n - 1);
		return res;
	}

	public static void divide(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				p[m] = i;
				cs[m] = 0;
				while (n % i == 0) {
					n /= i;
					cs[m]++;
				}
				m++;
			}
		}
		if (n > 1) {
			p[m] = n;
			cs[m] = 1;
			m++;
		}
	}

	public static long qpow(long a, long b, long p) {
		long res = 1 % p;
		for (; b > 0; b >>= 1) {
			if ((b & 1) == 1) {
				// res = qmul(res, a, p);
				res *= a;
				res %= p;
			}
			// a = qmul(a, a, p);
			a *= a;
			a %= p;
		}
		return res;
	}

	public static long qmul(long a, long b, long p) {
		long res = 0;
		for (; b > 0; b >>= 1) {
			if ((b & 1) == 1) {
				res += a;
				res %= p;
			}
			a += a;
			a %= p;
		}
		return res;
	}

	public static long gcd(long a, long b) {
		return b > 0 ? gcd(b, a % b) : a;
	}

	public static long exgcd(long a, long b) {
		if (b == 0) {
			x = 1;
			y = 0;
			return a;
		}
		long d = exgcd(b, a % b);
		long z = x;
		y = z - y * (a / b);
		return d;
	}

	public static long bsgs(long a, long b, long p) {
		HashMap<Long, Long> map = new HashMap<Long, Long>();
		b %= p;
		long t = (long) (Math.sqrt(p)) + 1;
		for (long j = 0; j < t; j++) {
			long val = b * qpow(a, j, p) % p;
			map.put(val, j);
		}
		a = qpow(a, t, p);
		if (a == 0) {
			return b == 0 ? 1 : -1;
		}
		for (long i = 0; i <= t; i++) {
			long val = qpow(a, i, p);
			long j = map.getOrDefault(val, -1L);
			if (j >= 0 && i * t - j >= 0)
				return i * t - j;
		}
		return -1;
	}

	public static void rref() {// POJ2947
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (Math.abs(c[j][i]) > 1e-8) {
					for (int k = 0; k < N; k++) {
						double t = c[i][k];
						c[i][k] = c[j][k];
						c[j][k] = t;
					}
					double t = b[i];
					b[i] = b[j];
					b[j] = t;
					break;
				}
			}
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				double rate = c[j][i] / c[i][i];
				for (int k = i; k < N; k++) {
					c[j][k] -= c[i][k] * rate;
				}
				b[j] -= b[i] * rate;
			}
		}
	}

	public static long[][] qpow(long[][] a, int k) {
		long[][] res = new long[N][N];
		for (int i = 0; i < N; i++) {
			res[i][i] = 1;
		}
		for (; k > 0; k >>= 1) {
			if ((k & 1) == 1) {
				res = mul(a, res);
			}
			a = mul(a, a);
		}
		return res;
	}

	public static long[][] mul(long[][] a, long[][] b) {
		long[][] c = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j]);
				}
			}
		}
		return c;
	}

	public static long CRT(long n, long m) {
		long M = 1;
		long[] as = new long[N];
		long[] ms = new long[N];
		long[] ts = new long[N];
		for (int i = 0; i < N; i++) {
			ms[i] = 1;
			as[i] = 1;
			M *= ms[i];
		}
		for (int i = 0; i < N; i++) {
			long Mi = M / ms[i];
			exgcd(Mi, ms[i]);
			ts[i] = x;
		}
		long res = 0;
		for (int i = 0; i < 4; i++) {
			res += as[i] * (M / ms[i]) * ts[i];
		}
		res = (res % M + M) % M;
		return res;
	}
}
