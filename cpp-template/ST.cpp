#include <bits/stdc++.h>

using namespace std;

const int MAX = 100005;
int N, M, arr[MAX], ST[18][MAX];

int main() {
	scanf("%d%d", &N, &M);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &arr[i]);
		ST[0][i] = arr[i];
	}
	for (int j = 1; j < 18; j++) {
		for (int i = 1; i <= N; i++) {
			ST[j][i] = max(ST[j - 1][i], ST[j - 1][i + (1 << (j - 1))]);
		}
	}
	while (M--) {
		int l, r;
		scanf("%d%d", &l, &r);
		int s = __lg(r - l + 1);
		printf("%d\n", max(ST[s][l], ST[s][r - (1 << s) + 1]));
	}
	return 0;
}
