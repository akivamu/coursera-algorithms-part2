# Undirected Graphs

  - Vertex (aka Node, Point)  
  - Edge (aka Arc, Line) connecting vertices  
  - Path: sequence of vertices connected by edges  
  - Cycle: is path which first vertex is also last vertex  

## How to store a graph

### 1. List of edges  
Maintain a list of edges  

### 2. Adjacent matrix
Maintain a matrix `adj[V][V]`  
For each edge of `v1` and `v2`, `adj[v1][v2] == adj[v2][v1] == true`  

### 3. Adjacent list
Maintain a list `adj[V]`. Item at `adj[v1]` is a list of all adjacent vertices to `v1`  

![](docs/graph-representation.png)

## Depth-first search vs Breadth-first search  

[Good comparision DFS vs BFS](https://www.thecrazyprogrammer.com/2017/06/difference-between-bfs-and-dfs.html)

## Find connected components  
Use DFS to identify all components in graph.  
Why DFS: Save memory (linear to depth)  


# Directed Graphs (digraph)

## How to store a digraph  
Similar to Undirected Graph, using Adjacent list. But the edge in Bag are one-way.

