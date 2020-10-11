#include <bits/stdc++.h>

using namespace std;

const int MAX = 500005;
int N, M, S, depth[MAX], fa[MAX][20];
vector<int> edges[MAX];

void dfs(int i, int p) {
	fa[i][0] = p;
	depth[i] = depth[p] + 1;
	for (int j = 1; j < 20; j++) {
		fa[i][j] = fa[fa[i][j - 1]][j - 1];
	}
	for (int j : edges[i]) {
		if (j == p)
			continue;
		dfs(j, i);
	}
}

int query(int a, int b) {
	if (depth[a] < depth[b])
		swap(a, b);
	for (int j = 19; j >= 0; j--) {
		if (depth[fa[a][j]] >= depth[b])
			a = fa[a][j];
	}
	if (a == b)
		return a;
	for (int j = 19; j >= 0; j--) {
		if (fa[a][j] != fa[b][j]) {
			a = fa[a][j];
			b = fa[b][j];
		}
	}
	return fa[a][0];
}

int main() {
	scanf("%d%d%d", &N, &M, &S);
	for (int i = 1; i <= N - 1; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		edges[a].push_back(b);
		edges[b].push_back(a);
	}
	dfs(S, 0);
	while (M--) {
		int a, b;
		scanf("%d%d", &a, &b);
		printf("%d\n", query(a, b));
	}
	return 0;
}
