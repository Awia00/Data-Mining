using System;
using System.Collections.Generic;

namespace NeatBFS.Graph.Factories
{
    public class RandomShortestPathInstanceFactory : IShortestPathInstanceFactory
    {
        private readonly Random _random;
        public int Vertices { get; }
        public int Edges { get; }
        public int MinPathLength { get; }

        public RandomShortestPathInstanceFactory(int vertices, int edges, int minPathLength, int? seed = null)
        {
            Vertices = vertices;
            Edges = edges;
            MinPathLength = minPathLength;
            _random = seed.HasValue ? new Random(seed.Value) : new Random();
        }

        public RandomShortestPathInstanceFactory(int vertices, int edges, int minPathLength, Random random)
        {
            Vertices = vertices;
            Edges = edges;
            MinPathLength = minPathLength;
            _random = random;
        }

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances()
        {
            // Generate graph.
            IGraph g = new AdjacencyMatrixGraph(Vertices);

            if (MinPathLength > 0)
            {
                IList<int> path = new List<int>();
                for (var i = 0; i < MinPathLength + 1; i++)
                {
                    int number;
                    do
                    {
                        number = _random.Next(Vertices);
                    } while (path.Contains(number));
                    path.Add(number);
                }

                for (var i = 0; i < MinPathLength; i++)
                {
                    g.AddEdge(path[i], path[i + 1]);
                }
            }
            for (var i = 0; i < _random.Next(Edges) - MinPathLength + 1; i++)
            {
                int from, to;
                do
                {
                    from = _random.Next(Vertices);
                    to = _random.Next(Vertices);
                } while (from == to || g.HasEdge(from, to));

                g.AddEdge(from, to);
            }

            return new ManualShortestPathInstanceFactory(g, MinPathLength).GenerateInstances();
        }
    }
}