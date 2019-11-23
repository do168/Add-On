package Fighting;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;



public class SE {
	static int[][] map;
	static int[][] map_search;
	static int[][] map_hazard;
	static boolean[][][] visit;
	static int row;
	static int col;
	static int start_x;
	static int start_y;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static String target = "";
	static String[][][] path;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(bfr.readLine());
		
		row = Integer.valueOf(str.nextToken());
		col = Integer.valueOf(str.nextToken());
		start_x = Integer.valueOf(str.nextToken());
		start_y = Integer.valueOf(str.nextToken()); // Map 크기, 시작 벡터 입력
		
		str = new StringTokenizer(bfr.readLine());
		int search_cnt = Integer.valueOf(str.nextToken()); // 탐색 위치 벡터 수 입력
		
		str = new StringTokenizer(bfr.readLine());
		int hazard_cnt = Integer.valueOf(str.nextToken()); // 위험 위치 벡터 수 입력
		
		map = new int[row+1][col+1];
		visit = new boolean[search_cnt][row+1][col+1];
		path = new String[search_cnt][row+1][col+1];
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				map[i][j] = 0;                   //일반 지역 = 0
				
				
			}
		}
		
		for (int k =0; k<search_cnt; k++) {
			for (int i=1; i<=row; i++) {
				for (int j=1; j<=col; j++) {
					                  
					visit[k][i][j] = false;
					path[k][i][j] = "";
				}
			}
		}
		
		
		for (int i=0; i<search_cnt; i++) {
			str = new StringTokenizer(bfr.readLine());
			int tempx = Integer.valueOf(str.nextToken());
			target = target + tempx;
			int tempy = Integer.valueOf(str.nextToken());
			map[tempx][tempy] = 1; // 탐색 지역 = 0
			target = target + tempy;
		}
		
		for (int i=0; i<hazard_cnt; i++) {
			str = new StringTokenizer(bfr.readLine());
			int tempx = Integer.valueOf(str.nextToken());
			int tempy = Integer.valueOf(str.nextToken());
			map[tempx][tempy] = 2;  // 위험 지역 = 0
		}
		
		for (int i=1; i<=row; i++) {
			for (int j=1; j<=col; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
		
		
		
		
		for (int i=0; i<search_cnt; i++) {
			visit[i][start_x][start_y] = true;
			path[i][start_x][start_y] = "(" + start_x + " , " + start_y + ")";
			bfs(i, start_x, start_y);
			int tmpx = Integer.parseInt(target.substring(2*i, 2*i+1));
			int tmpy = Integer.parseInt(target.substring(2*i+1, 2*(i+1)));
			System.out.println(path[i][tmpx][tmpy]);
			
			start_x = tmpx;
			start_y = tmpy;
		}
		
	}
	
	public static void bfs(int step, int x, int y) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(x, y));
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i=0; i<4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				
				if (nx <= 0 || ny <= 0 || nx > row || ny > col) {
                    continue;
                }
                //이미 방문했던 점이면 건너뛰기
                if (visit[step][nx][ny] || map[nx][ny] == 2) {
                    continue;
                }
                
                q.add(new Node(nx, ny));
                
                path[step][nx][ny] = path[step][n.x][n.y] + "(" + nx + " , " + ny + ")";
                visit[step][nx][ny] = true;
			}
		}
	}
	

}

class Node {
	int x;
	int y;
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}