using System;
using System.Collections.Generic;
using System.Linq;
using ENTM.Base;
using ENTM.Replay;
using NeatBFS.Graph;
using NeatBFS.Graph.Factories;

namespace NeatBFS.Experiments
{
    public class MultiStepShortestPathTaskEnvironment : BaseEnvironment
    {
        #region environment
        public override IController Controller { get; set; }
        public override int InputCount => Graph.NumberOfVertices;
        public override int OutputCount => 4 * Graph.NumberOfVertices; // One hot of vertex | many-hots of neighbors | one hot of goal | one hot of current.
        public override double[] InitialObservation => new double[OutputCount];
        private double _currentScore;
        public override double CurrentScore => _currentScore;

        public override double MaxScore => DistanceToArray[_startVertex] * 4;

        public override double NormalizedScore => CurrentScore / MaxScore;
        public override bool IsTerminated => DistanceToArray[CurrentVertex] == 0 || _step >= TotalTimeSteps;
        public override int TotalTimeSteps => 2 * Graph.NumberOfVertices + DistanceToArray[_startVertex] + 1;
        public override int MaxTimeSteps => TotalTimeSteps;

        public override int NoveltyVectorLength => TotalTimeSteps;

        public override int NoveltyVectorDimensions => InputCount;

        public override int MinimumCriteriaLength { get; } = 0;
        #endregion environment

        #region graph

        private readonly IShortestPathInstanceFactory _instanceFactory;
        public IGraph Graph => Current.Graph;
        public double[][] EncodedGraph { get; set; }
        public int[] DistanceToArray { get; set; }
        public int Goal => Current.Goal;
        public int CurrentVertex { get; set; }
        private int _startVertex;
        #endregion graph

        public MultiStepShortestPathTaskEnvironment(IShortestPathInstanceFactory instanceFactory)
        {
            _instanceFactory = instanceFactory;

            ResetAll();
        }

        public override double[] PerformAction(double[] action)
        {
            double[] output;
            if (_step < Graph.NumberOfVertices) // Training / Graph phase
            {
                output = new double[OutputCount];
                output[_step] = 1d; // v
                EncodedGraph[_step].CopyTo(output, Graph.NumberOfVertices); // neighbors of v
                output[2 * Graph.NumberOfVertices + Goal] = 1d; // goal
            }
            else if (_step < 2 * Graph.NumberOfVertices) // Training / Goal phase
            {
                output = new double[OutputCount];
                output[2 * Graph.NumberOfVertices + Goal] = 1d;
            }
            else if (_step == 2 * Graph.NumberOfVertices) // separator
            {
                output = InitialObservation;
            }
            else if (_step == 2 * Graph.NumberOfVertices + 1) // first instance
            {
                output = GetOutput(_startVertex);
            }
            else // Test phase
            {
                if (CurrentVertex == Goal) throw new Exception("Goal reached before step");
                var next = GetMaxIndex(action);
                var observation = GetOutput(next);
                var thisScore = Evaluate(CurrentVertex, next, action);

                _currentScore += thisScore;

                if (RecordTimeSteps)
                {
                    PrevTimeStep = new EnvironmentTimeStep(action, observation, thisScore);
                }
                CurrentVertex = next;
                output = observation;
            }

            _step++;
            return output;
        }

        private double[] GetOutput(int current)
        {
            var output = new double[OutputCount];
            output[Graph.NumberOfVertices * 3 + current] = 1;

            return output;
        }

        protected virtual double Evaluate(int current, int next, double[] action)
        {
            var step = _step; // step might be reset by score for move.
            var score = ScoreForMove(current, next);

            if (NoveltySearch.ScoreNovelty)
            {
                NoveltySearch.NoveltyVectors[step][0] = next;
            }
            return score;
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

            EncodedGraph = Graph.EncodedAdjacencyMatrix;

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