#include <bits/stdc++.h>

using namespace std;

const int MAX = 105;
int N, dist[MAX][MAX], d[MAX];
bool v[MAX];

int prim() {
	memset(d, 0x3f, sizeof(d));
	memset(v, false, sizeof(v));
	priority_queue<pair<int, int>> pq;
	d[1] = 0;
	int res = 0;
	pq.push(make_pair(0, 1));
	while (!pq.empty()) {
		int c = -pq.top().first;
		int i = pq.top().second;
		pq.pop();
		if (v[i])
			continue;
		v[i] = true;
		res += c;
		for (int j = 1; j <= N; j++) {
			if (i == j)
				continue;
			if (dist[i][j] < d[j]) {
				d[j] = dist[i][j];
				pq.push(make_pair(-dist[i][j], j));
			}
		}
	}
	return res;
}

int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> dist[i][j];
		}
	}
	cout << prim();
	return 0;
}
