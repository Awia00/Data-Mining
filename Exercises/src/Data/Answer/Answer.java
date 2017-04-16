package Data.Answer;

import Common.DataTypes.*;
import Common.Interfaces.NDimensionalPoint;
import Data.Answer.Enums.*;
import Data.Answer.SubAnswers.AnswerCanCalcMean;

import java.util.*;

public class Answer implements NDimensionalPoint {

    protected Map<Integer, SpaceComparable> map = new LinkedHashMap<>();

    public static final int AGE_INDEX              = 1;
    public static final int GENDER_INDEX           = 2;
    //public static final int SHOE_INDEX             = 3;
    //public static final int HEIGHT_INDEX           = 4;
    public static final int DEGREE_INDEX           = 5;
    public static final int WHYCOURSE_INDEX        = 6;
    public static final int PROLANGUAGE_INDEX      = 7;
    public static final int PHONEOS_INDEX          = 8;
    public static final int HOWINTERESTED_DATABASE_INDEX        = 9;
    public static final int HOWINTERESTED_PREDICTIVE_INDEX      = 10;
    public static final int HOWINTERESTED_SIMILAR_INDEX         = 11;
    public static final int HOWINTERESTED_VISUALIZATION_INDEX   = 12;
    public static final int HOWINTERESTED_PATTERNSET_INDEX      = 13;
    public static final int HOWINTERESTED_PATTERNSEQ_INDEX      = 14;
    public static final int HOWINTERESTED_PATTERNGRAPHS_INDEX   = 15;
    public static final int HOWINTERESTED_PATTERNTEXT_INDEX     = 16;
    public static final int HOWINTERESTED_PATTERNIMAGES_INDEX   = 17;
    public static final int HOWINTERESTED_CODE_INDEX            = 18;
    public static final int HOWINTERESTED_OFFSHELF_INDEX        = 19;
    public static final int WHICHGAMES_INDEX       = 20;
    public static final int COMMUTE_INDEX          = 21;
    //public static final int FOURRANDOMNUMBER_INDEX = 37;
    public static final int PICKANUMBER_INDEX      = 39;


    public Answer(){
        map = new HashMap<>();
        map.put(AGE_INDEX,                          new Numeric(0));
        map.put(GENDER_INDEX,                       Binary.negative());
        //map.put(SHOE_INDEX   ,                      new Numeric(0));
        //map.put(HEIGHT_INDEX  ,                     new Numeric(0));
        map.put(DEGREE_INDEX   ,                    new Nominal(DegreeEnum.Other));
        map.put(WHYCOURSE_INDEX ,                   new Nominal(WhyCourse.Other));
        map.put(PROLANGUAGE_INDEX,                  new Sequence<>(new ArrayList<>()));
        map.put(PHONEOS_INDEX     ,                 new Nominal(PhoneOS.Other));
        map.put(HOWINTERESTED_DATABASE_INDEX,       new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PREDICTIVE_INDEX,     new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_SIMILAR_INDEX,        new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_VISUALIZATION_INDEX,  new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PATTERNSET_INDEX,     new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PATTERNSEQ_INDEX,     new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PATTERNGRAPHS_INDEX,  new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PATTERNTEXT_INDEX,    new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_PATTERNIMAGES_INDEX,  new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_CODE_INDEX,           new Nominal(PreferRange.Meh));
        map.put(HOWINTERESTED_OFFSHELF_INDEX,       new Nominal(PreferRange.Meh));
        map.put(WHICHGAMES_INDEX,                   new Sequence<>(new ArrayList<>()));
        map.put(COMMUTE_INDEX,                      new Sequence<>(new ArrayList<>()));
        //map.put(FOURRANDOMNUMBER_INDEX,             new Sequence<>(new ArrayList<>()));
        map.put(PICKANUMBER_INDEX,                  new Nominal(PickANumber.Asparagus));
    }
    public Answer(Map<Integer, SpaceComparable> map){
        this.map = map;
    }


    @Override
    public String toString() {
        return "Answer{" + map.toString() + "}";
    }

    @Override
    public SpaceComparable get(Integer key) {
        return map.get(key);
    }

    @Override
    public Set<Integer> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<SpaceComparable> values() {
        return map.values();
    }

    @Override
    public Set<Map.Entry<Integer, SpaceComparable>> entrySet() {
        return map.entrySet();
    }

    @Override
    public NDimensionalPoint getDefaultPoint() {
        return new Answer();
    }


    public Answer convertToAnswerCanCalcMean(){
        Map<Integer, SpaceComparable> newMap = new HashMap<>(map);
        return new AnswerCanCalcMean(newMap);
    }
}