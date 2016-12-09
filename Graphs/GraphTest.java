package Graphs;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class GraphTest {
  private Graph graph;

  @Before
  public void setUp() {
    graph = new Graph();
    graph.addNode("Manila");
    graph.addNode("Cebu");
    graph.addNode("Clark");
    graph.addNode("Batangas");
  }

  @Test
  public void addNode() throws Exception {
    assertEquals(graph.toString(),
                    "0 0 0 0 \n" +
                    "0 0 0 0 \n" +
                    "0 0 0 0 \n" +
                    "0 0 0 0 \n");
  }

  @Test
  public void addValidEdge() throws Exception {
    graph.addEdge("Manila", "Batangas");assertEquals(graph.toString(),
                    "0 0 0 1 \n" +
                    "0 0 0 0 \n" +
                    "0 0 0 0 \n" +
                    "0 0 0 0 \n");
  }

  @Test
  public void addInvalidEdge() throws Exception {
    try {
      graph.addEdge("Cebu", "Cebu");
      fail("Should've thrown an exception!");
    } catch (IllegalArgumentException e) {
      // expected!
    }
  }

  @Test
  public void removeValidEdge() throws Exception {
    graph.addEdge("Clark", "Manila");
    graph.removeEdge("Clark", "Manila");
  }

  @Test
  public void removeInvalidEdge() throws Exception {
    graph.addEdge("Clark", "Manila");
    try {
      graph.removeEdge("Manila", "Clark");
      fail();
    } catch (IllegalStateException e) {
      // expected!
    }
  }

  @Test
  public void hasEdge() throws Exception {
    graph.addEdge("Manila", "Cebu");
    assertTrue(graph.hasEdge("Manila", "Cebu"));
    assertFalse(graph.hasEdge("Cebu", "Manila"));
  }

  @Test
  public void outEdges() throws Exception {
    graph.addEdge("Cebu", "Batangas");
    graph.addEdge("Cebu", "Clark");
    assertTrue(graph.outEdges("Cebu").contains("Clark"));
    assertTrue(graph.outEdges("Cebu").contains("Batangas"));
    assertFalse(graph.outEdges("Cebu").contains("Manila"));
  }

  @Test
  public void inEdges() throws Exception {
    graph.addEdge("Cebu", "Batangas");
    graph.addEdge("Manila", "Batangas");
    assertTrue(graph.inEdges("Batangas").contains("Cebu"));
    assertTrue(graph.inEdges("Batangas").contains("Manila"));
    assertFalse(graph.inEdges("Batangas").contains("Clark"));
  }

  @Test
  public void bfs() throws Exception {
    Graph g = new Graph();
    g.addNode("Manila");
    g.addNode("Cebu");
    g.addNode("Bacolod");
    g.addNode("Tagbilaran");
    g.addNode("Zamboanga");
    g.addNode("Tawi-Tawi");
    g.addNode("Naga");
    g.addNode("Basilan");
    g.addNode("Davao");
    g.addEdge("Manila", "Bacolod");
    g.addEdge("Bacolod", "Manila");
    g.addEdge("Manila", "Cebu");
    g.addEdge("Cebu", "Manila");
    g.addEdge("Manila", "Davao");
    g.addEdge("Davao", "Manila");
    g.addEdge("Manila", "Naga");
    g.addEdge("Naga", "Manila");
    g.addEdge("Manila", "Tagbilaran");
    g.addEdge("Tagbilaran", "Manila");
    g.addEdge("Manila", "Zamboanga");
    g.addEdge("Zamboanga", "Manila");
    g.addEdge("Bacolod", "Cebu");
    g.addEdge("Cebu", "Bacolod");
    g.addEdge("Davao", "Cebu");
    g.addEdge("Cebu", "Davao");
    g.addEdge("Zamboanga", "Cebu");
    g.addEdge("Cebu", "Zamboanga");
    g.addEdge("Davao", "Zamboanga");
    g.addEdge("Zamboanga", "Davao");
    g.addEdge("Zamboanga", "Tawi-Tawi");
    g.addEdge("Tawi-Tawi", "Zamboanga");
    List<String> ans = g.bfs("Manila");
    System.out.println("FINAL ANSWER:");
    while(ans.size() > 0) {
      System.out.println(ans.remove(0));
    }
  }

  @Test
  public void dfs() throws Exception {Graph g = new Graph();
    g.addNode("Manila");
    g.addNode("Cebu");
    g.addNode("Bacolod");
    g.addNode("Tagbilaran");
    g.addNode("Zamboanga");
    g.addNode("Tawi-Tawi");
    g.addNode("Naga");
    g.addNode("Basilan");
    g.addNode("Davao");
    g.addEdge("Manila", "Bacolod");
    g.addEdge("Bacolod", "Manila");
    g.addEdge("Manila", "Cebu");
    g.addEdge("Cebu", "Manila");
    g.addEdge("Manila", "Davao");
    g.addEdge("Davao", "Manila");
    g.addEdge("Manila", "Naga");
    g.addEdge("Naga", "Manila");
    g.addEdge("Manila", "Tagbilaran");
    g.addEdge("Tagbilaran", "Manila");
    g.addEdge("Manila", "Zamboanga");
    g.addEdge("Zamboanga", "Manila");
    g.addEdge("Bacolod", "Cebu");
    g.addEdge("Cebu", "Bacolod");
    g.addEdge("Davao", "Cebu");
    g.addEdge("Cebu", "Davao");
    g.addEdge("Zamboanga", "Cebu");
    g.addEdge("Cebu", "Zamboanga");
    g.addEdge("Davao", "Zamboanga");
    g.addEdge("Zamboanga", "Davao");
    g.addEdge("Zamboanga", "Tawi-Tawi");
    g.addEdge("Tawi-Tawi", "Zamboanga");
    List<String> ans = g.dfs("Manila");
    System.out.println("FINAL ANSWER:");
    while(ans.size() > 0) {
      System.out.println(ans.remove(0));
    }
  }
}