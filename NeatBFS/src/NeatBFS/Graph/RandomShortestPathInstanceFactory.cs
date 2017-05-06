using System;
using System.Collections.Generic;
using System.Linq;

namespace NeatBFS.Graph
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

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances()
        {
            while (true)
            {
                int from, to;

                // generate problem
                do
                {
                    from = _random.Next(Vertices);
                    to = _random.Next(Vertices);
                } while (from == to);

                // Generate graph.
                IGraph g = new AdjacencyMatrixGraph(Vertices);

                if (MinPathLength > 0)
                {
                    IList<int> path = new List<int>
                    {
                        from
                    };

                    while (path.Count < MinPathLength)
                    {
                        int number;
                        do
                        {
                            number = _random.Next(Vertices);
                        } while (number == to || path.Contains(number));
                        path.Add(number);
                    }
                    path.Add(to);

                    for (var i = 0; i < MinPathLength; i++)
                    {
                        g.AddEdge(path[i], path[i + 1]);
                    }
                }

                if (g.DistanceToArray(to)[from] != MinPathLength)
                {
                    throw new Exception("Logical error");
                }

                yield return new ShortestPathTaskInstance
                {
                    Source = from,
                    Goal = to,
                    Graph = g
                };
            }
        }
    }
}