#include <bits/stdc++.h>

using namespace std;

#define ll long long

const int MAX = 100005;

struct node {
	int l, r;
	ll sum, add;
} tree[MAX * 4];

int N, M;
ll arr[MAX];

void build(int p, int l, int r) {
	tree[p].l = l;
	tree[p].r = r;
	if (l == r) {
		tree[p].sum = arr[l];
		return;
	}
	int mid = (l + r) / 2;
	build(p * 2, l, mid);
	build(p * 2 + 1, mid + 1, r);
	tree[p].sum = tree[p * 2].sum + tree[p * 2 + 1].sum;
}

void spread(int p) {
	if (tree[p].add) {
		tree[p * 2].sum += tree[p].add * (tree[p * 2].r - tree[p * 2].l + 1);
		tree[p * 2 + 1].sum += tree[p].add
				* (tree[p * 2 + 1].r - tree[p * 2 + 1].l + 1);
		tree[p * 2].add += tree[p].add;
		tree[p * 2 + 1].add += tree[p].add;
		tree[p].add = 0;
	}
}

void add(int p, int l, int r, ll v) {
	if (l <= tree[p].l && r >= tree[p].r) {
		tree[p].sum += (tree[p].r - tree[p].l + 1) * v;
		tree[p].add += v;
		return;
	}
	spread(p);
	int mid = (tree[p].l + tree[p].r) / 2;
	if (l <= mid)
		add(p * 2, l, r, v);
	if (r > mid)
		add(p * 2 + 1, l, r, v);
	tree[p].sum = tree[p * 2].sum + tree[p * 2 + 1].sum;
}

ll query(int p, int l, int r) {
	if (l <= tree[p].l && r >= tree[p].r) {
		return tree[p].sum;
	}
	spread(p);
	ll res = 0;
	int mid = (tree[p].l + tree[p].r) / 2;
	if (l <= mid)
		res += query(p * 2, l, r);
	if (r > mid)
		res += query(p * 2 + 1, l, r);
	return res;
}

int main() {
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	build(1, 1, N);
	for (int i = 1; i <= M; i++) {
		int t, l, r, k;
		cin >> t >> l >> r;
		if (t == 1) {
			cin >> k;
			add(1, l, r, k);
		} else {
			cout << query(1, l, r) << endl;
		}
	}
}
