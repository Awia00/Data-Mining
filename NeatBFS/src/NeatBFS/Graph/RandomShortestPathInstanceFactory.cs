using System;
using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public class RandomShortestPathInstanceFactory : IShortestPathInstanceFactory
    {
        private readonly Random _random;
        public int Vertices { get; }
        public int Edges { get; }

        public RandomShortestPathInstanceFactory(int vertices, int edges, int? seed = null)
        {
            Vertices = vertices;
            Edges = edges;
            _random = seed.HasValue ? new Random(seed.Value) : new Random();
        }

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances()
        {
            // Generate graph.
            IGraph g = new AdjacencyMatrixGraph(Vertices);

            for (var i = 0; i < Edges; i++)
            {
                int from, to;
                do
                {
                    from = _random.Next(Vertices);
                    to = _random.Next(Vertices);
                } while (from == to || g.HasEdge(from, to));

                g.AddEdge(from, to);
            }

            return new ManualShortestPathInstanceFactory(g).GenerateInstances();
        }
    }
}