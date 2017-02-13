package Lab2.Classification;

import Lab2.Mushroom;
import Lab2.enums.Class_Label;

import java.util.List;

/**
 * Created by aws on 13-02-2017.
 */
public interface IMushroomClassifier extends IClassifier {

    void classifySet(List<Mushroom> mushroomList);
    //void test(List<Mushroom> mushroomList);
    Class_Label classify(Mushroom mushroom);

}
