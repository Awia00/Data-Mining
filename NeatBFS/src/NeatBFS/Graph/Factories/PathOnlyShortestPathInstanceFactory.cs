using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

namespace NeatBFS.Graph.Factories
{
    public class PathOnlyShortestPathInstanceFactory : IShortestPathInstanceFactory
    {
        private readonly ThreadLocal<Random> _random;
        public int Vertices { get; }
        public int PathLength { get; }
        private readonly bool _symmetricInstances;

        public PathOnlyShortestPathInstanceFactory(int vertices, int pathLength, bool symmetricInstances = true, int? seed = null)
        {
            Vertices = vertices;
            PathLength = pathLength;
            _symmetricInstances = symmetricInstances;
            _random = new ThreadLocal<Random>(() => seed.HasValue ? new Random(seed.Value) : new Random());
        }

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances()
        {
            while (true)
            {
                int from, to;

                // generate problem
                do
                {
                    from = _random.Value.Next(Vertices);
                    to = _random.Value.Next(Vertices);
                } while (from == to);

                // Generate graph.
                IGraph g = new AdjacencyMatrixGraph(Vertices);

                IList<int> path = new List<int>
                {
                    from
                };

                IList<int> vertices = new List<int>(Enumerable.Range(0, Vertices));
                vertices.Remove(from);
                vertices.Remove(to);
                while (path.Count < PathLength)
                {
                    var index = _random.Value.Next(vertices.Count);
                    var vertex = vertices[index];
                    vertices.Remove(vertex);
                    path.Add(vertex);
                }
                path.Add(to);

                for (var i = 0; i < PathLength; i++)
                {
                    g.AddEdge(path[i], path[i + 1]);
                }

                yield return new ShortestPathTaskInstance
                {
                    Source = from,
                    Goal = to,
                    Graph = g
                };

                if (_symmetricInstances)
                {
                    yield return new ShortestPathTaskInstance
                    {
                        Source = to,
                        Goal = from,
                        Graph = g
                    };
                }
            }
        }
    }
}