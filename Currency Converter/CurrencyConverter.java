import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    // Replace with your API key from ExchangeRate-API
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Allow the user to choose the base currency and the target currency
        System.out.print("Enter the base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency (e.g., EUR): ");
        String targetCurrency = scanner.next().toUpperCase();

        // Take input from the user for the amount they want to convert
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            // Fetch real-time exchange rate
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

            // Currency Conversion
            double convertedAmount = convertCurrency(amount, exchangeRate);

            // Display Result
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = API_URL + baseCurrency + "/" + targetCurrency;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        // Parse JSON response
        JSONObject jsonResponse = new JSONObject(content.toString());
        if (!jsonResponse.getString("result").equals("success")) {
            throw new Exception("Failed to fetch exchange rate");
        }

        return jsonResponse.getDouble("conversion_rate");
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
