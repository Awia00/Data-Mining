class Graph:
    def __init__(self, size):
        self.size = size
        adjacency_matrix = [[False for x in range(size)] for y in range(size)]

        self.adjacencyMatrix = adjacency_matrix

    def __getitem__(self, item):
        return self.adjacencyMatrix[item]

    def has_edge(self, u, v):
        return self.adjacencyMatrix[u][v]

    def add_edge(self, u, v):
        self.adjacencyMatrix[u][v] = True
        self.adjacencyMatrix[v][u] = True

    def remove_edge(self, u, v):
        self.adjacencyMatrix[u][v] = False
        self.adjacencyMatrix[v][u] = False

    def adjacency_matrix(self):
        return self.adjacencyMatrix

    def has_key(self, u):
        return u < len(self.adjacencyMatrix)

    def shortest_path(self, u, v):
        queue = [u]
        visited = [u]
        edge_from = {}
        if u == v:
            return [u]

        while queue:
            vertex = queue.pop(0)
            for neighbour in [x for x in range(self.size) if self.has_edge(vertex, x) and not visited.__contains__(x)]:
                visited.append(neighbour)
                edge_from[neighbour] = vertex
                if neighbour == v:
                    break
                queue.append(neighbour)

        path = []
        vertex = v
        while vertex:
            path.append(vertex)
            vertex = edge_from[vertex]

        list.reverse(path)
        return path

    def distance_to(self, u, v):
        return len(self.shortest_path(u, v))

    def next_on_shortest_path(self, u, v):
        path = self.shortest_path(u, v)
        if len(path) > 0:
            return path[1]
        return v
