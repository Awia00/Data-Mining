package Data.Answer.SubAnswers;

import Common.DataTypes.SpaceComparable;
import Data.Answer.Answer;
import java.util.Map;

/**
 * Created by ander on 15-04-2017.
 */
public class AnswerCanCalcMean extends Answer {
    public AnswerCanCalcMean(){
        super();
        map.remove(PROLANGUAGE_INDEX);
        map.remove(WHICHGAMES_INDEX);
        map.remove(COMMUTE_INDEX);
    }

    public AnswerCanCalcMean(Map<Integer, SpaceComparable> attributes){
        super(attributes);
        map.remove(PROLANGUAGE_INDEX);
        map.remove(WHICHGAMES_INDEX);
        map.remove(COMMUTE_INDEX);
    }
}
