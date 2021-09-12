package com.foobar.question1;

import java.util.HashMap;

public class Solution1 {

    public static int solution(int[] x , int[] y){

        if(y.length > x.length)
            return (solution(y , x));

        HashMap<Integer , Integer> keys = new HashMap<>();
        for(int z : y)
            keys.put(z , 1);

        for (int z : x)
             if(keys.get(z) == null)
                 return z;

        return -1;
    }


    public static void main(String[] args) {

        int x[] = new int[] {13, 5, 6, 2, 5 };
        int y[] = new int[] {5, 2, 5, 13};
        System.out.println(  Solution1.solution(  x , y)   );


        x = new int[] { 14, 27, 1, 4, 2, 50, 3, 1 };
        y = new int[] { 2, 4, -4, 3, 1, 1, 14, 27, 50 };
        System.out.println(  Solution1.solution(  x , y)   );


        x = new int[] { 14, 27, 1, 4, 2, 50, 3, 1 };
        y = new int[] { 2, 4, -4, 3, 1, 1, 14, 27, 50 };
        System.out.println(  Solution1.solution(  x , y)   );


        x = new int[] { 13, 5, 6, 2, 5};
        y = new int[] { 5, 2, 5, 13 };
        System.out.println(  Solution1.solution(  x , y)   );


        y = new int[] {14, 27, 1, 4, 2, 50, 3, 1};
        x = new int[] {2, 4, -4, 3, 1, 1, 14, 27, 50};
        System.out.println(  Solution1.solution(  x , y)   );

        y = new int[] {1,2};
        x = new int[] {1};
        System.out.println(  Solution1.solution(  x , y)   );


    }

}
