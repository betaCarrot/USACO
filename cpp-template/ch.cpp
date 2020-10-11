#include <bits/stdc++.h>

using namespace std;

const int MAX = 1e5 + 5;

struct point {
	double x, y;

	bool operator<(const point &rhs) const {
		if (x != rhs.x)
			return x < rhs.x;
		return y < rhs.y;
	}
} points[MAX];

double dist(point a, point b) {
	return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}

bool cw(point p, point q, point r) {
	return p.x * (q.y - r.y) - p.y * (q.x - r.x) + (q.x * r.y - r.x * q.y) < 0;
}

bool ccw(point p, point q, point r) {
	return p.x * (q.y - r.y) - p.y * (q.x - r.x) + (q.x * r.y - r.x * q.y) > 0;
}

int N;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> points[i].x >> points[i].y;
	}
	sort(points + 1, points + N + 1);
	stack<point> s1, s2;
	for (int i = 1; i <= N; i++) {
		if (s1.size() < 2) {
			s1.push(points[i]);
			continue;
		}
		while (true) {
			if (s1.size() == 1) {
				s1.push(points[i]);
				break;
			}
			point q = s1.top();
			s1.pop();
			point p = s1.top();
			if (cw(p, q, points[i])) {
				s1.push(q);
				s1.push(points[i]);
				break;
			}
		}
	}
	for (int i = 1; i <= N; i++) {
		if (s2.size() < 2) {
			s2.push(points[i]);
			continue;
		}
		while (true) {
			if (s2.size() == 1) {
				s2.push(points[i]);
				break;
			}
			point q = s2.top();
			s2.pop();
			point p = s2.top();
			if (ccw(p, q, points[i])) {
				s2.push(q);
				s2.push(points[i]);
				break;
			}
		}
	}
	double res = 0;
	while (s1.size() > 1) {
		point p = s1.top();
		s1.pop();
		point q = s1.top();
		res += dist(p, q);
	}
	while (s2.size() > 1) {
		point p = s2.top();
		s2.pop();
		point q = s2.top();
		res += dist(p, q);
	}
	printf("%.2lf", res);
	return 0;
}
