package Individual;

import Common.DataStructures.Cluster.Cluster;
import Common.DataTypes.Binary;
import Common.DataTypes.ComparerWrapper;
import Common.DataTypes.Sequence;
import Common.DataTypes.Nominal;
import Common.Interfaces.ClassifiablePoint;
import Common.Interfaces.Classifier;
import Common.Interfaces.NDimensionalPoint;
import Common.Preprocessing.Normalizer;
import Common.Statistics.BooleanEvaluationSuite;
import Common.Statistics.PurityChecker;
import Common.Statistics.EvaluationStatistics;
import Data.Answer.*;
import Data.Answer.SpecialisedTypes.AnswerCanCalcMean;
import Data.Answer.SpecialisedTypes.ClassifiableAnswer;
import Lab2.Classification.ID3DecisionTreeClassifier;
import Lab2.Classification.KNearestNeighboursClassifier;
import Lab3.Apriori;
import Lab3.AssociationRule;
import Lab3.ItemSet;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ander on 15-04-2017.
 */
public class IndividualProjectRunner {
    public static void main(String[] args){
        List<NDimensionalPoint> answers = AnswerDataLoader.LoadAllAnswerData();
        answers = new ArrayList<>(new Normalizer().NormilizeData(answers, new AnswerBuilder(new Answer())));
        Collections.shuffle(answers, new Random(args[0] == null ? 1 : Integer.parseInt(args[0])));
        //printData(answers);

        System.out.println("\nStarting KNN\n");
        runKNN(answers);

        System.out.println("\nStarting ID3\n");
        runID3(answers);

        System.out.println("\nStarting Apriori\n");
        runApriori(answers);

        System.out.println("\nStarting KMeans\n");
        runKMeans(answers);

        System.out.println("\nStarting KMedoids\n");
        runKMedoids(answers);
    }

    private static void printData(List<NDimensionalPoint> answers){
        for (NDimensionalPoint answer : answers) {
            System.out.println(answer);
        }
    }

    private static void runKNN(List<NDimensionalPoint> answers) {
        List<ClassifiablePoint<Binary>> answersClassifiableDegree = answers
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Binary>(((Answer)nDimensionalPoint), Answer.GENDER_INDEX)
                ).collect(Collectors.toList());

        List<ClassifiablePoint<Binary>> trainingSet = answersClassifiableDegree.subList(0, (int) (0.8 * answersClassifiableDegree.size()));
        List<ClassifiablePoint<Binary>> testSet = answersClassifiableDegree.subList((int) (0.2 * answersClassifiableDegree.size()), answersClassifiableDegree.size());

        System.out.println("Training size: " + trainingSet.size() + " test size: " + testSet.size());

        Classifier classifierKNN = new KNearestNeighboursClassifier(3);
        classifierKNN.trainWithSet(trainingSet);

        EvaluationStatistics evaluationStatistics = new BooleanEvaluationSuite().testClassifier(classifierKNN, new ArrayList<>(testSet));
        System.out.println(evaluationStatistics);
    }

    private static void runID3(List<NDimensionalPoint> answers) {
        List<ClassifiablePoint<Binary>> answersClassifiableGender = answers
                .stream().map(nDimensionalPoint ->
                        new ClassifiableAnswer<Binary>(((Answer)nDimensionalPoint), Answer.GENDER_INDEX)
                ).collect(Collectors.toList());

        List<ClassifiablePoint<Binary>> trainingSet = answersClassifiableGender.subList(0, (int) (0.8 * answersClassifiableGender.size()));
        List<ClassifiablePoint<Binary>> testSet = answersClassifiableGender.subList((int) (0.2 * answersClassifiableGender.size()), answersClassifiableGender.size());

        System.out.println("Training size: " + trainingSet.size() + " test size: " + testSet.size());

        Classifier classifierID3 = new ID3DecisionTreeClassifier();
        classifierID3.trainWithSet(trainingSet);
        EvaluationStatistics evaluationStatistics = new BooleanEvaluationSuite().testClassifier(classifierID3, new ArrayList<>(testSet));
        System.out.println(evaluationStatistics);
    }

    private static void runApriori(List<NDimensionalPoint> answers) {

        List<Sequence<Nominal>> nominalSequence = answers.stream()
                .map(nDimensionalPoint -> (Sequence<Nominal>)nDimensionalPoint.get(Answer.PROLANGUAGE_INDEX))
                .collect(Collectors.toList());

        ComparerWrapper<Nominal>[][] elements = new ComparerWrapper[nominalSequence.size()][];
        for (int i = 0; i < nominalSequence.size(); i++) {
            Sequence<Nominal> sequence = nominalSequence.get(i);
            List<Nominal> elements1 = sequence.getElements();
            for (int i1 = 0; i1 < elements1.size(); i1++) {
                Nominal nominal = elements1.get(i1);
                if(elements[i] == null) {
                    elements[i] = new ComparerWrapper[elements1.size()];
                }
                elements[i][i1] =new ComparerWrapper<>(nominal);
            }
        }
        Apriori<ComparerWrapper<Nominal>> comparerWrapperApriori = new Apriori<>();
        List<AssociationRule> aprioriResult = comparerWrapperApriori.generateAssociationRules(elements, 10, 0.84);
        for (AssociationRule associationRule : aprioriResult) {
            System.out.println(associationRule);
        }
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

        Collection<KMedoidCluster> foundClustersKMeans = new KMedoid().KMedoidPartition(4, answersClassifiableDegree);
        PurityChecker clusterPurityChecker = new PurityChecker();
        for (Cluster foundClustersKMean : foundClustersKMeans) {
            System.out.println(clusterPurityChecker.checkCluster(foundClustersKMean.points.stream().map(nDimensionalPoint -> (ClassifiablePoint<Nominal>)nDimensionalPoint).collect(Collectors.toList())));
            //System.out.println(foundClustersKMean);
        }
    }
}
