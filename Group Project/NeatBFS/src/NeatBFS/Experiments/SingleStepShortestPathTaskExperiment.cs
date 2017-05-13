using ENTM.Base;
using ENTM.TuringMachine;

namespace NeatBFS.Experiments
{
    public class SingleStepShortestPathTaskExperiment : BaseExperiment<SingleStepShortestPathTaskEvaluator, SingleStepShortestPathTaskEnvironment, TuringController>
    {
        public override int EnvironmentInputCount => _evaluator.EnvironmentInputCount;
        public override int EnvironmentOutputCount => _evaluator.EnvironmentOutputCount;
        public override int ControllerInputCount => _evaluator.ControllerInputCount;
        public override int ControllerOutputCount => _evaluator.ControllerOutputCount;
    }
}
