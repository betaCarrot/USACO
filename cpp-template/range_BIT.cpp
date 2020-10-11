#include <bits/stdc++.h>

using namespace std;

const int MAX = 1e5 + 5;
int N, M, arr[2][MAX];

void add(int x, int d) {
	for (int i = x; i <= N; i += i & (-i)) {
		arr[0][i] += d;
		arr[1][i] += d * x;
	}
}

void range_add(int l, int r, int d) {
	add(l, d);
	add(r + 1, -d);
}

int query(int x) {
	int res = 0;
	for (int i = x; i; i -= i & (-i)) {
		res += (x + 1) * arr[0][i] - arr[1][i];
	}
	return res;
}

int range_query(int l, int r) {
	return query(r) - query(l - 1);
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		int d;
		cin >> d;
		range_add(i, i, d);
	}
	while (M--) {
		int t, x, y, k;
		cin >> t >> x >> y;
		if (t == 1) {
			cin >> k;
			range_add(x, y, k);
		} else {
			cout << range_query(x, y) << endl;
		}
	}
}
