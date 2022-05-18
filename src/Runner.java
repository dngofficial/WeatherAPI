import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        WeatherNetworking api = new WeatherNetworking();
        api.parseCurrent(api.makeAPICallForForecast("11229")).printOutAll();

    }
}