using System;
using System.Linq;
using ENTM.Base;
using ENTM.Replay;
using NeatBFS.Graph;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEnvironment : BaseEnvironment
    {
        #region environment
        public override bool RecordTimeSteps { get; set; }
        public override EnvironmentTimeStep InitialTimeStep { get; }    
        public override EnvironmentTimeStep PreviousTimeStep { get; }
        public override IController Controller { get; set; }
        public override int InputCount { get; }
        public override int OutputCount { get; }
        public override double[] InitialObservation { get; }
        private double _currentScore;
        public override double CurrentScore => _currentScore;
        public override double MaxScore { get; }
        public override double NormalizedScore { get; }
        public override bool IsTerminated { get; }
        public override int TotalTimeSteps { get; } = 1;
        public override int MaxTimeSteps { get; } = 1;
        public override int NoveltyVectorLength { get; }
        public override int NoveltyVectorDimensions { get; }
        public override int MinimumCriteriaLength { get; }
        #endregion environment

        #region graph

        public IGraph Graph { get; set; }
        public double[] EncodedGraph { get; set; }
        public int[] DistanceToArray { get; set; }
        public int Goal { get; set; }
        public int CurrentVertex { get; set; }
        #endregion graph

        public ShortestPathTaskEnvironment(IGraph graph, int goal, int currentVertex)
        {
            Graph = graph;
            EncodedGraph = graph.EncodedGraph;
            DistanceToArray = graph.DistanceToArray(goal);
            Goal = goal;
            CurrentVertex = currentVertex;
            //todo number of iterations.
        }

        public override void ResetIteration()
        {
            throw new NotImplementedException();
        }

        public override double[] PerformAction(double[] action)
        {
            var next = GetMaxIndex(action);

            if (!Graph.HasEdge(CurrentVertex, next)) // took non edge
            {
                _currentScore += 0;
            }
            else if (DistanceToArray[next] > DistanceToArray[CurrentVertex]) // took an edge away from goal
            {
                _currentScore += 1;
            }
            else if (DistanceToArray[next] == DistanceToArray[CurrentVertex]) // took an edge on the current fridge
            {
                _currentScore += 2;
            }
            else if (DistanceToArray[next] < DistanceToArray[CurrentVertex]) // took an edge towards the goal.
            {
                _currentScore += 3;
            }
            CurrentVertex = next;

            return GetOutput(next);
        }

        private static int GetMaxIndex(double[] action)
        {
            var index = -1;
            var oneHotVal = double.NegativeInfinity;

            for (var i = 0; i < action.Length; i++)
            {
                if (action[i] > oneHotVal)
                {
                    index = i;
                    oneHotVal = action[i];
                }
            }

            return index;
        }

        private double[] OneHotOfVertex(int vertex)
        {
            var result = new double[Graph.NumberOfVertices];
            result[vertex] = 1d;
            return result;
        }

        public override void ResetAll()
        {
            throw new NotImplementedException();
        }

        public override int RandomSeed { get; set; }

        protected double[] GetOutput(int source)
        {
            var n = Graph.NumberOfVertices;
            var arr = new double[n*n + 2*n];
            
            EncodedGraph.CopyTo(arr, 0);
            arr[n * n + source] = 1d;
            arr[n * n + n + Goal] = 1d;

            return arr;
        }
    }
}