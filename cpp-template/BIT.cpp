const int MAX = 500005;
int N, arr[MAX];

void add(int i, int d) {
	for (; i <= N; i += i & (-i)) {
		arr[i] += d;
	}
}

int query(int i) {
	int res = 0;
	for (; i; i -= i & (-i)) {
		res += arr[i];
	}
	return res;
}

int lower_bound(int x) {
	int t = 0;
	for (int i = 24; i >= 0; i--) {
		t |= (1 << i);
		if (t > N || arr[t] >= x) {
			t -= (1 << i);
		} else {
			x -= arr[t];
		}
	}
	return t + 1;
}
