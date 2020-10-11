#include <bits/stdc++.h>

using namespace std;

#define ll long long

const int MAX = 1e3;
const int MOD = 10007;
int C[MAX][MAX];

int qpow(int a, int b, int p) {
	int res = 1 % p;
	for (; b; b >>= 1) {
		if (b & 1)
			res = res * a % p;
		a = a * a % p;
	}
	return res;
}

ll qpow(ll a, ll b, ll p) {
	ll res = 1 % p;
	for (; b; b >>= 1) {
		if (b & 1)
			res = res * a % p;
		a = a * a % p;
	}
	return res;
}

void prepare() {
	C[0][0] = 1;
	for (int i = 1; i < MAX; i++) {
		C[i][0] = 1;
		for (int j = 1; j <= i; j++) {
			C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
		}
	}
}

int CC(int n, int k) {
	int up = 1;
	for (int i = n; i > n - k; i--) {
		up = (up * i) % MOD;
	}
	int down = 1;
	for (int i = k; i >= 1; i--) {
		down = (down * i) % MOD;
	}
	return up * qpow(down, MOD - 2, MOD) % MOD;
}

int v[MAX], prime[MAX], phi[MAX];
void euler(int n) {
	memset(v, 0, sizeof(v));
	int m = 0;
	for (int i = 2; i <= n; i++) {
		if (v[i] == 0) {
			v[i] = i, prime[++m] = i;
			phi[i] = i - 1;
		}
		for (int j = 1; j <= m; j++) {
			if (prime[j] > v[i] || prime[j] > n / i)
				break;
			v[i * prime[j]] = prime[j];
			phi[i * prime[j]] = phi[i]
					* (i % prime[j] ? prime[j] - 1 : prime[j]);
		}
	}
}

struct matrix {
	ll mat[32][32];
};

matrix operator *(matrix a, matrix b) {
	matrix res;
	for (int i = 0; i < 32; i++) {
		for (int j = 0; j < 32; j++) {
			res.mat[i][j] = 0;
			for (int k = 0; k < 32; k++) {
				res.mat[i][j] =
						(res.mat[i][j] + a.mat[i][k] * b.mat[k][j] % MOD) % MOD;
			}
		}
	}
	return res;
}

matrix qpow(matrix a, ll k) {
	matrix res;
	for (int i = 0; i < 32; i++) {
		for (int j = 0; j < 32; j++) {
			res.mat[i][j] = i == j;
		}
	}
	for (; k > 0; k >>= 1) {
		if ((k & 1) == 1) {
			res = a * res;
		}
		a = a * a;
	}
	return res;
}

int get(int i) {
	return __lg(i & (-i));
}

int mex(int i) {
	int res = 0;
	while (i & 1) {
		res++;
		i >>= 1;
	}
	return res;
}

int sg(int mask) {
	int res = 0;
	for (int i = 0;; i++) {
		res |= sg(i);
	}
	return 1 << (mex(res));
}
