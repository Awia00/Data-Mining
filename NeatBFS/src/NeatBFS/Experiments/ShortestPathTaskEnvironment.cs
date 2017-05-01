using System;
using ENTM.Base;
using ENTM.Replay;
using NeatBFS.Graph;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEnvironment : BaseEnvironment
    {
        public override bool RecordTimeSteps { get; set; }
        public override EnvironmentTimeStep InitialTimeStep { get; }    
        public override EnvironmentTimeStep PreviousTimeStep { get; }
        public override IController Controller { get; set; }
        public override int InputCount { get; }
        public override int OutputCount { get; }
        public override double[] InitialObservation { get; }
        public override double CurrentScore { get; }
        public override double MaxScore { get; }
        public override double NormalizedScore { get; }
        public override bool IsTerminated { get; }
        public override int TotalTimeSteps { get; } = 1;
        public override int MaxTimeSteps { get; } = 1;
        public override int NoveltyVectorLength { get; }
        public override int NoveltyVectorDimensions { get; }
        public override int MinimumCriteriaLength { get; }

        public override void ResetIteration()
        {
            throw new NotImplementedException();
        }

        public override double[] PerformAction(double[] action)
        {
            var next = GetMaxIndex(action);



            throw new NotImplementedException();
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

        public override void ResetAll()
        {
            throw new NotImplementedException();
        }

        public override int RandomSeed { get; set; }

        protected double[] GetOutput(ShortestPathTaskInstance instance)
        {
            var arr = new double[instance.Graph.NumberOfVertices * instance.Graph.NumberOfVertices + 2];
            
            throw new NotImplementedException();
        }
    }
}