using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public interface IGraph
    {
        void AddEdge(int u, int v);
        int Distance(int u, int v);
        int[] DistanceToArray(int v);
        int NextOnShortestPath(int u, int v);
        void RemoveEdge(int u, int v);
        List<int> ShortestPath(int u, int v);
    }
}