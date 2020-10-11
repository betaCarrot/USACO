#include <bits/stdc++.h>

using namespace std;

struct edge {
	int to;
	int weight;
};

const int MAX = 1e5 + 5;
int N, M, dist[MAX];
bool vis[MAX];
vector<edge> edges[MAX];
priority_queue<pair<int, int>> q;

void dijkstra(int source) {
	memset(dist, 0x3f, sizeof(dist));
	memset(vis, false, sizeof(vis));
	dist[source] = 0;
	q.push(make_pair(0, source));
	while (!q.empty()) {
		int i = q.top().second;
		q.pop();
		if (vis[i])
			continue;
		vis[i] = true;
		for (edge e : edges[i]) {
			int j = e.to;
			int w = e.weight;
			if (dist[j] > dist[i] + w) {
				dist[j] = dist[i] + w;
				q.push(make_pair(-dist[j], j));
			}
		}
	}
}
