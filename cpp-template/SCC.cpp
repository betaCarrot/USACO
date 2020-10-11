#include <bits/stdc++.h>

using namespace std;

const int MAX = 1e4 + 5;
int N, M, dfn[MAX], low[MAX], out[MAX], c[MAX], num, cnt;
bool v[MAX];
stack<int> s;
vector<int> edges[MAX], scc[MAX];

void tarjan(int i) {
	dfn[i] = low[i] = ++num;
	s.push(i);
	v[i] = true;
	for (int j : edges[i]) {
		if (!dfn[j]) {
			tarjan(j);
			low[i] = min(low[i], low[j]);
		} else if (v[j]) {
			low[i] = min(low[i], low[j]);
		}
	}
	if (dfn[i] == low[i]) {
		cnt++;
		int j;
		do {
			j = s.top();
			s.pop();
			v[j] = false;
			c[j] = cnt;
			scc[cnt].push_back(j);
		} while (i != j);
	}
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= M; i++) {
		int a, b;
		cin >> a >> b;
		edges[a].push_back(b);
	}
	for (int i = 1; i <= N; i++) {
		if (!dfn[i])
			tarjan(i);
	}
	for (int i = 1; i <= N; i++) {
		for (int j : edges[i]) {
			if (c[i] == c[j])
				continue;
			out[c[i]]++;
		}
	}
	int cnt2 = 0;
	int index = 0;
	for (int i = 1; i <= cnt; i++) {
		if (out[i] == 0) {
			cnt2++;
			index = i;
		}
	}
	if (cnt2 >= 2)
		cout << 0;
	else
		cout << scc[index].size();
	return 0;
}
