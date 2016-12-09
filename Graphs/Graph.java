package Graphs;

import java.util.*;

// using Adjacency Matrix
public class Graph {
  private static int ARR_SIZE = 20;
  private int[][] matrix; // ROW = source; COL = destination
  private int node;
  private String[] key;

  public Graph() {
    matrix = new int[ARR_SIZE][ARR_SIZE];
    key = new String[ARR_SIZE];
    node = 0;
  }

  public void addNode(String src) {
    if (node + 1 >= ARR_SIZE) {
      ARR_SIZE *= 2;
      matrix = Arrays.copyOf(matrix, ARR_SIZE);
      for(int i = ARR_SIZE / 2; i < ARR_SIZE; i++) {
        matrix[i] = new int[ARR_SIZE];
        for(int j = 0; j < ARR_SIZE; j++) {
          matrix[i][j] = 0;
        }
      }
      for(int i = 0; i < ARR_SIZE; i++) {
        matrix[i] = Arrays.copyOf(matrix[i], ARR_SIZE);
      }
      key = Arrays.copyOf(key, ARR_SIZE);
    }
    key[node++] = src;
    for(int i = 0; i < node; i++) {
      matrix[i][node - 1] = 0;
      matrix[node - 1][i] = 0;
    }
  }

  public void addEdge(String src, String dst) {
    int sou = getIndex(src);
    int det = getIndex(dst);
    if (sou == det) {
      throw new IllegalArgumentException("Source and destination shouldn't be the same.");
    } else if (matrix[sou][det] != 0) {
      throw new IllegalStateException(src + " and " + dst + " is already connected.");
    }
    matrix[sou][det] = 1;
  }

  public void removeEdge(String src, String dst) {
    int sou = getIndex(src);
    int det = getIndex(dst);
    if (sou == det) {
      throw new IllegalArgumentException("Source and destination shouldn't be the same.");
    } else if (matrix[sou][det] != 1) {
      throw new IllegalStateException(src + " and " + dst + " is not connected in the first place.");
    }
    matrix[sou][det] = 0;
  }

  public boolean hasEdge(String src, String dst) {
    int sou = getIndex(src);
    int des = getIndex(dst);
    return matrix[sou][des] == 1;
  }

  public List<String> outEdges(String src) {
    int sou = getIndex(src);
    List<String> lst = new ArrayList<>();
    for(int i = 0; i < node; i++) {
      if (matrix[sou][i] == 1) {
        lst.add(getKey(i));
      }
    }
    return lst;
  }

  public List<String> inEdges(String src) {
    int sou = getIndex(src);
    List<String> lst = new ArrayList<>();
    for(int i = 0; i < node; i++) {
      if (matrix[i][sou] == 1) {
        lst.add(getKey(i));
      }
    }
    return lst;
  }

  public List<String> bfs(String src) {
    ArrayDeque<String> heap = new ArrayDeque<>();
    heap.add(src);
    return bfs(src, heap, new ArrayList<>());
  }

  private List<String> bfs(String src, ArrayDeque<String> heap, ArrayList<String> visited) {
    if (!(visited.contains(src))) {
      visited.add(src);
    }
    heap.removeFirst();
    if (visited.size() == node) {
      return visited;
    } else {
      List<String> neighbors = outEdges(src);
      int j = neighbors.size() - 1;
      for(int i = 0; j >= 0; i++, j--) {
        if (!(visited.contains(neighbors.get(0)))) {
          heap.add(neighbors.remove(0));
        } else {
          neighbors.remove(0);
        }
      }
      try {
        return bfs(heap.getFirst(), heap, visited);
      } catch (NoSuchElementException e) {
        return visited;
      }
    }
  }

  public List<String> dfs(String src) {
    Stack<String> heap = new Stack<>();
    heap.push(src);
    return dfs(src, heap, new ArrayList<>());
  }

  private List<String> dfs(String src, Stack<String> heap, ArrayList<String> visited) {
    if (!(visited.contains(src))) {
      visited.add(src);
    }
    heap.pop();
    if (visited.size() == node) {
      return visited;
    } else {
      List<String> neighbors = outEdges(src);
      int j = neighbors.size() - 1;
      for(int i = 0; j >= 0; i++, j--) {
        if (!(visited.contains(neighbors.get(0)))) {
          heap.push(neighbors.remove(0));
        } else {
          neighbors.remove(0);
        }
      }
      try {
        return dfs(heap.peek(), heap, visited);
      } catch (EmptyStackException e) {
        return visited;
      }
    }
  }

  private int getIndex(String src) {
    for (int i = 0; i < node; i++) {
      if (src.equals(key[i])) {
        return i;
      }
    }
    throw new NoSuchElementException(src + " not found");
  }

  private String getKey(int i) {
    return key[i];
  }

  public String toString() {
    String str = "";
    for(int i = 0; i < node; i++) {
      for (int j = 0; j < node; j++) {
        str += matrix[i][j] + " ";
      }
      str += "\n";
    }
    return str;
  }
}
