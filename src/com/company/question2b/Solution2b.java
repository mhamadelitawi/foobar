package com.company.question2b;

//BFS Algorithm

//https://www.geeksforgeeks.org/minimum-steps-reach-target-knight-set-2/
public class Solution2b {


        public  static int[] posToXY(int number)
        {
            int t[] = new int[2];
            t[0] = number / 8;
            t[1] = number % 8;

            return t;

        }

        public static int solution(int src , int dest){
            if(src == dest) return 0;

            return GFG.minStepToReachTarget( src , dest);

        }


        // Driver code
        public static void main(String[] args)
        {
//        int N = 8;
//        int knightPos[] = { 1, 1 };
//        int targetPos[] = { 30, 30 };
//        System.out.println(
//                GFG.minStepToReachTarget(
//                        knightPos, targetPos, N));

            System.out.println( Solution2b.solution( 19, 36));
        }

}

class GFG {

    static int dp[][] = new int[8][8];

    public  static int getsteps(int x, int y,
                                int tx, int ty) {

        if (x == tx && y == ty) {
            return dp[0][0];
        } else
        if (dp[ Math.abs(x - tx)][ Math.abs(y - ty)] != 0) {
            return dp[ Math.abs(x - tx)][ Math.abs(y - ty)];
        } else {


            int x1, y1, x2, y2;

            if (x <= tx) {
                if (y <= ty) {
                    x1 = x + 2;
                    y1 = y + 1;
                    x2 = x + 1;
                    y2 = y + 2;
                } else {
                    x1 = x + 2;
                    y1 = y - 1;
                    x2 = x + 1;
                    y2 = y - 2;
                }
            } else if (y <= ty) {
                x1 = x - 2;
                y1 = y + 1;
                x2 = x - 1;
                y2 = y + 2;
            } else {
                x1 = x - 2;
                y1 = y - 1;
                x2 = x - 1;
                y2 = y - 2;
            }


            dp[ Math.abs(x - tx)][ Math.abs(y - ty)]
                    = Math.min(getsteps(x1, y1, tx, ty),
                    getsteps(x2, y2, tx, ty)) + 1;


            dp[ Math.abs(y - ty)][ Math.abs(x - tx)]
                    = dp[ Math.abs(x - tx)][ Math.abs(y - ty)];
            return dp[ Math.abs(x - tx)][ Math.abs(y - ty)];
        }
    }

    public static int minStepToReachTarget (int src , int dest) {
        int i, n, x, y, tx, ty, ans;

        n = 8;
        x = src / n;
        y = src % n;
        tx = dest / n;
        ty = dest % n;


        if ((x == 1 && y == 1 && tx == 2 && ty == 2)
                || (x == 2 && y == 2 && tx == 1 && ty == 1)) {
            ans = 4;
        } else if ((x == 1 && y == n && tx == 2 && ty == n - 1)
                || (x == 2 && y == n - 1 && tx == 1 && ty == n)) {
            ans = 4;
        } else if ((x == n && y == 1 && tx == n - 1 && ty == 2)
                || (x == n - 1 && y == 2 && tx == n && ty == 1)) {
            ans = 4;
        } else if ((x == n && y == n && tx == n - 1 && ty == n - 1)
                || (x == n - 1 && y == n - 1 && tx == n && ty == n)) {
            ans = 4;
        } else {

            dp[1][0] = 3;
            dp[0][1] = 3;
            dp[1][1] = 2;
            dp[2][0] = 2;
            dp[0][2] = 2;
            dp[2][1] = 1;
            dp[1][2] = 1;

            ans = getsteps(x, y, tx, ty);
        }

        return ans;
    }

}
