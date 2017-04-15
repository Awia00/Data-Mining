package Data.Answer;

import Common.DataTypes.Nominal;
import Common.DataTypes.Numeric;
import Common.DataTypes.Multiple;
import Common.DataTypes.SpaceComparable;
import Common.Interfaces.NDimensionalPoint;
import Data.Answer.Enums.*;
import Data.CSVFileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 15-04-2017.
 */
public class AnswerDataLoader {

    public static ArrayList<NDimensionalPoint> LoadAllAnswerData() {
        ArrayList<NDimensionalPoint> data = new ArrayList<>();
        AnswerBuilder answerBuilder = new AnswerBuilder();
        try {
            String[][] dataOrig = CSVFileReader.readDataFile("Resources/Data Mining - Spring 2017.csv", "\",\"", "-", true);

            for (String[] aDataOrig : dataOrig) {
                answerBuilder.addAttributeValue(Answer.AGE_INDEX, new Numeric(ageParser(aDataOrig[Answer.AGE_INDEX])));
                answerBuilder.addAttributeValue(Answer.GENDER_INDEX, new Nominal(genderParser(aDataOrig[Answer.GENDER_INDEX])));
                answerBuilder.addAttributeValue(Answer.SHOE_INDEX, new Numeric(shoeParser(aDataOrig[Answer.SHOE_INDEX])));
                answerBuilder.addAttributeValue(Answer.HEIGHT_INDEX, new Numeric(heightParser(aDataOrig[Answer.HEIGHT_INDEX])));
                answerBuilder.addAttributeValue(Answer.DEGREE_INDEX, new Nominal(degreeParser(aDataOrig[Answer.DEGREE_INDEX])));
                answerBuilder.addAttributeValue(Answer.WHYCOURSE_INDEX, new Nominal(whyCourseParser(aDataOrig[Answer.WHYCOURSE_INDEX])));
                answerBuilder.addAttributeValue(Answer.PROLANGUAGE_INDEX, new Multiple(proLanguagesParser(aDataOrig[Answer.PROLANGUAGE_INDEX])));
                answerBuilder.addAttributeValue(Answer.PHONEOS_INDEX, new Nominal(phoneOSParser(aDataOrig[Answer.PHONEOS_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_DATABASE_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_DATABASE_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PREDICTIVE_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PREDICTIVE_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_SIMILAR_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_SIMILAR_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_VISUALIZATION_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_VISUALIZATION_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PATTERNSET_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PATTERNSET_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PATTERNSEQ_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PATTERNSEQ_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PATTERNGRAPHS_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PATTERNGRAPHS_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PATTERNTEXT_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PATTERNTEXT_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_PATTERNIMAGES_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_PATTERNIMAGES_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_CODE_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_CODE_INDEX])));
                answerBuilder.addAttributeValue(Answer.HOWINTERESTED_OFFSHELF_INDEX, new Nominal(howInterestedSetParser(aDataOrig[Answer.HOWINTERESTED_OFFSHELF_INDEX])));
                answerBuilder.addAttributeValue(Answer.WHICHGAMES_INDEX, new Multiple(whichGamesParser(aDataOrig[Answer.WHICHGAMES_INDEX])));
                answerBuilder.addAttributeValue(Answer.COMMUTE_INDEX, new Multiple(commutesParser(aDataOrig[Answer.COMMUTE_INDEX])));
                answerBuilder.addAttributeValue(Answer.FOURRANDOMNUMBER_INDEX, new Multiple(fourRandomsParser(aDataOrig[Answer.FOURRANDOMNUMBER_INDEX])));
                answerBuilder.addAttributeValue(Answer.PICKANUMBER_INDEX, new Nominal(pickNumberParser(aDataOrig[Answer.PICKANUMBER_INDEX])));
                data.add(answerBuilder.buildPoint());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded " + data.size() + " Answers.");
        return data;
    }

    private static PickANumber pickNumberParser(String value) {
        switch (value.toLowerCase()){
            case "7": return PickANumber._7;
            case "9": return PickANumber._9;
            case "asparagus": return PickANumber.Asparagus;
            case "13": return PickANumber._13;
            default: throw new RuntimeException("no pickus numbus");
        }
    }

    private static List<SpaceComparable> fourRandomsParser(String value) {
        List<SpaceComparable> fourRandomNums = new ArrayList<>();
        for (String randomNum : value.split("[,;]")) {
            try {
                fourRandomNums.add(new Numeric(fourRandomParser(randomNum)));
            } catch (Exception e) {

            }
        }
        return fourRandomNums;
    }

    private static double fourRandomParser(String randomNum) throws Exception {
        return Double.parseDouble(randomNum);
    }

    private static List<SpaceComparable> commutesParser(String value) {
        List<SpaceComparable> commutes = new ArrayList<>();
        for (String commuteMean : value.split("[,;]")) {
            commutes.add(new Nominal(commuteParser(commuteMean)));
        }
        return commutes;
    }
    private static CommuteMean commuteParser(String commute) {
        switch (commute.toLowerCase()) {
            case "bike": return CommuteMean.Bike;
            case "walk": return CommuteMean.Walk;
            case "train": return CommuteMean.Train;
            case "bus": return CommuteMean.Bus;
            case "car": return CommuteMean.Car;
            case "metro": return CommuteMean.Metro;
            case "other": return CommuteMean.Other;
            default: throw new RuntimeException("commutos no existos");
        }
    }

    private static List<SpaceComparable> whichGamesParser(String value) {
        List<SpaceComparable> games = new ArrayList<>();
        for (String game : value.split("[,;]")) {
            games.add(new Nominal(whichGameParser(game)));
        }
        return games;
    }
    private static Game whichGameParser(String game) {
        switch (game) {
            case "Candy Crush": return Game.CandyCrush;
            case "Wordfeud": return Game.WordFeud;
            case "Minecraft": return Game.MineCraft;
            case "FarmVille": return Game.FarmVille;
            case "Fifa 2017": return Game.FiFa2017;
            case "Star Wars Battlefield": return Game.StarWarsBattlefield;
            case "Life is Strange": return Game.LifeIsStrange;
            case "Battlefield 4": return Game.Battlefield4;
            case "Journey": return Game.Journey;
            case "Gone Home": return Game.GoneHome;
            case "Stanley Parable": return Game.StanleyParable;
            case "Call of Duty: Black Ops": return Game.CallOfDutyBlackOps;
            case "Rocket League": return Game.RocketLeague;
            case "Bloodthorne": return Game.BloodThorne;
            case "Rise of the Tomb Raider": return Game.RiseOfTheTombRaider;
            case "The Witness": return Game.TheWitness;
            case "Her Story": return Game.HerStory;
            case "Fallout 4": return Game.Fallout4;
            case "Dragon Age: Inquisition": return Game.DragonAgeInquisition;
            case "Counter Strike_ GO": return Game.CounterStrikeGO;
            case "Angry Birds": return Game.AngryBirds;
            case "The Last of Us": return Game.TheLastOfUs;
            case "The Magic Circle": return Game.TheMagicCircle;
            case "I have not played any of these games": return Game.None;
            default:
                throw new RuntimeException("game no existo");
        }
    }

    private static PreferRange howInterestedSetParser(String prefered) {
        switch (prefered.toLowerCase()) {
            case "not interested": return PreferRange.NotInterested;
            case "meh": return PreferRange.Meh;
            case "sounds interesting": return PreferRange.SoundsInteresting;
            case "very interesting": return PreferRange.VeryInteresting;
            default:
                throw new RuntimeException("invalid preferrange");
        }
    }


    private static PhoneOS phoneOSParser(String value) {
        switch (value.toLowerCase()) {
            case "windows": return PhoneOS.Windows;
            case "ios": return PhoneOS.IOS;
            case "android": return PhoneOS.Android;
            case "ubuntu touch": return PhoneOS.UbuntuTouch;
            case "other": return PhoneOS.Other;
            default: throw new RuntimeException("OS no existo");
        }
    }

    private static List<SpaceComparable> proLanguagesParser(String value) {
        List<SpaceComparable> languages = new ArrayList<>();
        for (String lang : value.split("[,;]")) {
            languages.add(new Nominal(proLanguageParser(lang)));
        }
        return languages;
    }

    private static Language proLanguageParser(String lang){
        switch (lang.toLowerCase()) {
            case "c#":
            case "csharp":
            case "c sharp":
            case "c #":
                return Language.CSharp;
            case "java":
                return Language.Java;
            case "sql":
                return Language.SQL;
            case "javascript":
            case "js":
                return Language.JavaScript;
            case "python":
                return Language.Python;
            case "c++":
            case "cpp":
                return Language.Cpp;
            case "c":
                return Language.C;
            case "php":
                return Language.PHP;
            case "swift":
                return Language.Swift;
            case "ruby":
                return Language.Ruby;
            default:
                return Language.Other;
        }
    }

    private static WhyCourse whyCourseParser(String value) {
        switch (value.toLowerCase()) {
            case "i am interested in the subject": return WhyCourse.Interested;
            case "it may help me to find a job": return WhyCourse.Job;
            case "the other optional courses were least appealing": return WhyCourse.OtherCoursesUnappealing;
            case "This was a mandatory course for me": return WhyCourse.Mandatory;
            default: return WhyCourse.Other;
        }
    }

    private static DegreeEnum degreeParser(String value) {
        switch (value.toLowerCase()) {
            case "games-a": return DegreeEnum.Games_A;
            case "games-t": return DegreeEnum.Games_T;
            case "sdt-se": return DegreeEnum.SDT_SE;
            case "sdt-dt": return DegreeEnum.SDT_DT;
            case "swu": return DegreeEnum.SWU;
            case "guest student": return DegreeEnum.Guest;
            default: return DegreeEnum.Other;
        }
    }

    private static double heightParser(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static double shoeParser(String value) {
        return Float.parseFloat(value.replace(',', '.'));
    }

    private static double ageParser(String value){
        return Double.parseDouble(value);
    }

    private static PreferRange preferRangeParser(String value) {
        switch (value.toLowerCase()) {
            case "not interested":
                return PreferRange.NotInterested;
            case "meh":
                return PreferRange.Meh;
            case "sounds interesting":
                return PreferRange.SoundsInteresting;
            case "very interesting":
                return PreferRange.VeryInteresting;
            default:
                throw new RuntimeException("something sketchy");
        }
    }

    private static Gender genderParser(String value) {
        if(value.length()>0) {
            char genderChar = value.toLowerCase().charAt(0);
            return genderChar == 'm' ? Gender.Male : genderChar == 'f' ? Gender.Female : Gender.None;
        }
        return Gender.None;
    }

}
