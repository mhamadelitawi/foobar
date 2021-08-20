package com.company.question3a;

public class Solution3a {

   // https://livecodestream.dev/challenge/the-grandest-staircase-of-them-all/

//https://en.wikipedia.org/wiki/Partition_(number_theory)#Odd_parts_and_distinct_parts
    public static int solution(int n)
    {
        int [][] table = new int[n+1][n+1];
        table[0][0] = 1;

        for (int stair = 1 ; stair < n+1 ; stair++ )
        {
            for (int left = 0 ; left < n+1 ; left++)
            {
                table[stair][left] = table[stair-1][left];
                if(left >= stair)
                    table[stair][left]  +=  table[stair-1][left-stair];
            }
        }

        printTable(table);
        return table[n][n]-1;
    }

    public static void  printTable(int [][] table )
    {
        for(int i = 0 ; i < table.length ; i++)
        {
            for (int j= 0 ; j < table[0].length ; j++)
                System.out.print( make3Digit(table[i][j]+"")  +"  " );
            System.out.println();
        }
    }

    public static String make3Digit(String x)
    {
        if(x.length() == 3) return x;
        return make3Digit(x+" ");
    }


    // Driver code
    public static void main(String[] args)
    {


        System.out.println(   Solution3a.solution(  20 )   );
//        int N = 8;
//        int knightPos[] = { 1, 1 };
//        int targetPos[] = { 30, 30 };
//        System.out.println(
//                GFG.minStepToReachTarget(
//                        knightPos, targetPos, N));

//        System.out.println( Solution.solution( 19, 36));
    }

}
