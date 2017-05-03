using System;
using System.Xml;
using ENTM.TuringMachine;
using NeatBFS.Graph;
using SharpNeat.Domains;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEvaluator : TuringEvaluator<ShortestPathTaskEnvironment>
    {
        public override int Iterations => _iterations;
        public override int MaxScore => Iterations*3;
        public override int EnvironmentInputCount => _environmentInputCount;
        public override int EnvironmentOutputCount => _environmentOutputCount;

        private int _environmentInputCount, _environmentOutputCount, _iterations;

        private ShortestPathTaskInstance _instance;

        public ShortestPathTaskEvaluator()
        {
            
        }

        public override void Initialize(XmlElement xmlConfig)
        {
            base.Initialize(xmlConfig);

            var shortestPathParams = xmlConfig.SelectSingleNode("ShortestPathTaskParams") as XmlElement;

            _iterations = XmlUtils.TryGetValueAsInt(shortestPathParams, "Iterations") ?? 1;

            _instance = new ShortestPathTaskInstance
            {
                Source = XmlUtils.GetValueAsInt(shortestPathParams, "Source"),
                Goal = XmlUtils.GetValueAsInt(shortestPathParams, "Goal"),
                Graph = AdjacencyMatrixGraph.Parse(shortestPathParams.SelectSingleNode("Graph") as XmlElement)
            };

            _environmentOutputCount = _instance.Graph.NumberOfVertices * _instance.Graph.NumberOfVertices + 2 * _instance.Graph.NumberOfVertices;
            _environmentInputCount = _instance.Graph.NumberOfVertices;
        }
        
        protected override ShortestPathTaskEnvironment NewEnvironment()
        {
            return new ShortestPathTaskEnvironment(_instance.Graph, _instance.Goal, _instance.Source);
        }

        protected override void OnObjectiveEvaluationStart()
        {
            // Reset the environment. This will reset the random
            Environment.ResetAll();
        }
    }
}