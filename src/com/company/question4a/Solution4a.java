package com.company.question4a;//https://yifan.lu/2017/09/13/foobar-blossoms-and-isomorphism/  <= chare777
//https://www.sanfoundry.com/java-program-implement-edmonds-algorithm-maximum-cardinality-matching/
//https://code.govanify.com/govanify/foobar/src/branch/master/distract_the_guards.py
//https://github.com/ivanseed/google-foobar-help/issues/10


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution4a {

    //https://github.com/ivanseed/google-foobar-help/issues/10


    static int getGCD(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }


//    def bananaGraph(banana_list):
//    G = {i: [] for i in range(len(banana_list))}
//    for i, a in enumerate(banana_list):
//            for j, b in enumerate(banana_list):
//            if i != j and willLoop(a, b):
//    G[i].append(j)
//    return G

    public static boolean isLoop(int a , int b) //using int instead of boolean because easier to count
    {
        int res = (a + b) / getGCD(a , b);
        return   (((res - 1) & res) != 0 )  ;


    }


     public static int solution(int[] banana_list){


         List<Integer>[] graph = new List[banana_list.length];

         for (int i = 0 ; i < banana_list.length ; i++)
         {
             graph[i] = new ArrayList<>();
             for(int j = 0 ; j < banana_list.length ; j++)
             {
                 if( i == j ) continue;

                 if(isLoop(banana_list[i] , banana_list[j]))
                     graph[i].add( j );
             }
         }

       return banana_list.length - EdmondsMaximumCardinalityMatching.maxMatching(graph)*2;
    }





    public static void main(String[] args)
    {
        System.out.println( Solution4a.solution(new int[]{ 1, 7, 3, 21, 13, 19}  ));
    }

}


class EdmondsMaximumCardinalityMatching
{
   static int lca(int[] match, int[] base, int[] p, int a, int b)
   {
       boolean[] used = new boolean[match.length];
       while (true)
       {
           a = base[a];
           used[a] = true;
           if (match[a] == -1)
               break;
           a = p[match[a]];
       }
       while (true)
       {
           b = base[b];
           if (used[b])
               return b;
           b = p[match[b]];
       }
   }

   static void markPath(int[] match, int[] base, boolean[] blossom, int[] p,
                        int v, int b, int children)
   {
       for (; base[v] != b; v = p[match[v]])
       {
           blossom[base[v]] = blossom[base[match[v]]] = true;
           p[v] = children;
           children = match[v];
       }
   }

   static int findPath(List<Integer>[] graph, int[] match, int[] p, int root)
   {
       int n = graph.length;
       boolean[] used = new boolean[n];
       Arrays.fill(p, -1);
       int[] base = new int[n];
       for (int i = 0; i < n; ++i)
           base[i] = i;
       used[root] = true;
       int qh = 0;
       int qt = 0;
       int[] q = new int[n];
       q[qt++] = root;
       while (qh < qt)
       {
           int v = q[qh++];
           for (int to : graph[v])
           {
               if (base[v] == base[to] || match[v] == to)
                   continue;
               if (to == root || match[to] != -1 && p[match[to]] != -1)
               {
                   int curbase = lca(match, base, p, v, to);
                   boolean[] blossom = new boolean[n];
                   markPath(match, base, blossom, p, v, curbase, to);
                   markPath(match, base, blossom, p, to, curbase, v);
                   for (int i = 0; i < n; ++i)
                       if (blossom[base[i]])
                       {
                           base[i] = curbase;
                           if (!used[i])
                           {
                               used[i] = true;
                               q[qt++] = i;
                           }
                       }
               }
               else if (p[to] == -1)
               {
                   p[to] = v;
                   if (match[to] == -1)
                       return to;
                   to = match[to];
                   used[to] = true;
                   q[qt++] = to;
               }
           }
       }
       return -1;
   }

   public static int maxMatching(List<Integer>[] graph)
   {
       int n = graph.length;
       int[] match = new int[n];
       Arrays.fill(match, -1);
       int[] p = new int[n];
       for (int i = 0; i < n; ++i)
       {
           if (match[i] == -1)
           {
               int v = findPath(graph, match, p, i);
               while (v != -1)
               {
                   int pv = p[v];
                   int ppv = match[pv];
                   match[v] = pv;
                   match[pv] = v;
                   v = ppv;
               }
           }
       }
       int matches = 0;
       for (int i = 0; i < n; ++i)
           if (match[i] != -1)
               ++matches;
       return matches / 2;
   }

   @SuppressWarnings("unchecked")
   public static void main(String[] args)
   {
       Scanner sc = new Scanner(System.in);
       //System.out.println("Enter the number of vertices: ");
      // int v = sc.nextInt();
       int v = 6;
      // System.out.println("Enter the number of edges: ");
      // int e = sc.nextInt();
       int e = 7;
       List<Integer>[] g = new List[v];
       for (int i = 0; i < v; i++)
       {
           g[i] = new ArrayList<Integer>();
       }
//        System.out.println("Enter all the edges: <from> <to>");
//        for (int i = 0; i < e; i++)
//        {
//            g[sc.nextInt()].add(sc.nextInt());
//        }

       g[0].add(1);
       g[1].add(2);
       g[1].add(3);
       g[3].add(4);
       g[4].add(5);
       g[5].add(3);
       g[5].add(2);


       System.out.println("Maximum matching for the given graph is: "
               + maxMatching(g));
       sc.close();
   }
}


