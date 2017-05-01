using System;
using ENTM.Base;
using ENTM.Replay;

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
        public override int TotalTimeSteps { get; }
        public override int MaxTimeSteps { get; }
        public override int NoveltyVectorLength { get; }
        public override int NoveltyVectorDimensions { get; }
        public override int MinimumCriteriaLength { get; }

        public override void ResetIteration()
        {
            throw new NotImplementedException();
        }

        public override double[] PerformAction(double[] action)
        {
            throw new NotImplementedException();
        }

        public override void ResetAll()
        {
            throw new NotImplementedException();
        }

        public override int RandomSeed { get; set; }
    }
}