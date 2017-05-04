using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public interface IShortestPathInstanceFactory
    {
        IEnumerable<ShortestPathTaskInstance> GenerateInstances();
        int Vertices { get; }
    }
}
