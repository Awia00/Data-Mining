package Lab4;

import Common.Interfaces.SpaceComparable;
import Lab4.data.DataLoader;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.ArrayList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        ArrayList<SpaceComparable> irisData = DataLoader.LoadAllIrisData();

        for (SpaceComparable irisDatum : irisData) {
            System.out.println(irisDatum);
        }

        //Second step --> do the clustering using k-means!
        ArrayList<KMeanCluster> FoundClusters_KMeans = new KMeans().KMeansPartition(3, irisData);

        //Third step --> do the clustering using k-medoids!
        ArrayList<KMedoidCluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
    }

}
