#include <bits/stdc++.h>

using namespace std;

const int MAX = 1e4 + 5;
int N, M, fa[MAX], size[MAX];

void init() {
	for (int i = 1; i <= N; i++) {
		fa[i] = i;
	}
	for (int i = 1; i <= N; i++) {
		size[i] = 1;
	}
}

int find(int x) {
	if (x == fa[x])
		return x;
	return fa[x] = find(fa[x]);
}

void join2(int x, int y) {
	fa[find(x)] = find(y);
}

void join(int x, int y) {
	int root1 = find(x);
	int root2 = find(y);
	if (size[root1] > size[root2]) {
		//swap(root1, root2);
	}
	if (root1 != root2) {
		fa[root1] = root2;
		size[root2] += size[root1];
	}
}

int main() {
	cin >> N >> M;
	init();
	for (int i = 1; i <= M; i++) {
		int t, x, y;
		cin >> t >> x >> y;
		if (t == 1) {
			join(x, y);
		} else {
			if (find(x) == find(y))
				cout << "Y" << endl;
			else
				cout << "N" << endl;
		}
	}
}
