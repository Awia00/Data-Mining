package Lab4;

import Common.Interfaces.NDimensionalPoint;
import Lab4.data.DataLoader;
import Lab4.data.Iris;
import Lab4.data.IrisBuilder;
import Lab4.data.Normalizer;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        List<NDimensionalPoint> irisData = DataLoader.LoadAllIrisData();
        irisData = (List<NDimensionalPoint>) new Normalizer().NormilizeData(irisData, new Iris(), new IrisBuilder());

        //Second step --> do the clustering using k-means!
        Collections.shuffle(irisData);
        Collection<KMeanCluster> foundClustersKMeans = new KMeans().KMeansPartition(3, irisData, new IrisBuilder());
        for (KMeanCluster foundClustersKMean : foundClustersKMeans) {
            System.out.println(foundClustersKMean);
        }

        //Third step --> do the clustering using k-medoids!
        //Collection<KMedoidCluster> foundClustersKMedoids = KMedoid.KMedoidPartition(3, irisData);
        //for (KMedoidCluster foundClustersKMedoid : foundClustersKMedoids) {
        //    System.out.println(foundClustersKMedoid);
        //}
    }

}
