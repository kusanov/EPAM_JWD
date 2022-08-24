package by.tc.task01.entity;

import by.tc.task01.entity.criteria.Criteria;


public class Refrigerator extends Appliance{

    public int power_Consumption;
    public double weight;
    public int freezer_Capacity;
    public double overall_Capacity;
    public double height;
    public double width;

    public Refrigerator(String name, Criteria[] criteriaList) {
        super(name);
    }

    public Refrigerator() {
        super("Refrigerator");
    }

    public Refrigerator(String name, int power_Consumption, double weight, int freezer_Capacity, double overall_Capacity, double height, double width) {
        super(name);
        this.power_Consumption = power_Consumption;
        this.weight = weight;
        this.freezer_Capacity = freezer_Capacity;
        this.overall_Capacity = overall_Capacity;
        this.height = height;
        this.width = width;
    }

    public Refrigerator(String refrigerator) {
    }

    public int getPower_Consumption() {
        return power_Consumption;
    }

    public void setPower_Consumption(int power_Consumption) {
        this.power_Consumption = power_Consumption;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getFreezer_Capacity() {
        return freezer_Capacity;
    }

    public void setFreezer_Capacity(int freezer_Capacity) {
        this.freezer_Capacity = freezer_Capacity;
    }

    public double getOverall_Capacity() {
        return overall_Capacity;
    }

    public void setOverall_Capacity(double overall_Capacity) {
        this.overall_Capacity = overall_Capacity;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "power_Consumption=" + power_Consumption +
                ", weight=" + weight +
                ", freezer_Capacity=" + freezer_Capacity +
                ", overall_Capacity=" + overall_Capacity +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
