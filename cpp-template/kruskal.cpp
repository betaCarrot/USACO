#include <bits/stdc++.h>

using namespace std;

const int MAX = 105;
int N, M, fa[MAX], dist[MAX][MAX];

struct edge {
	int x, y, z;

	bool operator<(const edge &rhs) const {
		return z < rhs.z;
	}
} edges[MAX * MAX];

void init() {
	for (int i = 1; i <= N; i++) {
		fa[i] = i;
	}
}

int find(int x) {
	if (x == fa[x])
		return x;
	return fa[x] = find(fa[x]);
}

void join(int x, int y) {
	fa[find(x)] = find(y);
}

int kruskal() {
	init();
	sort(edges + 1, edges + M + 1);
	int res = 0;
	for (int i = 1; i <= M; i++) {
		if (find(edges[i].x) == find(edges[i].y))
			continue;
		join(edges[i].x, edges[i].y);
		res += edges[i].z;
	}
	return res;
}

int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> dist[i][j];
			if (i != j)
				edges[++M] = edge { i, j, dist[i][j] };
		}
	}
	cout << kruskal();
	return 0;
}
