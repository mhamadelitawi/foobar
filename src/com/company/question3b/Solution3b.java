package com.company.question3b;

public class Solution3b {

     public static int solution(int[] l){
        int table[] = new int[l.length];
        int ret = 0;

        for(int i = 0 ; i < table.length ; i++)
        {
            for (int j = 0 ; j < i ; j++)
            {
                if (l[i] % l[j] == 0)
                {
                    table[i] = table[i] + 1;
                    ret +=  table[j];
                }
            }
        }
        return ret;
    }

    public static void main(String[] args)
    {

        System.out.println( Solution3b.solution(new int[]{ 1 , 1 , 1  }  ));
    }

}
