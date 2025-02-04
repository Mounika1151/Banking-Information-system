public class FraudPrediction {

    private Classifier classifier;

    public FraudPrediction() {
        try {
            // Load the model
            classifier = (Classifier) SerializationHelper.read("fraudDetectionModel.model");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFraudulentTransaction(String accountNumber, double amount) {
        try {
            // Load the dataset for prediction
            DataSource source = new DataSource("new_transaction_data.arff");
            Instances data = source.getDataSet();

            // Set the class index (target variable)
            data.setClassIndex(data.numAttributes() - 1);

            // Make predictions
            for (int i = 0; i < data.numInstances(); i++) {
                double label = classifier.classifyInstance(data.instance(i));
                String predictedClass = data.classAttribute().value((int) label);
                if (predictedClass.equals("fraudulent")) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
