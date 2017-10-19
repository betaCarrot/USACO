import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class cowrace {
	private static int result, status, lastStatus;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowrace.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrace.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayDeque<segment> bessie = new ArrayDeque<segment>();
		ArrayDeque<segment> elsie = new ArrayDeque<segment>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			bessie.offer(new segment(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			elsie.offer(new segment(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
		}
		int currentTime = 0;
		int bSpeed = 0;
		int eSpeed = 0;
		int bDistance = 0;
		int eDistance = 0;
		while (!bessie.isEmpty() && !elsie.isEmpty()) {
			segment bSegment = bessie.poll();
			bSpeed = bSegment.getSpeed();
			int bNextTime = bSegment.getTime();
			segment eSegment = elsie.poll();
			eSpeed = eSegment.getSpeed();
			int eNextTime = eSegment.getTime();
			if (bNextTime < eNextTime) {
				currentTime += bNextTime;
				bDistance += bNextTime * bSpeed;
				eDistance += bNextTime * eSpeed;
				elsie.addFirst(new segment(eSpeed, eNextTime - bNextTime));
			} else if (bNextTime > eNextTime) {
				currentTime += eNextTime;
				bDistance += eNextTime * bSpeed;
				eDistance += eNextTime * eSpeed;
				bessie.addFirst(new segment(bSpeed, bNextTime - eNextTime));
			} else {
				currentTime += eNextTime;
				bDistance += bNextTime * bSpeed;
				eDistance += eNextTime * eSpeed;
			}
			checkStatus(bDistance, eDistance);
		}
		out.println(result);
		out.close();
	}

	public static void checkStatus(int bDistance, int eDistance) {
		if (status == 1) {
			if (eDistance > bDistance) {
				result++;
				status = 2;
				lastStatus = 2;
			} else if (eDistance == bDistance) {
				status = 0;
				lastStatus = 1;
			}
		} else if (status == 2) {
			if (bDistance > eDistance) {
				result++;
				status = 1;
				lastStatus = 1;
			} else if (bDistance == eDistance) {
				status = 0;
				lastStatus = 2;
			}
		} else {
			if (bDistance > eDistance) {
				status = 1;
				if (lastStatus == 2)
					result++;
				lastStatus = 1;
			} else if (eDistance > bDistance) {
				status = 2;
				if (lastStatus == 1)
					result++;
				lastStatus = 2;
			}
		}
	}
}

class segment {
	int speed;
	int time;

	public segment(int s, int t) {
		speed = s;
		time = t;
	}

	public int getSpeed() {
		return speed;
	}

	public int getTime() {
		return time;
	}

	public String toString() {
		return speed + " " + time;
	}
}
