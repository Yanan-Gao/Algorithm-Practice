package AmazonOA;
import java.util.*;
public class Robot {

    class Point {
        int x, y, l;
        public Point(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        int[][] length = new int[row][col]; // record length
        for (int[] arr : length) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<Point> list = new LinkedList<>(); //没必要用PQ。直接用queue就行了
        list.offer(new Point(start[0], start[1], 0));
        while (!list.isEmpty()) {
            Point p = list.poll();
            if (length[p.x][p.y] <= p.l) continue; //这里类似于用了dp。记录了当前最小值，如果后边有人到了这里并且距离大，直接放弃
            length[p.x][p.y] = p.l;
            for (int i = 0; i < 4; i++) {
                int x = p.x, y = p.y, l = p.l;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0) {
                    x += dir[i][0];
                    y += dir[i][1];
                    l++;
                }
                x -= dir[i][0];
                y -= dir[i][1];
                l--;
                list.offer(new Point(x, y, l));
            }
        }


        return length[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : length[destination[0]][destination[1]];
    }



}
