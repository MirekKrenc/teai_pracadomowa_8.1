package krenc.mirek.temperature.grabber.tools;

public class TemperatureConverter {

    private TemperatureConverter() {
    }

    public static final double CELSIUS_ZERO_KELVIN = 273.15;

    public static double fromKelvinToCelsius(double tempKelvin)
    {
        return tempKelvin - CELSIUS_ZERO_KELVIN;
    }

}
