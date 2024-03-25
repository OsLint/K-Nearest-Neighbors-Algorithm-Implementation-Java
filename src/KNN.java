import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class KNN {

    private final List<Data> learningData;


    public KNN(List<Data> learningData) {
        this.learningData = learningData;
    }

    public void predictTags(List<Data> unpredictedData, int k) {
        k = Math.max(1,k);
        k = Math.min(learningData.size(),k);
        System.out.println("Wprowadzone k=" + k);

        for (Data testData : unpredictedData) {
            List<Data> nearestNeighbors = findNearestNeighbors(testData, k);
            String predictedTag = determineTag(nearestNeighbors);
            testData.setPredictedTag(predictedTag);
            System.out.println(testData);
        }
    }


    private List<Data> findNearestNeighbors(Data testData, int k) {
        List<Data> nearestNeighbors = new ArrayList<>(k);
        learningData.sort(Comparator.comparingDouble(data -> euclideanDistance(data.getAttributes(), testData.getAttributes())));

        for (int i = 0; i < k; i++) {
            nearestNeighbors.add(learningData.get(i));
        }
        return nearestNeighbors;
    }

    private String determineTag(List<Data> nearestNeighbors) {
        int maxCount = 0;
        String mostFrequentTag = null;
        for (Data neighbor : nearestNeighbors) {
            int count = 1;
            for (Data otherNeighbor : nearestNeighbors) {
                if (neighbor != otherNeighbor && neighbor.getTag().equals(otherNeighbor.getTag())) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentTag = neighbor.getTag();
            }
        }
        return mostFrequentTag;
    }

    private double euclideanDistance(List<Double> x1, List<Double> x2) {
        double distance = 0;
        int commonAttributes = Math.min(x1.size(), x2.size());
        for (int i = 0; i < commonAttributes; i++) {
            distance += Math.pow(x1.get(i) - x2.get(i), 2);
        }
        return Math.sqrt(distance);
    }

    public void displayCorrectPredictionsPercentage(List<Data> dataAfterPrediction) {
        double length = dataAfterPrediction.size();
        double correctPredictions = getNumberOfCorrectPredictions(dataAfterPrediction);
        System.out.println("Liczba prawidłowo zaklasyfikowanych przykładów: " + correctPredictions);
        System.out.println("Procent prawidłowo zaklasyfikowanych przykładów: " + correctPredictions / length * 100);
    }

    private int getNumberOfCorrectPredictions(List<Data> dataAfterPrediction) {
        int numberOfCorrectPredictions = 0;
        for (Data data : dataAfterPrediction) {
            if (data.getTag().trim().equals(data.getPredictedTag())) {
                numberOfCorrectPredictions++;
            }
        }
        return numberOfCorrectPredictions;
    }
}