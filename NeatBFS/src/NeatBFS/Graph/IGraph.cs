using System.Collections.Generic;

namespace NeatBFS.Graph
{
    public interface IGraph
    {
        int NumberOfVertices { get; }
        void AddEdge(int u, int v);
        void RemoveEdge(int u, int v);
        bool HasEdge(int u, int v);
        int[] DistanceToArray(int v);
    }
}