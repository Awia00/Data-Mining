package Lab4;

import Common.Interfaces.NDimensionalPoint;
import Common.Preprocessing.Normalizer;
import Common.Statistics.ClusterPurityChecker;
import Data.Iris.IrisBuilder;
import Data.Iris.IrisDataLoader;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        List<? extends NDimensionalPoint> irisData = IrisDataLoader.LoadAllIrisData();
        irisData = (List<NDimensionalPoint>) new Normalizer().NormilizeData(irisData, new IrisBuilder());

        //Second step --> do the clustering using k-means!
        Collections.shuffle(irisData);
        Collection<KMeanCluster> foundClustersKMeans = new KMeans().KMeansPartition(3, irisData, new IrisBuilder());
        ClusterPurityChecker clusterPurityChecker = new ClusterPurityChecker();
        for (KMeanCluster foundClustersKMean : foundClustersKMeans) {
            System.out.println(clusterPurityChecker.checkCluster(foundClustersKMean));
            System.out.println(foundClustersKMean);
        }

        //Third step --> do the clustering using k-medoids!
        Collection<KMedoidCluster> foundClustersKMedoids = KMedoid.KMedoidPartition(3, irisData);
        for (KMedoidCluster foundClustersKMedoid : foundClustersKMedoids) {
            System.out.println(clusterPurityChecker.checkCluster(foundClustersKMedoid));
            System.out.println(foundClustersKMedoid);
        }
    }

}
