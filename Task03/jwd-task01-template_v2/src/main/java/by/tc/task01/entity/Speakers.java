package by.tc.task01.entity;

public class Speakers extends Appliance{

    public int power_Consumption;
    public int number_Of_Speakers;
    public String frequency_Range;
    public int cord_Length;

    public Speakers(int power_Consumption, int number_Of_Speakers, String frequency_Range, int cord_Length) {
        this.power_Consumption = power_Consumption;
        this.number_Of_Speakers = number_Of_Speakers;
        this.frequency_Range = frequency_Range;
        this.cord_Length = cord_Length;
    }


    public int getPower_Consumption() {
        return power_Consumption;
    }

    public void setPower_Consumption(int power_Consumption) {
        this.power_Consumption = power_Consumption;
    }

    public int getNumber_Of_Speakers() {
        return number_Of_Speakers;
    }

    public void setNumber_Of_Speakers(int number_Of_Speakers) {
        this.number_Of_Speakers = number_Of_Speakers;
    }

    public String getFrequency_Range() {
        return frequency_Range;
    }

    public void setFrequency_Range(String frequency_Range) {
        this.frequency_Range = frequency_Range;
    }

    public int getCord_Length() {
        return cord_Length;
    }

    public void setCord_Length(int cord_Length) {
        this.cord_Length = cord_Length;
    }

    public Speakers(String name) {
        super(name);
    }

    public Speakers() {
        super("Speakers");
    }

    @Override
    public String toString() {
        return "Speakers{" +
                "power_Consumption=" + power_Consumption +
                ", number_Of_Speakers=" + number_Of_Speakers +
                ", frequency_Range='" + frequency_Range + '\'' +
                ", cord_Length=" + cord_Length +
                '}';
    }
}
