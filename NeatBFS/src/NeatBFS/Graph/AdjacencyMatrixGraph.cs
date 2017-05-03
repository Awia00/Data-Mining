using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Xml;
using SharpNeat.Domains;

namespace NeatBFS.Graph
{
    public class AdjacencyMatrixGraph : IGraph
    {
        public bool[,] AdjacencyMatrix { get; }
        public int NumberOfVertices { get; }

        public AdjacencyMatrixGraph(int size)
        {
            AdjacencyMatrix = new bool[size,size];
            NumberOfVertices = size;
        }

        public void AddEdge(int u, int v)
        {
            Debug.Assert(u < NumberOfVertices);
            Debug.Assert(v < NumberOfVertices);
            AdjacencyMatrix[u, v] = true;
            AdjacencyMatrix[v, u] = true;
        }

        public void RemoveEdge(int u, int v)
        {
            Debug.Assert(u < NumberOfVertices);
            Debug.Assert(v < NumberOfVertices);
            AdjacencyMatrix[u, v] = false;
            AdjacencyMatrix[v, u] = false;
        }

        public bool HasEdge(int u, int v)
        {
            return AdjacencyMatrix[u, v];
        }

        private IEnumerable<int> Neighbours(int u)
        {
            var result = new List<int>();
            for (var i = 0; i < NumberOfVertices; i++)
            {
                if (AdjacencyMatrix[u, i])
                {
                    result.Add(i);
                }
            }
            return result;
        }

        public int[] DistanceToArray(int v)
        {
            var queue = new Queue<int>();
            var edgeFrom = new Dictionary<int, int>();
            var distanceTo = new int[NumberOfVertices];
            for (var i = 0; i < distanceTo.Length; i++)
            {
                distanceTo[i] = -1;
            }

            distanceTo[v] = 0;
            queue.Enqueue(v);
            while (queue.Any())
            {
                var vertex = queue.Dequeue();
                foreach (var neighbour in Neighbours(vertex))
                {
                    if (!edgeFrom.ContainsKey(neighbour))
                    {
                        queue.Enqueue(neighbour);
                        edgeFrom.Add(neighbour, vertex);
                        distanceTo[neighbour] = distanceTo[vertex] + 1;
                    }
                }
            }
            distanceTo[v] = 0;
            return distanceTo;
        }

        public double[] EncodedGraph
        {
            get
            {
                var arr = new double[NumberOfVertices * NumberOfVertices];
                for (var i = 0; i < NumberOfVertices; i++)
                {
                    for (var j = 0; j < NumberOfVertices; j++)
                    {
                        arr[i * NumberOfVertices + j] = AdjacencyMatrix[i, j] ? 1d : 0d;
                    }
                }
                return arr;
            }
        }

        public static IGraph Parse(XmlElement xmlElement)
        {
            var vertices = int.Parse(xmlElement.GetAttribute("vertices"));

            var graph = new AdjacencyMatrixGraph(vertices);

            foreach (XmlElement edge in xmlElement.SelectNodes("Edge"))
            {
                var from = int.Parse(edge.GetAttribute("from"));
                var to = int.Parse(edge.GetAttribute("to"));

                graph.AddEdge(from, to);
            }

            return graph;
        }
    }
}
