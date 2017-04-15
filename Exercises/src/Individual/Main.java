package Individual;

import Common.Interfaces.NDimensionalPoint;
import Data.Answer.AnswerDataLoader;

import java.util.List;

/**
 * Created by ander on 15-04-2017.
 */
public class Main {
    public static void main(String[] args){
        List<NDimensionalPoint> mushrooms = AnswerDataLoader.LoadAllAnswerData();
    }
}
