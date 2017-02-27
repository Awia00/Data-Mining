package Lab4.data;

import java.util.ArrayList;
import java.util.Collection;

public class Normalizer {

    public Collection<Iris> findNormilizers(Collection<Iris> elements)
    {
        Collection<Iris> normalizedElements = new ArrayList<>();
        float max_sepal_Length = -1;
        float max_sepal_Width = -1;
        float max_petal_Length = -1;
        float max_petal_Width = -1;
        for (Iris iris : elements) {
            max_sepal_Length = Math.max(iris.Sepal_Length, max_petal_Length);
            max_sepal_Width = Math.max(iris.Sepal_Width, max_petal_Width);
            max_petal_Length = Math.max(iris.Petal_Length, max_sepal_Length);
            max_petal_Width = Math.max(iris.Petal_Width, max_sepal_Width);
        }
        for (Iris element : elements) {
            normalizedElements.add(new Iris(
                    element.Sepal_Length/max_sepal_Length,
                    element.Sepal_Width/max_sepal_Width,
                    element.Petal_Length/max_petal_Length,
                    element.Petal_Width/max_petal_Width,
                    element.Class));
        }
        return normalizedElements;
    }
}
