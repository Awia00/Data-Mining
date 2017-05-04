using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public class ManualShortestPathInstanceFactory : IShortestPathInstanceFactory
    {
        public int MinPathLength { get; }
        private readonly IGraph _graph;

        public ManualShortestPathInstanceFactory(IGraph graph)
        {
            _graph = graph;
        }
        public ManualShortestPathInstanceFactory(IGraph graph, int minPathLength=1)
        {
            _graph = graph;
            MinPathLength = minPathLength;
        }

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances()
        {
            for (var goal = 0; goal < _graph.NumberOfVertices; goal++)
            {
                var distance = _graph.DistanceToArray(goal);
                for (var source = 0; source < _graph.NumberOfVertices; source++)
                {
                    if (source != goal && distance[source] >= MinPathLength)
                    {
                        yield return new ShortestPathTaskInstance
                        {
                            Graph = _graph,
                            Source = source,
                            Goal = goal
                        };
                    }
                }
            }
        }

        public int Vertices => _graph.NumberOfVertices;
    }
}