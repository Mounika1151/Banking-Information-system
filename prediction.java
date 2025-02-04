import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;

public class FraudPrediction {

    public static void main(String[] args) {
        try {
            // Load the model
            Classifier classifier = (Classifier) SerializationHelper.read("fraudDetectionModel.model");

            // Load the dataset for prediction
            DataSource source = new DataSource("new_transaction_data.arff");
            Instances data = source.getDataSet();

            // Set the class index (target variable)
            data.setClassIndex(data.numAttributes() - 1);

            // Make predictions
            for (int i = 0; i < data.numInstances(); i++) {
                double label = classifier.classifyInstance(data.instance(i));
                System.out.println("Transaction " + (i + 1) + ": " + data.classAttribute().value((int) label));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
