using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public class SingleGraphFactory : IGraphFactory
    {
        private readonly IGraph _graph;

        public SingleGraphFactory(IGraph graph)
        {
            _graph = graph;
        }

        public IEnumerable<ShortestPathTaskInstance> GenerateInstances(int size, long seed = 42)
        {
            for (var i = 0; i < _graph.NumberOfVertices; i++)
            {
                for (var j = 0; j < _graph.NumberOfVertices; j++)
                {
                    if (j != i)
                    {
                        yield return new ShortestPathTaskInstance
                        {
                            Graph = _graph,
                            Source = i,
                            Goal = j
                        };
                    }
                }
            }
        }
    }
}