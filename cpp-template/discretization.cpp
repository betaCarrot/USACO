#include <bits/stdc++.h>

using namespace std;

const int MAX = 200005;
int N, a[MAX], b[MAX], c[MAX];

int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> a[i];
		b[i] = a[i];
	}
	sort(b + 1, b + N + 1);
	int m = unique(b + 1, b + N + 1) - b - 1;
	for (int i = 1; i <= N; i++) {
		a[i] = lower_bound(b + 1, b + m + 1, a[i]) - b;
		c[a[i]]++;
	}
	for (int i = 1; i <= m; i++) {
		cout << b[i] << " " << c[i] << endl;
	}
}
