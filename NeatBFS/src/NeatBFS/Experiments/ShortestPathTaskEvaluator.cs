using System;
using ENTM.TuringMachine;
using NeatBFS.Graph;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEvaluator : TuringEvaluator<ShortestPathTaskEnvironment>
    {
        public override int Iterations { get; } = 1;
        public override int MaxScore => Iterations*3;
        public override int EnvironmentInputCount { get; }
        public override int EnvironmentOutputCount { get; }

        private readonly ShortestPathTaskInstance _instance;

        public ShortestPathTaskEvaluator(ShortestPathTaskInstance instance)
        {
            _instance = instance;
            EnvironmentOutputCount = instance.Graph.NumberOfVertices * instance.Graph.NumberOfVertices + 2* instance.Graph.NumberOfVertices;
            EnvironmentInputCount = instance.Graph.NumberOfVertices;
        }
        protected override ShortestPathTaskEnvironment NewEnvironment()
        {
            return new ShortestPathTaskEnvironment(_instance.Graph, _instance.Goal, _instance.Source);
        }
    }
}