import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataReader {

    public static List<Data> readData(String filePath) {
        List<Data> data = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Data parsedLine = parseLine(line);

                data.add(parsedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(data);
        return data;
    }

    public static Data parseLine(String input) {
        String[] elements = input.strip().split("\t");


        Double[] tempArr = new Double[elements.length - 1];

        for (int i = 0; i < elements.length - 1; i++) {
            tempArr[i] = Double.parseDouble(elements[i].replace(',', '.'));
        }
        List<Double> attributes = Arrays.asList(tempArr);
        String tag = elements[elements.length - 1];
        Data tempData = new Data(attributes, tag);

       // System.out.println(tempData);

        return tempData;
    }
}