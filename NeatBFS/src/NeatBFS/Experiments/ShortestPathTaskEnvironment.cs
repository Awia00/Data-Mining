using System;
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
        public override int TotalTimeSteps { get; }
        public override int MaxTimeSteps { get; }
        public override int NoveltyVectorLength { get; }
        public override int NoveltyVectorDimensions { get; }
        public override int MinimumCriteriaLength { get; }
        #endregion environment

        #region graph
        public IGraph Graph { get; set; }
        public int[] DistanceToArray { get; set; }
        public int Goal { get; set; }
        public int CurrentVertex { get; set; }
        #endregion graph

        public ShortestPathTaskEnvironment(IGraph graph, int[] distanceToArray, int goal, int currentVertex)
        {
            Graph = graph;
            DistanceToArray = distanceToArray;
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

            if (Graph.HasEdge(CurrentVertex, next))
            {
                _currentScore = 0;
            }
            else if (DistanceToArray[next] > DistanceToArray[CurrentVertex])
            {
                _currentScore = 1;
            }
            else if (DistanceToArray[next] == DistanceToArray[CurrentVertex])
            {
                _currentScore = 2;
            }
            else if (DistanceToArray[next] < DistanceToArray[CurrentVertex])
            {
                _currentScore = 3;
            }
            CurrentVertex = next;

            return OneHotOfVertex(next);
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
            result[vertex] = 1;
            return result;
        }

        public override void ResetAll()
        {
            throw new NotImplementedException();
        }

        public override int RandomSeed { get; set; }
    }
}