using System;
using System.Configuration;
using System.Xml;
using ENTM.TuringMachine;
using NeatBFS.Graph;
using NeatBFS.Graph.Factories;
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

        public override void Initialize(XmlElement xmlConfig)
        {
            base.Initialize(xmlConfig);

            var shortestPathParams = (XmlElement) xmlConfig.SelectSingleNode("ShortestPathTaskParams");

            _iterations = XmlUtils.TryGetValueAsInt(shortestPathParams, "Iterations") ?? 1;

            var graphConfig = (XmlElement) shortestPathParams?.SelectSingleNode("Graph");

            int vertices, pathLength;
            int? seed;

            switch (graphConfig?.GetAttribute("type").ToLower())
            {
                case "random":
                    vertices = int.Parse(graphConfig.GetAttribute("vertices"));
                    var edges = int.Parse(graphConfig.GetAttribute("edges"));

                    pathLength = int.Parse(graphConfig.GetAttribute("minpath"));
                    seed = graphConfig.HasAttribute("seed") ? int.Parse(graphConfig.GetAttribute("seed")) : (int?) null;

                    _instanceFactory = new RandomShortestPathInstanceFactory(vertices, edges, pathLength, seed);
                    break;
                case "manual":
                    _instanceFactory = new ManualShortestPathInstanceFactory(AdjacencyMatrixGraph.Parse(graphConfig));
                    break;
                case "path":
                    vertices = int.Parse(graphConfig.GetAttribute("vertices"));

                    pathLength = int.Parse(graphConfig.GetAttribute("minpath"));
                    seed = graphConfig.HasAttribute("seed") ? int.Parse(graphConfig.GetAttribute("seed")) : (int?)null;
                    var symmetricInstances = !graphConfig.HasAttribute("symmetricinstances") || bool.Parse(graphConfig.GetAttribute("symmetricinstances"));

                    _instanceFactory = new PathOnlyShortestPathInstanceFactory(vertices, pathLength, symmetricInstances, seed);
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