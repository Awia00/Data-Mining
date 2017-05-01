using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public interface IGraphFactory
    {
        IEnumerable<ShortestPathTaskInstance> GenerateInstances(int size, long seed = 42L);
    }
}
