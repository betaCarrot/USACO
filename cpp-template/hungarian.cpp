#include <bits/stdc++.h>

using namespace std;

const int MAX = 1005;
int N, M, E, match[MAX];
bool vis[MAX];
vector<int> edges[MAX];

bool dfs(int i) {
	for (int j : edges[i]) {
		if (!vis[j]) {
			vis[j] = true;
			if (!match[j] || dfs(match[j])) {
				match[j] = i;
				return true;
			}
		}
	}
	return false;
}

int main() {
	cin >> N >> M >> E;
	for (int i = 1; i <= E; i++) {
		int a, b;
		cin >> a >> b;
		if (a > N || b > M)
			continue;
		edges[a].push_back(b);
	}
	int res = 0;
	for (int i = 1; i <= N; i++) {
		memset(vis, false, sizeof(vis));
		if (dfs(i))
			res++;
	}
	cout << res;
	return 0;
}
