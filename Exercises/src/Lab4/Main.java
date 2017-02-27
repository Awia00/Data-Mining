package Lab4;

import Common.Interfaces.NDimensionalPoint;
import Lab4.data.DataLoader;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;

import java.util.Collection;
import java.util.List;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        List<NDimensionalPoint> irisData = DataLoader.LoadAllIrisData();

        //Second step --> do the clustering using k-means!
        Collection<KMeanCluster> foundClustersKMeans = new KMeans().KMeansPartition(3, irisData);
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
