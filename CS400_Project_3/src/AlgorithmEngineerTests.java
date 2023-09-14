import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlgorithmEngineerTests {
  private CS400Graph<Integer,Integer> graph;
  
  /**
   * Instantiate graph from GeeksForGeeks
   */
  @BeforeEach
  public void createGraph() {
      graph = new CS400Graph<>();
      // insert vertices A-F
      graph.insertVertex(0);
      graph.insertVertex(1);
      graph.insertVertex(2);
      graph.insertVertex(3);
      graph.insertVertex(4);
      graph.insertVertex(5);
      graph.insertVertex(6);
      graph.insertVertex(7);
      graph.insertVertex(8);
      
      graph.insertEdge(0,1,4);
      graph.insertEdge(1,0,4);
      
      graph.insertEdge(0,7,8);
      graph.insertEdge(7,0,8);
      
      graph.insertEdge(1,2,8);
      graph.insertEdge(2,1,8);
      
      graph.insertEdge(2,3,7);
      graph.insertEdge(3,2,7);
      
      graph.insertEdge(3,4,9);
      graph.insertEdge(4,3,9);
      
      graph.insertEdge(4,5,10);
      graph.insertEdge(4,5,10);
      
      graph.insertEdge(5,6,2);
      graph.insertEdge(6,5,2);
      
      graph.insertEdge(6,7,1);
      graph.insertEdge(7,6,1);
      
      graph.insertEdge(1,7,11);
      graph.insertEdge(7,1,11);
      
      graph.insertEdge(7,8,7);
      graph.insertEdge(8,7,7);
      
      graph.insertEdge(6,8,6);
      graph.insertEdge(8,6,6);
      
      graph.insertEdge(2,8,2);
      graph.insertEdge(8,2,2);
      
      graph.insertEdge(2,5,4);
      graph.insertEdge(5,2,4);
      
      graph.insertEdge(3,5,14);
      graph.insertEdge(5,3,14);
  }
  
  /**
   * Checks the correct vertices in the minimum spanning tree when starting node is 0
   */
  @Test
  public void testMSTVerticesStarting0() {
    CS400Graph<Integer,Integer> graphMST = graph.getMinSpanningTree(0);
    assertTrue(graphMST.containsVertex(0));
    assertTrue(graphMST.containsVertex(1));
    assertTrue(graphMST.containsVertex(2));
    assertTrue(graphMST.containsVertex(3));
    assertTrue(graphMST.containsVertex(4));
    assertTrue(graphMST.containsVertex(5));
    assertTrue(graphMST.containsVertex(6));
    assertTrue(graphMST.containsVertex(7));
    assertTrue(graphMST.containsVertex(8));
  }
  
  /**
   * Checks the correct vertices in the minimum spanning tree when starting node is 8
   */
  @Test
  public void testMSTVerticesStarting8() {
    CS400Graph<Integer,Integer> graphMST = graph.getMinSpanningTree(8);
    assertTrue(graphMST.containsVertex(0));
    assertTrue(graphMST.containsVertex(1));
    assertTrue(graphMST.containsVertex(2));
    assertTrue(graphMST.containsVertex(3));
    assertTrue(graphMST.containsVertex(4));
    assertTrue(graphMST.containsVertex(5));
    assertTrue(graphMST.containsVertex(6));
    assertTrue(graphMST.containsVertex(7));
    assertTrue(graphMST.containsVertex(8));
  }


  /**
   * Checks the correct edges in the minimum spanning tree when starting node is 0
   */
  @Test
  public void testMSTEdgesStarting0() {
    CS400Graph<Integer, Integer> graphMST = graph.getMinSpanningTree(0);
    assertTrue(graphMST.containsEdge(0, 1));
    assertTrue(graphMST.containsEdge(1, 2));
    assertTrue(graphMST.containsEdge(2, 8));
    assertTrue(graphMST.containsEdge(2, 5));
    assertTrue(graphMST.containsEdge(2, 3));
    assertTrue(graphMST.containsEdge(3, 4));
    assertTrue(graphMST.containsEdge(5, 6));
    assertTrue(graphMST.containsEdge(6, 7));
  }
  

  /**
   * Checks the correct edges in the minimum spanning tree when starting node is 8
   */
  @Test
  public void testMSTEdgesStarting8() {
    CS400Graph<Integer, Integer> graphMST = graph.getMinSpanningTree(8);
    assertTrue(graphMST.containsEdge(0, 1));
    assertTrue(graphMST.containsEdge(2, 5));
    assertTrue(graphMST.containsEdge(2, 3));
    assertTrue(graphMST.containsEdge(3, 4));
    assertTrue(graphMST.containsEdge(5, 6));
    assertTrue(graphMST.containsEdge(6, 7));
    assertTrue(graphMST.containsEdge(7, 0));
    assertTrue(graphMST.containsEdge(8, 2));
  }
  
  /**
   * Checks the correct cost in the minimum spanning tree when the starting node is 0
   */
  @Test
  public void testMSTCostStarting0() {
    assertTrue(graph.getMinSpanningTreeCost(0) == 37.0);
    
  }
  
  /**
   * Checks the correct cost in the minimum spanning tree when the starting node is 8
   */
  @Test
  public void testMSTCostStarting8() {
    assertTrue(graph.getMinSpanningTreeCost(8) == 37.0);
  }
  
  
  
  


}
