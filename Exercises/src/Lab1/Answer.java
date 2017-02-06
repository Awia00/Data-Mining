package Lab1;

import java.util.*;
enum Gender
{
    None,
    Male,
    Female
}
enum PreferRange
{
    NotInterested,
    Meh,
    SoundsInteresting,
    VeryInteresting
}
public class Answer
{
    String timeStamp;
    int age;
    Gender gender;
    float shoeSize;
    float height;
    String degree;
    String whyCourse;
    List<String> profficientLanguages;
    String phoneOS;
    PreferRange[] howInterested;
    String[] whichGamesPlayed;
    String[] commutePossibilities;
    String[] traversalOfITU;
    String therbForttGlag;
    int[] fourRandomNumbers;
    int pickANumber;
    String favoriteFilm;
    String favoriteTV;
    String favoriteGame;
    char row;
    int seat;

    public Answer(String timeStamp, int age, Gender gender, float shoeSize, float height, String degree, String whyCourse, List<String> profficientLanguages, String phoneOS, PreferRange[] howInterested, String[] whichGamesPlayed, String[] commutePossibilities, String[] traversalOfITU, String therbForttGlag, int[] fourRandomNumbers, int pickANumber, String favoriteFilm, String favoriteTV, String favoriteGame, char row, int seat) {
        this.timeStamp = timeStamp;
        this.age = age;
        this.gender = gender;
        this.shoeSize = shoeSize;
        this.height = height;
        this.degree = degree;
        this.whyCourse = whyCourse;
        this.profficientLanguages = profficientLanguages;
        this.phoneOS = phoneOS;
        this.howInterested = howInterested;
        this.whichGamesPlayed = whichGamesPlayed;
        this.commutePossibilities = commutePossibilities;
        this.traversalOfITU = traversalOfITU;
        this.therbForttGlag = therbForttGlag;
        this.fourRandomNumbers = fourRandomNumbers;
        this.pickANumber = pickANumber;
        this.favoriteFilm = favoriteFilm;
        this.favoriteTV = favoriteTV;
        this.favoriteGame = favoriteGame;
        this.row = row;
        this.seat = seat;
    }

    private PreferRange preferRangeParser(String value)
    {
        switch (value.toLowerCase())
        {
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
    public Answer(String[] data)
    {
        int index = 0;
        this.timeStamp = data[index++];
        this.age = Integer.parseInt(data[index++]);
        char gender = data[index++].toLowerCase().charAt(0);
        this.gender = gender == 'm' ? Gender.Male : gender == 'f' ? Gender.Female: Gender.None;
        this.shoeSize = Float.parseFloat(data[index++].replace(',', '.'));
        try{
            this.height = Float.parseFloat(data[index++]);
        }catch (Exception e)
        {

        }

        this.degree = data[index++];
        this.whyCourse = data[index++];
        this.profficientLanguages = Arrays.asList(data[index++].split(",|;"));
        this.phoneOS = data[index++];
        this.howInterested = new PreferRange[]{ preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]), preferRangeParser(data[index++]) };
        this.whichGamesPlayed = data[index++].split(";");
        this.commutePossibilities = data[index++].split(",|;");
        this.traversalOfITU = new String[] { data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++], data[index++] };
        this.therbForttGlag = data[index++];
        try{
            this.fourRandomNumbers = new int[4];
            this.fourRandomNumbers = new int[]{Integer.parseInt(data[index++]), Integer.parseInt(data[index++]), Integer.parseInt(data[index++]), Integer.parseInt(data[index++])};
        }catch (Exception e) {} // supressed;
        try {
            this.pickANumber = Integer.parseInt(data[index++]);
        } catch (Exception e) {} // supressed;
        this.favoriteFilm = data[index++];
        this.favoriteTV = data[index++];
        this.favoriteGame = data[index++];
        this.row = data[index++].charAt(0);

        try{
            Integer.parseInt(data[index++]);
        }catch (Exception e)
        {
            this.seat = -1;
        }
    }

    @Override
    public String toString() {
        return "Answer{" +
                "timeStamp='" + timeStamp + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", shoeSize=" + shoeSize +
                ", height=" + height +
                ", degree='" + degree + '\'' +
                ", whyCourse='" + whyCourse + '\'' +
                ", profficientLanguages=" + profficientLanguages +
                ", phoneOS='" + phoneOS + '\'' +
                ", howInterested=" + Arrays.toString(howInterested) +
                ", whichGamesPlayed=" + Arrays.toString(whichGamesPlayed) +
                ", commutePossibilities=" + Arrays.toString(commutePossibilities) +
                ", traversalOfITU=" + Arrays.toString(traversalOfITU) +
                ", therbForttGlag='" + therbForttGlag + '\'' +
                ", fourRandomNumbers=" + Arrays.toString(fourRandomNumbers) +
                ", PickANumber=" + pickANumber +
                ", favoriteFilm='" + favoriteFilm + '\'' +
                ", favoriteTV='" + favoriteTV + '\'' +
                ", favoriteGame='" + favoriteGame + '\'' +
                ", row=" + row +
                ", seat=" + seat +
                '}';
    }
}