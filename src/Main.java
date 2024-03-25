import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        System.out.println("Wczytuje dane treningowe");
        List<Data> trainingData = DataReader.readData("iris_training.txt");
        System.out.println("Wczytuje dane testowe");
        List<Data> testData = DataReader.readData("iris_test.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj wartość parametru k: ");
        int k;
        k = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Klasyfikuje dane testowe");
        KNN model = new KNN(trainingData);
        model.predictTags(testData, k);
        model.displayCorrectPredictionsPercentage(testData);

        DataInput dataInput = new DataInput(scanner);


        while (true) {
            dataInput.printPromt();
            String input;
            Data tempData;
            try {
                System.out.println("Czekam na input...");
                input = dataInput.getInput();
                System.out.println("Wprowadzony input: " + input);
                tempData = DataReader.parseLine(input);
                List<Data> tempList = new ArrayList<>();
                tempList.add(tempData);
                model.predictTags(tempList, k);
            } catch (Exception e) {
                System.out.println("Zakończono działanie programu");
                e.printStackTrace();
                System.exit(0);
            }


        }


    }
}
