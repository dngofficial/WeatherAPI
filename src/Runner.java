import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        WeatherNetworking api = new WeatherNetworking();
        System.out.println(api.makeAPICallForForecast("11229"));
    }
}