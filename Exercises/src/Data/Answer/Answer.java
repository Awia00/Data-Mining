package Data.Answer;

import Common.DataTypes.Nominal;
import Common.DataTypes.Numeric;
import Common.DataTypes.Multiple;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Data.Answer.Enums.*;

import java.util.*;

public class Answer implements NDimensionalPoint {

    private Map<Integer, SpaceComparable> map = new LinkedHashMap<>();

    static final int AGE_INDEX              = 1;
    static final int GENDER_INDEX           = 2;
    static final int SHOE_INDEX             = 3;
    static final int HEIGHT_INDEX           = 4;
    static final int DEGREE_INDEX           = 5;
    static final int WHYCOURSE_INDEX        = 6;
    static final int PROLANGUAGE_INDEX      = 7;
    static final int PHONEOS_INDEX          = 8;
    static final int HOWINTERESTED_DATABASE_INDEX        = 9;
    static final int HOWINTERESTED_PREDICTIVE_INDEX      = 10;
    static final int HOWINTERESTED_SIMILAR_INDEX         = 11;
    static final int HOWINTERESTED_VISUALIZATION_INDEX   = 12;
    static final int HOWINTERESTED_PATTERNSET_INDEX      = 13;
    static final int HOWINTERESTED_PATTERNSEQ_INDEX      = 14;
    static final int HOWINTERESTED_PATTERNGRAPHS_INDEX   = 15;
    static final int HOWINTERESTED_PATTERNTEXT_INDEX     = 16;
    static final int HOWINTERESTED_PATTERNIMAGES_INDEX   = 17;
    static final int HOWINTERESTED_CODE_INDEX            = 18;
    static final int HOWINTERESTED_OFFSHELF_INDEX        = 19;
    static final int WHICHGAMES_INDEX       = 20;
    static final int COMMUTE_INDEX          = 21;
    static final int FOURRANDOMNUMBER_INDEX = 37;
    static final int PICKANUMBER_INDEX      = 39;


    Answer(){
        map = new HashMap<>();
        map.put(AGE_INDEX,                          new Numeric(0));
        map.put(GENDER_INDEX,                       new Nominal(Gender.None));
        map.put(SHOE_INDEX   ,                      new Numeric(0));
        map.put(HEIGHT_INDEX  ,                     new Numeric(0));
        map.put(DEGREE_INDEX   ,                    new Nominal(DegreeEnum.Other));
        map.put(WHYCOURSE_INDEX ,                   new Nominal(WhyCourse.Other));
        map.put(PROLANGUAGE_INDEX,                  new Multiple(new ArrayList<>()));
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
        map.put(WHICHGAMES_INDEX,                   new Multiple(new ArrayList<>()));
        map.put(COMMUTE_INDEX,                      new Multiple(new ArrayList<>()));
        map.put(FOURRANDOMNUMBER_INDEX,             new Multiple(new ArrayList<>()));
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
}