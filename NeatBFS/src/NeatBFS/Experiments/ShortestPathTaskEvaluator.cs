using System;
using ENTM.TuringMachine;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEvaluator : TuringEvaluator<ShortestPathTaskEnvironment>
    {
        public override int Iterations { get; }
        public override int MaxScore { get; }
        public override int EnvironmentInputCount { get; }
        public override int EnvironmentOutputCount { get; }
        protected override ShortestPathTaskEnvironment NewEnvironment()
        {
            throw new NotImplementedException();
        }
    }
}