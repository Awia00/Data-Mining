package Data.Answer.SpecialisedTypes;

import Common.DataTypes.Numeric;
import Common.DataTypes.Sequence;
import Common.DataTypes.SpaceComparable;
import Data.Answer.Answer;

import java.util.Map;

/**
 * Created by aws on 16-04-2017.
 */
public class AnswerWithAmtLang extends Answer {

    public AnswerWithAmtLang(){
        super();
        map.put(ACCUMULATEDLANG_INDEX, new Numeric(0));
    }

    public AnswerWithAmtLang(Map<Integer, SpaceComparable> attributes){
        super(attributes);
        map.put(ACCUMULATEDLANG_INDEX, new Numeric(((Sequence)map.get(PROLANGUAGE_INDEX)).getElements().size()));
    }
}
