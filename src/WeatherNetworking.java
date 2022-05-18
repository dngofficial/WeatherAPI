
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherNetworking {

    private String baseUrl;
    private String apiKey;


    public WeatherNetworking()
    {
        baseUrl = "http://api.weatherapi.com/v1";
        apiKey = "8823c95dd63e46079a3170028221805";
    }

    public String makeAPICallForForecast(String zipCode)
    {
        String endPoint = "/current.json";
        String url = baseUrl + endPoint + "?q=" + zipCode + "&key=" + apiKey;

        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CurrentWeather parseCurrent(String json)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject currentObj = jsonObj.getJSONObject("current");
        JSONObject condition1 = currentObj.getJSONObject("condition");

        double currentF = currentObj.getDouble("temp_f");
        double currentC = currentObj.getDouble("temp_c");
       String filePath = condition1.getString("icon");
        String condition = condition1.getString("text");

        return new CurrentWeather(currentF, currentC, filePath, condition);
    }
}