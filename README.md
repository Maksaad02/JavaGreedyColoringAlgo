# JavaGreedyColoringAlgo
Coloriage Glouton - Graph Coloring Application
  This JavaFX-based project visualizes and solves graph coloring problems using a greedy algorithm. 
  It allows users to create custom graphs, define edges, and compute the minimum number of colors 
  needed to color the graph, ensuring that no two adjacent vertices share the same color.

Features
. Create and manage custom graphs with named vertices and edges.
. Visualize the graph coloring process in real-time.
. Supports disconnected graphs and ensures efficient vertex coloring.

Problem Example
  Problem: How can a class schedule be created where no two classes sharing the same teacher
  or room overlap in timing?
Solution: The greedy coloring algorithm assigns colors (time slots) to classes
such that no conflicting classes have the same time.

Technologies Used
. Java (Core logic).
. JavaFX (User interface).


How It Works
 The greedy coloring algorithm assigns colors to vertices in the graph such that:

  . No two adjacent vertices share the same color.
  . The algorithm uses the smallest number of colors possible for the given graph structure.
Steps:

  1- Iterate through all vertices.
  2- For each vertex, check the colors of its neighbors.
  3- Assign the first available color.

Acknowledgments
  JavaFX Documentation: https://openjfx.io/
  Graph Theory Resources: https://en.wikipedia.org/wiki/Graph_coloring


## Contributors

- Rajib Nouhaila
- (https://github.com/Nouhailarajib)
- Makroum Saad (https://github.com/Maksaad02)
