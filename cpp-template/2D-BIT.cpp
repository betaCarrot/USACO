#include <bits/stdc++.h>

using namespace std;

const int MAX = 2055;

int N, M, arr[4][MAX][MAX];

void add(int x, int y, int d) {
	for (int i = x; i <= N; i += i & (-i)) {
		for (int j = y; j <= M; j += j & (-j)) {
			arr[0][i][j] += d;
			arr[1][i][j] += d * x;
			arr[2][i][j] += d * y;
			arr[3][i][j] += d * x * y;
		}
	}
}

void range_add(int x1, int y1, int x2, int y2, int d) {
	add(x1, y1, d);
	add(x1, y2 + 1, -d);
	add(x2 + 1, y1, -d);
	add(x2 + 1, y2 + 1, d);
}

int query(int x, int y) {
	int res = 0;
	for (int i = x; i; i -= i & (-i)) {
		for (int j = y; j; j -= j & (-j)) {
			res += (x + 1) * (y + 1) * arr[0][i][j] - (y + 1) * arr[1][i][j]
					- (x + 1) * arr[2][i][j] + arr[3][i][j];
		}
	}
	return res;
}

int range_query(int x1, int y1, int x2, int y2) {
	return query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1)
			+ query(x1 - 1, y1 - 1);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	char c;
	int x1, y1, x2, y2, d;
	cin >> c >> N >> M;
	while (cin >> c) {
		cin >> x1 >> y1 >> x2 >> y2;
		if (c == 'L') {
			cin >> d;
			range_add(x1, y1, x2, y2, d);
		} else {
			cout << range_query(x1, y1, x2, y2) << endl;
		}
	}
	return 0;
}
