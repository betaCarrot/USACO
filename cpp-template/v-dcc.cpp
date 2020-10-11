#include <bits/stdc++.h>

using namespace std;
#define ll long long
const int MAX = 505;
int M, cnt, root, num, dfn[MAX], low[MAX];
bool cut[MAX], v[MAX];
stack<int> s;
vector<int> dcc[MAX], edges[MAX];

void tarjan(int i) {
	dfn[i] = low[i] = ++num;
	s.push(i);
	if (i == root && edges[i].empty()) {
		dcc[++cnt].push_back(i);
		return;
	}
	int flag = 0;
	for (int j : edges[i]) {
		if (!dfn[j]) {
			tarjan(j);
			low[i] = min(low[i], low[j]);
			if (low[j] >= dfn[i]) {
				flag++;
				if (i != root || flag > 1)
					cut[i] = true;
				cnt++;
				int k;
				do {
					k = s.top();
					s.pop();
					dcc[cnt].push_back(k);
				} while (k != j);
				dcc[cnt].push_back(i);
			}
		} else {
			low[i] = min(low[i], dfn[j]);
		}
	}
}
