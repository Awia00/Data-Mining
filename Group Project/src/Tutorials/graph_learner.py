from src.Tutorials.graph import Graph

graph = Graph(5)

graph.add_edge(0,1)
graph.add_edge(1,2)
graph.add_edge(2,3)
graph.add_edge(1,3)
graph.add_edge(3,4)
graph.add_edge(2,4)

for row in graph.adjacencyMatrix:
    print(row)

path = graph.shortest_path(0,4)
length = graph.distance_to(0,4)
next_node = graph.next_on_shortest_path(0,4)
print(path)
print(length)
print(next_node)

