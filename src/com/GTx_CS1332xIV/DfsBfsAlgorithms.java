package GTx_CS1332xIV;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class DfsBfsAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        HashSet<Vertex<T>> seen = new HashSet<Vertex<T>>();
        ArrayDeque<Vertex<T>> queue = new ArrayDeque<Vertex<T>>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjListMap = graph.getAdjList();
        ArrayList<Vertex<T>> result = new ArrayList<>();
        queue.addLast(start);
        seen.add(start);

        while (!queue.isEmpty()){
            Vertex<T> cur = queue.removeFirst();
            result.add(cur);
            for (VertexDistance<T> vd: adjListMap.get(cur)){
                Vertex<T> neighbor = vd.getVertex();
                if (!seen.contains(neighbor)){
                    queue.add(neighbor);
                    seen.add(neighbor);
                }
            }
        }
        return result;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        HashSet<Vertex<T>> seen = new HashSet<Vertex<T>>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjListMap = graph.getAdjList();
        ArrayList<Vertex<T>> result = new ArrayList<>();
        _dfs(start, result, seen, adjListMap);
        return result;
   }

   private static <T> void _dfs(Vertex<T> current, List<Vertex<T>> results, HashSet<Vertex<T>> seen, Map<Vertex<T>, List<VertexDistance<T>>> map){
       results.add(current);
       seen.add(current);
       for (VertexDistance<T> vd: map.get(current)){
           Vertex<T> v = vd.getVertex();
           if (!seen.contains(v)){
               _dfs(v, results, seen, map);
           }
       }
   }
}

/*

[Test Failure: bfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, d, c]
	Actual : [a, b, d, c, c]

[Test Failure: bfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b]
	Actual : [a, b, b]

[Test Failure: bfs] [-0.38] : Unexpected content in search list.
	Expected : [b, c, d]
	Actual : [b, c, d, d]

[Test Failure: bfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, d, f, e, g]
	Actual : [a, b, c, d, f, e, g, e, g]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [c, b, a, d]
	Actual : [c, d, b, a]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, d]
	Actual : [a, d, c, b, b]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b]
	Actual : [a, b, b]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [b, c, d]
	Actual : [b, d, c, c]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, d, e, f, g]
	Actual : [a, b, c, f, e, g, d, d, d]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, e, d, f]
	Actual : [a, b, d, f, e, c]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, f, e, d]
	Actual : [a, f, e, d, b, c]

[Test Failure: dfs] [-0.38] : Unexpected content in search list.
	Expected : [a, b, c, d, e, f, g]
	Actual : [a, g, b, f, e, c, d, c]

[Test Failure: unmodified] [-0.38] : Graph structure could not be validated for the following method(s) due to early test failure(s): dfs, bfs.
 */