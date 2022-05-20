public class CurrentWeather
{
    private final double currentF;
    private final double currentC;
    private final String filePath;
    private final String condition;

    public CurrentWeather(double currentF, double currentC, String filePath, String condition)
    {
        this.currentC = currentC;
        this.currentF = currentF;
        this.filePath = filePath;
        this.condition = condition;
    }

    public double getCurrentC() {
        return currentC;
    }

    public double getCurrentF() {
        return currentF;
    }

    public String getCondition() {
        return condition;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void printOutAll()
    {
        System.out.print("" + currentF  + " " + currentC  + " " + filePath + " " + condition);
    }
}

