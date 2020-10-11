#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to, weight;
};

const int MAX = 2005;
int N, M, dist[MAX], v[MAX], cnt[MAX];
vector<edge> edges[MAX];

bool spfa(int source) {
	memset(dist, 0x3f, sizeof(dist));
	memset(v, false, sizeof(v));
	memset(cnt, 0, sizeof(cnt));
	queue<int> q;
	dist[source] = 0;
	v[source] = true;
	cnt[source] = 0;
	q.push(source);
	while (!q.empty()) {
		int i = q.front();
		q.pop();
		v[i] = false;
		for (edge e : edges[i]) {
			int j = e.to;
			int w = e.weight;
			if (dist[j] > dist[i] + w) {
				dist[j] = dist[i] + w;
				cnt[j] = cnt[i] + 1;
				if (cnt[j] >= N)
					return true;
				if (!v[j]) {
					q.push(j);
					v[j] = true;
				}
			}
		}
	}
	return false;
}
