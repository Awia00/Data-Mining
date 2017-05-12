using System;
using System.Collections.Generic;
using ENTM.Base;
using ENTM.Replay;
using NeatBFS.Graph;
using NeatBFS.Graph.Factories;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEnvironment : BaseEnvironment
    {
        #region environment
        public override IController Controller { get; set; }
        public override int InputCount => Graph.NumberOfVertices;
        public override int OutputCount => Graph.NumberOfVertices * Graph.NumberOfVertices + 2 * Graph.NumberOfVertices;
        public override double[] InitialObservation => GetOutput(_startVertex);
        private double _currentScore;
        public override double CurrentScore => _currentScore;

        public override double MaxScore => DistanceToArray[_startVertex]*4;

        public override double NormalizedScore => CurrentScore / MaxScore;
        public override bool IsTerminated => DistanceToArray[CurrentVertex] == 0 || _step >= TotalTimeSteps;
        public override int TotalTimeSteps => DistanceToArray[_startVertex];
        public override int MaxTimeSteps => DistanceToArray[_startVertex];

        public override int NoveltyVectorLength => Graph.NumberOfVertices;

        public override int NoveltyVectorDimensions => 1;

        public override int MinimumCriteriaLength { get; } = 0;
        #endregion environment

        #region graph

        private readonly IShortestPathInstanceFactory _instanceFactory;
        public IGraph Graph => Current.Graph;
        public double[] EncodedGraph { get; set; }
        public int[] DistanceToArray { get; set; }
        public int Goal => Current.Goal;
        public int CurrentVertex { get; set; }
        private int _startVertex;
        #endregion graph

        public ShortestPathTaskEnvironment(IShortestPathInstanceFactory instanceFactory)
        {
            _instanceFactory = instanceFactory;

            ResetAll();
        }

        public override double[] PerformAction(double[] action)
        {
            if (CurrentVertex == Goal) throw new Exception("Goal reached before step");
            var next = GetMaxIndex(action);
            var observation = GetOutput(next);
            var thisScore = Evaluate(CurrentVertex, next, action);
            //var thisScore = FuzzyEvaluate(CurrentVertex, action);

            _currentScore += thisScore;

            if (RecordTimeSteps)
            {
                PrevTimeStep = new EnvironmentTimeStep(action, observation, thisScore);
            }
            CurrentVertex = next;
            _step++;
            return GetOutput(next);
        }
        
        protected virtual double Evaluate(int current, int next, double[] action)
        {
            if (NoveltySearch.ScoreNovelty)
            {
                NoveltySearch.NoveltyVectors[current][0] = next;
            }
            return ScoreForMove(current, next);
        }

        protected int ScoreForMove(int current, int i)
        {
            if (!Graph.HasEdge(current, i)) // took non edge
            {
                _step = MaxTimeSteps;
                _currentScore = 0;
                return 0; 
            }
            if (DistanceToArray[i] > DistanceToArray[current]) // took an edge away from goal
            {
                return 1;
            }
            if (DistanceToArray[i] == DistanceToArray[current]) // took an edge on the current fridge
            {
                return 2;
            }
            if (DistanceToArray[i] < DistanceToArray[current]) // took an edge towards the goal.
            {
                return 4;
            }
            throw new Exception("Should not get here aka no move");
        }

        protected static int GetMaxIndex(double[] action)
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

        
        /// <summary>
        /// Move the agent back to start for a new round of evaluation (can be different from the previous state).
        /// </summary>
        public override void ResetIteration()
        {
            if (_instances == null || !_instances.MoveNext())
            {
                _seenInstances.Clear();
                do
                {
                    _instances = _instanceFactory.GenerateInstances().GetEnumerator();
                } while (!_instances.MoveNext());
            }

            _seenInstances.Add(Current);

            EncodedGraph = Graph.EncodedGraph;
            DistanceToArray = Graph.DistanceToArray(Goal);
            _startVertex = Current.Source;

            CurrentVertex = _startVertex;
            _currentScore = 0;
            _step = 0;
        }

        public ShortestPathTaskInstance Current => _instances.Current;
        private IEnumerator<ShortestPathTaskInstance> _instances;
        private List<ShortestPathTaskInstance> _seenInstances = new List<ShortestPathTaskInstance>();

        /// <summary>
        /// ResetAll the simulator to some initial state (for new agents to be tested under same circumstances
        /// </summary>
        public sealed override void ResetAll()
        {
            _instances = _seenInstances.GetEnumerator();
            _seenInstances = new List<ShortestPathTaskInstance>();
            ResetIteration();
        }

        #region RecordTimesteps

        private int _step;
        public override bool RecordTimeSteps { get; set; }
        protected EnvironmentTimeStep PrevTimeStep;

        public override EnvironmentTimeStep InitialTimeStep
        {
            get
            {
                return PrevTimeStep = new EnvironmentTimeStep(new double[InputCount], GetOutput(CurrentVertex), _currentScore);
            }
        }
        public override EnvironmentTimeStep PreviousTimeStep => PrevTimeStep;
        #endregion
    }
}