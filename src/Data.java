
import java.util.List;

public class Data {
    private final List<Double> attributes;
    private final String tag;
    private String predictedTag = "";


    public Data(List<Double> attributes, String tag) {

        this.attributes = attributes;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "{" +
                "atr=" + attributes +
                ", tg='" + tag + '\'' +
                ", pdTg='" + predictedTag + '\'' +
                '}';
    }

    public void setPredictedTag(String predictedTag) {
        this.predictedTag = predictedTag;
    }

    public String getTag() {
        return tag;
    }

    public List<Double> getAttributes() {
        return attributes;
    }

    public String getPredictedTag() {
        return predictedTag;
    }
}