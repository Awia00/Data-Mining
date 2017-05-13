using NeatBFS.Graph.Factories;

namespace NeatBFS.Experiments
{
    public class FuzzyShortestPathTaskEnvironment : SingleStepShortestPathTaskEnvironment
    {
        public FuzzyShortestPathTaskEnvironment(IShortestPathInstanceFactory instanceFactory) : base(instanceFactory)
        {}

        private double _nonEdgeThreshold = 0.1;
        protected override double Evaluate(int current, int ignored, double[] next)
        {
            if (NoveltySearch.ScoreNovelty)
            {
                NoveltySearch.NoveltyVectors[current][0] = GetMaxIndex(next);
            }

            double weightSum = 0, scoreSum = 0;
            for (var i = 0; i < next.Length; i++)
            {
                weightSum += next[i];
                var scoreMove = ScoreForMove(current, i);
                if (scoreMove == 0 && next[i] > _nonEdgeThreshold)
                    return 0;
                scoreSum += scoreMove;
            }
            return scoreSum / (next.Length / weightSum);
        }
    }
}