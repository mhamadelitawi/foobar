package com.company.question4b;
//https://github.com/xphoniex/Google-Foobar/blob/master/running_with_bunnies_answer.java

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

public class Solution4b {

    /*
    public static int[] solution (int[][] times, int times_limit) {


        if(times.length < 3) return new int[]{};

        int[][] min = applyBellmanFord( times );

        if(negativeCycle(min , times) )
            getThemAll(times.length - 2);


        return tryDFS( times_limit ,  min );
//        List<List<Integer>> subsets = getAllSubsets(getThemAll(times.length - 2));
//
//        int source;
//        int time;
//
//        for (int index = 0; index < subsets.size(); index++)
//        {
//            set = new HashSet();
//            permutations = new ArrayList();
//
//            permute(subsets, 0, index);
//
//            for (int p = 0 ; p < permutations.size(); p++ ) {
//
//                source = 0;
//                time = times_limit;
//
//                List<Integer> perm = permutations.get(p);
//
//                for (int i = 0; i < perm.size(); i++) {
//
//                    time -= min[source][perm.get(i)+1];
//                    source = perm.get(i)+1;
//
//                }
//
//                time -= min[source][times.length-1];
//
//                if (time >= 0) {
//
//                    int[] result = new int[subsets.get(index).size()];
//
//                    for (int r = 0 ; r < subsets.get(index).size(); r++)
//                        result[r] = subsets.get(index).get(r);
//
//                    return result;
//                }
//
//            }
//
//        }
//
//        int[] result = new int[0];
//
//        return result;
    }

    */

    public static int[]  getThemAll(int bunniesNumber)
    {
        int[] result = new int[bunniesNumber];
        for(int i = 0 ;  i < bunniesNumber; i++)
            result[i] = i;
        return result;
    }

    static Set<List<Integer>> set = new HashSet();
    static List<List<Integer>> permutations = new ArrayList();

    public static void permute(List<List<Integer>> numbers, int pos, int index) {

        List<Integer> curNumbers = numbers.get(index);

        for (int i = pos; i < curNumbers.size(); i++) {

            Collections.swap(curNumbers, i, pos);

            if (!set.contains(curNumbers)) {

                List<Integer> new_perm = new ArrayList (curNumbers);

                set.add(curNumbers);
                permutations.add(new_perm);
            }

            permute(numbers, pos+1, index);

            Collections.swap(curNumbers, pos, i);
        }


    }

   public static List<List<Integer>> getAllSubsets(int[] list)
    {

        List<List<Integer>> result = new ArrayList();

        int n = list.length;
        for (int i = 0; i < (1<<n); i++)
        {
            List<Integer>  cur = new ArrayList<>();
            for (int j = 0; j < n; j++)
                if ((i & (1 << j)) > 0)
                    cur.add( list[j] );
            result.add(cur);
        }

        Collections.sort(result, new ListCompare());
        return result;
    }

    public static int[][] applyBellmanFord(int[][] times)
    {
        int[][] min = new int[times.length][times.length];





        for (int z = 0; z < times.length; z++)
        {
            for (int source = 0; source < times.length; source++)
            {
                if (z==0) {
                    for (int i=0; i<times.length; ++i)
                        min[source][i] = Integer.MAX_VALUE;
                    min[source][source] = 0;
                }

                for (int i=0; i<times.length; ++i)
                    for (int j=0; j<times.length; ++j)
                        if (min[source][i] != Integer.MAX_VALUE && min[source][i] + times[i][j] < min[source][j])
                            min[source][j] = min[source][i] + times[i][j];
            }
        }

        return min;
    }

    public static boolean negativeCycle(int min[][] , int[][] times)
    {
        for (int src = 0; src < times.length; src++)
        {
            for (int i=0; i<times.length; ++i)
            {
                for (int j=0; j<times.length; ++j)
                {
                    if (min[src][i] + times[i][j] < min[src][j]) return true;
                }
            }
        }
        return false;
    }


    static int size;
    static List < Integer > ans;
    public static void dfs(int u, int time, int[][] times, List < Integer > list, boolean[] visited) {

        int n = times.length;
        if (time <= -999 || (u == n - 1 && time < 0) || (size == n - 2)) {
            return;
        }
        if (time >= 0 && u == n - 1) {
            if (list.size() > size) {
                ans = new ArrayList < >(list);
                size = list.size();
            }
            return;
        }
        if (visited[u]) {
            return;
        }
        visited[u] = true;
        list.add(u - 1);
        for (int v = 1; v < n; ++v) {
            if (v == u) {
                continue;
            }
            dfs(v, time - times[u][v], times, list, visited);
        }
        list.remove(list.size() - 1);
        visited[u] = false;
    }



    static int[] tryDFS(int times_limit , int[][] times )
    {
        size = 0;
        ans = new ArrayList < >();
        boolean[] visited = new boolean[times.length];
        visited[0] = true;
        for (int i = 1; i < times.length - 1; i++) {
            dfs(i, times_limit - times[0][i], times, new ArrayList < >(), visited);
        }
        if (ans.size() == 0) {
            return new int[] {};
        }
        int[] ret = new int[ans.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ans.get(i);
        }
        Arrays.sort(ret);
        return ret;
    }



    public static void main(String[] args)
    {

         int[][] input = new int[][]{{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};
        int[] res  =  Solution4b.solution(input , 3)   ;

        for(int i : res)
            System.out.print(i + " ");

        System.out.println();


        input = new int[][]{{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}};
        res  =  Solution4b.solution(input , 1)   ;

        for(int i : res)
            System.out.print(i + " ");

       // System.out.println(res);


    }


//    static int size;
//    static List < Integer > ans;
    public static int[] solution(int[][] times, int times_limit) {
        // Your code here
        int n = times.length;
        if (n <= 2 || (n != times[0].length)) {
            return new int[] {};
        }
        boolean negCycle = check(times, n);
        if (negCycle) {
            int[] ans = new int[n - 2];
            for (int i = 0; i < n - 2; i++) {
                ans[i] = i ;
            }
            return ans;
        } else {
            size = 0;
            ans = new ArrayList < >();
            boolean[] visited = new boolean[n];
            visited[0] = true;
            for (int i = 1; i < n - 1; i++) {
                dfs(i, times_limit - times[0][i], times, new ArrayList < >(), visited);
            }
            if (ans.size() == 0) {
                return new int[] {};
            }
            int[] ret = new int[ans.size()];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = ans.get(i);
            }
            Arrays.sort(ret);
            return ret;
        }
    }


    public static boolean check(int[][] times, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (times[i][k] + times[k][j] < times[i][j]) {
                        times[i][j] = times[i][k] + times[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (times[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
























}



class ListCompare implements Comparator<List<Integer>> {
    @Override
    public int compare(List<Integer> list1, List<Integer> list2) {
        if(list2.size() > list1.size())
            return 1;

        if(list2.size() < list1.size())
            return -1;

        int val = 0;
        for(int i =0 ; i < list1.size() ; i++)
        {
            val = list1.get(i).compareTo(list2.get(i));
            if(val != 0)
                return val;
        }
        return 0;
    }
}