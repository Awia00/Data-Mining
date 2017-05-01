using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NeatBFS.Graph
{
    public class AdjacencyMatrixGraph : IGraph
    {
        public bool[,] AdjacencyMatrix { get; }
        private readonly int _size;


        public AdjacencyMatrixGraph(int size)
        {
            AdjacencyMatrix = new bool[size,size];
            _size = size;
        }

        public void AddEdge(int u, int v)
        {
            Debug.Assert(u < _size);
            Debug.Assert(v < _size);
            AdjacencyMatrix[u, v] = true;
            AdjacencyMatrix[v, u] = true;
        }

        public void RemoveEdge(int u, int v)
        {
            Debug.Assert(u < _size);
            Debug.Assert(v < _size);
            AdjacencyMatrix[u, v] = false;
            AdjacencyMatrix[v, u] = false;
        }

        private IEnumerable<int> Neighbours(int u)
        {
            var result = new List<int>();
            for (var i = 0; i < _size; i++)
            {
                if (AdjacencyMatrix[u, i])
                {
                    result.Add(i);
                }
            }
            return result;
        }

        public List<int> ShortestPath(int u, int v)
        {
            var queue = new Queue<int>();
            var edgeFrom = new Dictionary<int,int>();
            queue.Enqueue(u);
            while (queue.Count != 0)
            {
                var vertex = queue.Dequeue();
                foreach (var neighbour in Neighbours(vertex))
                {
                    if (!edgeFrom.ContainsKey(neighbour))
                    {
                        queue.Enqueue(neighbour);
                        edgeFrom.Add(neighbour, vertex);
                        if (neighbour == v)
                        {
                            queue.Clear();
                        }
                    }
                }
            }
            var path = new List<int>();
            var pathVertex = v;
            while (pathVertex != u)
            {
                path.Add(pathVertex);
                pathVertex = edgeFrom[pathVertex];
            }
            path.Add(u);
            path.Reverse();
            return path;
        }

        public int NextOnShortestPath(int u, int v)
        {
            var path = ShortestPath(u, v);
            return path.Count > 1 ? path[1] : v;
        }

        public int Distance(int u, int v)
        {
            var path = ShortestPath(u, v);
            return path.Count;
        }

        public int[] DistanceToArray(int v)
        {
            var result = new int[_size];
            for (var i = 0; i < AdjacencyMatrix.GetLength(0); i++)
            {
                result[i] = Distance(i, v);
            }
            return result;
        }
    }
}
