using System;
using System.Configuration;
using System.Xml;
using ENTM.TuringMachine;
using NeatBFS.Graph;
using SharpNeat.Domains;

namespace NeatBFS.Experiments
{
    public class ShortestPathTaskEvaluator : TuringEvaluator<ShortestPathTaskEnvironment>
    {
        public override int Iterations => _iterations;
        public override int MaxScore => 1;
        public override int EnvironmentInputCount => _environmentInputCount;
        public override int EnvironmentOutputCount => _environmentOutputCount;

        private int _environmentInputCount, _environmentOutputCount, _iterations;

        private IShortestPathInstanceFactory _instanceFactory;

        public ShortestPathTaskEvaluator()
        {
            
        }

        public override void Initialize(XmlElement xmlConfig)
        {
            base.Initialize(xmlConfig);

            var shortestPathParams = xmlConfig.SelectSingleNode("ShortestPathTaskParams") as XmlElement;

            _iterations = XmlUtils.TryGetValueAsInt(shortestPathParams, "Iterations") ?? 1;

            var graphConfig = shortestPathParams.SelectSingleNode("Graph") as XmlElement;
            
            switch (graphConfig.GetAttribute("type").ToLower())
            {
                case "random":
                    int vertices = int.Parse(graphConfig.GetAttribute("vertices")),
                        edges = int.Parse(graphConfig.GetAttribute("edges"));

                    int minPath = graphConfig.HasAttribute("minpath") ? int.Parse(graphConfig.GetAttribute("minpath")) : 1;
                    var seed = graphConfig.HasAttribute("seed") ? int.Parse(graphConfig.GetAttribute("seed")) : (int?) null;

                    _instanceFactory = new RandomShortestPathInstanceFactory(vertices, edges, minPath, seed);
                    break;
                case "manual":
                    _instanceFactory = new ManualShortestPathInstanceFactory(AdjacencyMatrixGraph.Parse(graphConfig));
                    break;
                default:
                    throw new ConfigurationErrorsException();
            }

            var n = _instanceFactory.Vertices;

            _environmentOutputCount = n*n + 2*n;
            _environmentInputCount = n;
        }
        
        protected override ShortestPathTaskEnvironment NewEnvironment()
        {
            return new ShortestPathTaskEnvironment(_instanceFactory);
        }

        protected override void OnObjectiveEvaluationStart()
        {
            // Reset the environment. This will reset the random
            Environment.ResetAll();
        }
    }
}