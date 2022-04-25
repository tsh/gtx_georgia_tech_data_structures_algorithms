package com.GTx_CS1332xIV;

import GTx_CS1332xIV.*;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String [] arguments)
    {
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        // v1 - e1 - v2 - e2 - v3
        //  |          \
        //  |           \- e3 - v4
        //  |                    |
        //  -------e5----------- /
        Edge<Integer> e1 = new Edge<>(v1, v2, 1);
        Edge<Integer> e2 = new Edge<>(v2, v3, 2);
        Edge<Integer> e3 = new Edge<>(v2, v4, 4);
        Edge<Integer> e4 = new Edge<>(v1, v1, 0);
        Edge<Integer> e5 = new Edge<>(v4, v1, 100);
        Edge<Integer> e6 = new Edge<>(v1, v4, 100);

        HashSet<Vertex<Integer>> vertices = new HashSet<>(Arrays.asList(v1, v2, v3, v4));
        HashSet<Edge<Integer>> edges = new HashSet<>(Arrays.asList(e1,e2,e3, e4, e5, e6));

        Graph<Integer> g = new Graph<Integer>(vertices, edges);

        System.out.println(GraphAlgorithms.prims(v1, g));
    }
}
