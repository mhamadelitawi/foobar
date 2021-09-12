package com.foobar.question4b;
//https://github.com/xphoniex/Google-Foobar/blob/master/running_with_bunnies_answer.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionFail {


    public static List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<>(), arr);
        return list;
    }


    public static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){

        // Base case
        if(resultList.size() == arr.length){
            list.add(new ArrayList<>(resultList));
        }
        else{
            for(int i = 0; i < arr.length; i++){

                if(resultList.contains(arr[i]))
                {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(arr[i]);
                // Explore
                permuteHelper(list, resultList, arr);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }


    public int[] getPossiblePath()
    {

        return null;
    }


    public  static   List<int[]>  permutation(int[] perm, int pos, int[] table , List<int[]> response) {

        if(response == null)
            response= new ArrayList<>();

        if (pos == perm.length) {
            //System.out.println(new String(perm));
            response.add(perm.clone());

        } else {
            for (int i = 0 ; i < table.length ; i++) {
                perm[pos] = table[i];
                response = permutation(perm, pos+1, table , response);
            }
        }

        return  response;
    }


    public static int[]  getThemAll(int bunniesNumber)
    {
        int[] result = new int[bunniesNumber];
        for(int i = 0 ;  i < bunniesNumber; i++)
            result[i] = i;
        return result;
    }


//    def convert_to_path(perm):
//    perm = list(perm)
//    perm = [0] + perm + [-1]
//    path = list()
//    for i in range(1, len(perm)):
//            path.append((perm[i - 1], perm[i]))
//            return path

    public static List<int[]> getPath(int[] line)
    {
        ArrayList<Integer> ar = new ArrayList();
        for(int i  = 0 ; i < line.length ; i++)
            ar.add(line[i]);//TODO : CHANGe

        ar.add(0 , 0 );
        ar.add(ar.size() , -1);

        List<int[]> path = new ArrayList();
        for(int i = 1 ; i < line.length ; i++ )
            path.add(new int[]{line[i - 1], line[i]});
        return path;
    }



    public static int[] solution (int[][] times, int times_limit)
    {

        int[] result = new int[]{};
        Graph graph = createGraph(times);
        if(graph.V < 3) return result;

        if (isNegCycleBellmanFord(graph, 0))
            return getThemAll(times.length-2);

        int valLength = times.length -1;
        int[] table = new int[valLength];
        for(int i = 0 ; i < valLength ; i++ )
            table[i] = i+1;


        for (int i = valLength ; i >-1 ; i--  )
        {
            // for perm in itertools.permutations(range(1, bunnies + 1), i):

            List<int[]> response = null;
            int[] perm = new int[i];
            response = permutation(  perm, 0,   table ,  response);
            for( int[] line :  response)
            {
                int time = 0;

                List<int[]> path = getPath( line);
                for (int[] choice :   path)
                    time += times[choice[0]][choice[1]];
                if (time <= times_limit)
                {
                    for( int x = 0 ; x < line.length ; x++)
                        line[x] = line[x] - 1;
                    Arrays.sort(line);
                    return line;
                    //  return sorted(list(i - 1 for i in perm))
                }

            }




        }



        return null;
    }



    public static void main(String[] args)
    {

        int[][] input = new int[][]{{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};
        int[] res  =  SolutionFail.solution(input , 3)   ;

        System.out.println(res);


//        int[] table = new int [] {1 , 2 , 3 };
//        List<int[]> response = null;
//        int[] perm = new int[3];
//        response = permutation(  perm, 0,   table ,  response);
//
//        for(int[] x : response)
//        {
//            for(int i = 0 ; i < x.length ; i++)
//                System.out.print(x[i]);
//            System.out.println();
//        }


    }



    public static Graph createGraph(int[][] times) {
        Graph graph = new Graph();
        graph.V = times.length;
        graph.E = times.length * (times.length-1);
        graph.edge = new Edge[graph.E];

//        for (int i = 0; i < graph.E; i++) {
//            graph.edge[i] = new Edge();
//        }

        int z = 0 ;
        for(int i = 0 ; i < times.length ; i++)
        {
            for(int j = 0 ; j < times.length ; j++)

            {
                if(i==j) continue;
                graph.edge[z] = new Edge(i , j , times[i][j]);
                z++;
            }
        }


        return graph;
    }



    static boolean isNegCycleBellmanFord(Graph graph, int src) {
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];

        // Step 1: Initialize distances from src
        // to all other vertices as INFINITE
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Step 2: Relax all edges |V| - 1 times.
        // A simple shortest path from src to any
        // other vertex can have at-most |V| - 1
        // edges
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Step 3: check for negative-weight cycles.
        // The above step guarantees shortest distances
        // if graph doesn't contain negative weight cycle.
        // If we get a shorter path, then there
        // is a cycle.
        for (int i = 0; i < E; i++) {
            int u = graph.edge[i].src;
            int v = graph.edge[i].dest;
            int weight = graph.edge[i].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                return true;
        }

        return false;
    }



    static int[] getPossiblePath(int start, int goal, int time_limit)
    {
        return null;
    }








}


class Edge {
    public int src, dest, weight;

    public Edge() {
    }

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {

    // V-> Number of vertices, E-> Number of edges
    int V, E;

    // graph is represented as an array of edges.
    Edge edge[];

}

class GFG {






    // a structure to represent a weighted edge in graph
    // a structure to represent a connected, directed and
    // weighted graph

    // Creates a graph with V vertices and E edges
    static Graph createGraph(int V, int E) {
        Graph graph = new Graph();
        graph.V = V;
        graph.E = E;
        graph.edge = new Edge[graph.E];

        for (int i = 0; i < graph.E; i++) {
            graph.edge[i] = new Edge();
        }





        return graph;
    }

    // The main function that finds shortest distances
    // from src to all other vertices using Bellman-
    // Ford algorithm. The function also detects
    // negative weight cycle


    // Driver Code
    public static void main(String[] args) {
//        int V = 5, E = 8;
//        Graph graph = createGraph(V, E);
//
//        // add edge 0-1 (or A-B in above figure)
//        graph.edge[0].src = 0;
//        graph.edge[0].dest = 1;
//        graph.edge[0].weight = -1;
//
//        // add edge 0-2 (or A-C in above figure)
//        graph.edge[1].src = 0;
//        graph.edge[1].dest = 2;
//        graph.edge[1].weight = 4;
//
//        // add edge 1-2 (or B-C in above figure)
//        graph.edge[2].src = 1;
//        graph.edge[2].dest = 2;
//        graph.edge[2].weight = 3;
//
//        // add edge 1-3 (or B-D in above figure)
//        graph.edge[3].src = 1;
//        graph.edge[3].dest = 3;
//        graph.edge[3].weight = 2;
//
//        // add edge 1-4 (or A-E in above figure)
//        graph.edge[4].src = 1;
//        graph.edge[4].dest = 4;
//        graph.edge[4].weight = 2;
//
//        // add edge 3-2 (or D-C in above figure)
//        graph.edge[5].src = 3;
//        graph.edge[5].dest = 2;
//        graph.edge[5].weight = 5;
//
//        // add edge 3-1 (or D-B in above figure)
//        graph.edge[6].src = 3;
//        graph.edge[6].dest = 1;
//        graph.edge[6].weight = 1;
//
//        // add edge 4-3 (or E-D in above figure)
//        graph.edge[7].src = 4;
//        graph.edge[7].dest = 3;
//        graph.edge[7].weight = -3;
//
//        if (isNegCycleBellmanFord(graph, 0))
//            System.out.println("Yes");
//        else
//            System.out.println("No");
    }
}

// This code is contributed by
// sanjeev2552


