package com.foobar.question4a;

import java.util.ArrayList;
import java.util.HashMap;


public class SolutionFailure {

    //https://github.com/ivanseed/google-foobar-help/issues/10


    static int getGCD(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }


    public static int isLoop(int a , int b) //using int instead of boolean because easier to count
    {
        int res = (a + b) / getGCD(a , b);
        res =  (((res - 1) & res) != 0 )  ? 2 : 0  ;
        return res;
    }



    public static ArrayList<Integer> getArray(int[] list)
    {
        ArrayList<Integer> array = new ArrayList();
       for(int i : list)
           array.add(i);
       return array;
    }


    public static ArrayList<Integer> getSubArray(ArrayList<Integer> array , int i , int j)
    {
        if(i > j ) return getSubArray( array , j , i);
        ArrayList<Integer> res = new ArrayList<>(array);
        res.remove(j);
        res.remove(i);
        return res;
    }


    public static HashMap<ArrayList<Integer> , Integer> hashMap = new HashMap<>();




    public static int getMax(ArrayList<Integer> array)
    {

        if(array.size() == 0) return 0;

        if(array.size() == 1) return 0;

        if(hashMap.get(array) != null)
        {
            return hashMap.get(array);
        }



        int count =0;
        int maxCount = 0;

        for (int i = 0 ; i < array.size() ; i++)
        {
            for(int j = 0 ; j < array.size() ; j++)
            {
                if(i == j) continue; // same element]
                count =  isLoop(array.get(i) , array.get(j) )  + getMax( getSubArray( array ,  i ,  j));
                maxCount = Math.max(count , maxCount);
            }


        }

        hashMap.put(array , maxCount);
        return maxCount;
    }


     public static int solution(int[] banana_list){

       return banana_list.length - getMax(  getArray( banana_list)  );
    }

    public static void main(String[] args)
    {

       // System.out.println( Solution.solution(new int[]{ 1, 7, 3, 21, 13, 19 , 3, 7}  ));
     //   System.out.println(Solution.isLoop(3, 3));
    }






}
