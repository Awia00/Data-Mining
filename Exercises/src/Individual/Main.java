package Individual;

import Common.DataStructures.Cluster.Cluster;
import Common.DataTypes.Binary;
import Common.DataTypes.Nominal;
import Common.Interfaces.Classifiable;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;
import Common.Interfaces.NDimensionalPoint;
import Common.Preprocessing.Normalizer;
import Common.Statistics.BooleanEvaluationSuite;
import Common.Statistics.PurityChecker;
import Common.Statistics.EvaluationStatistics;
import Data.Answer.*;
import Data.Answer.SubAnswers.AnswerCanCalcMean;
import Lab2.Classification.ID3DecisionTreeClassifier;
import Lab2.Classification.KNearestNeighboursClassifier;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ander on 15-04-2017.
 */
public class Main {
    public static void main(String[] args){
        List<NDimensionalPoint> answers = AnswerDataLoader.LoadAllAnswerData();
        answers = new ArrayList<>(new Normalizer().NormilizeData(answers, new AnswerBuilder(new Answer())));
        Collections.shuffle(answers);
        //print(answers);

        //List<NDimensionalPoint> trainingSet = answers.subList(0, (int) (0.7 * answers.size()));
        //List<NDimensionalPoint> testSet = answers.subList((int) (0.7 * answers.size()), answers.size());
        //System.out.println("Training size: " + trainingSet.size() + " test size: " + testSet.size());

        runKNN(answers);

        runID3(answers);

        runApriori(answers);

        runKMeans(answers);

        runKMedoids(answers);
    }

    private static void print(List<NDimensionalPoint> answers){
        for (NDimensionalPoint answer : answers) {
            System.out.println(answer);
        }
    }

    private static void runApriori(List<NDimensionalPoint> answers) {

    }

    private static void runID3(List<NDimensionalPoint> answers) {
        List<ClassifiablePoint<Binary>> answersClassifiableGender = answers
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Binary>(((Answer)nDimensionalPoint), Answer.GENDER_INDEX)
                ).collect(Collectors.toList());

        List<ClassifiablePoint<Binary>> trainingSet = answersClassifiableGender.subList(0, (int) (0.8 * answersClassifiableGender.size()));
        List<ClassifiablePoint<Binary>> testSet = answersClassifiableGender.subList((int) (0.2 * answersClassifiableGender.size()), answersClassifiableGender.size());

        Classifier classifierID3 = new ID3DecisionTreeClassifier();
        classifierID3.trainWithSet(trainingSet);
        EvaluationStatistics evaluationStatistics = new BooleanEvaluationSuite().testClassifier(classifierID3, new ArrayList<>(testSet));
        System.out.println(evaluationStatistics);
    }

    private static void runKNN(List<NDimensionalPoint> answers) {
        List<ClassifiablePoint<Nominal>> answersClassifiableDegree = answers
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Nominal>(((Answer)nDimensionalPoint), Answer.DEGREE_INDEX)
                ).collect(Collectors.toList());

        List<ClassifiablePoint<Nominal>> trainingSet = answersClassifiableDegree.subList(0, (int) (0.8 * answersClassifiableDegree.size()));
        List<ClassifiablePoint<Nominal>> testSet = answersClassifiableDegree.subList((int) (0.2 * answersClassifiableDegree.size()), answersClassifiableDegree.size());

        Classifier classifierKNN = new KNearestNeighboursClassifier(5);
        classifierKNN.trainWithSet(trainingSet);
        PurityChecker purityChecker = new PurityChecker();

        purityChecker.checkClassifier(classifierKNN, testSet);
    }

    private static void runKMeans(List<NDimensionalPoint> answers){

        List<NDimensionalPoint> answersWithoutMultiple = answers
                .stream().map(nDimensionalPoint ->
                        ((Answer)nDimensionalPoint).convertToAnswerCanCalcMean()
                ).collect(Collectors.toList());

        List<ClassifiablePoint<Nominal>> answersClassifiableDegree = answersWithoutMultiple
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Nominal>(((Answer)nDimensionalPoint), Answer.DEGREE_INDEX)
                ).collect(Collectors.toList());

        System.out.println("Starting KMeans");
        Collection<KMeanCluster> foundClustersKMeans = new KMeans().KMeansPartition(4, answersClassifiableDegree, new AnswerBuilder(new AnswerCanCalcMean()));
        PurityChecker clusterPurityChecker = new PurityChecker();
        for (Cluster foundClustersKMean : foundClustersKMeans) {
            System.out.println(clusterPurityChecker.checkCluster(foundClustersKMean.points.stream().map(nDimensionalPoint -> (ClassifiablePoint<Nominal>)nDimensionalPoint).collect(Collectors.toList())));
            //System.out.println(foundClustersKMean);
        }
    }

    private static void runKMedoids(List<NDimensionalPoint> answers){
        List<ClassifiablePoint<Nominal>> answersClassifiableDegree = answers
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Nominal>(((Answer)nDimensionalPoint), Answer.DEGREE_INDEX)
                ).collect(Collectors.toList());

        System.out.println("Starting KMedoids");
        Collection<KMedoidCluster> foundClustersKMeans = new KMedoid().KMedoidPartition(4, answersClassifiableDegree);
        PurityChecker clusterPurityChecker = new PurityChecker();
        for (Cluster foundClustersKMean : foundClustersKMeans) {
            System.out.println(clusterPurityChecker.checkCluster(foundClustersKMean.points.stream().map(nDimensionalPoint -> (ClassifiablePoint<Nominal>)nDimensionalPoint).collect(Collectors.toList())));
            //System.out.println(foundClustersKMean);
        }
    }
}
