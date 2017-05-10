using System.Collections.Generic;

namespace NeatBFS.Graph.Factories
{
    public interface IShortestPathInstanceFactory
    {
        IEnumerable<ShortestPathTaskInstance> GenerateInstances();
        int Vertices { get; }
    }
}
