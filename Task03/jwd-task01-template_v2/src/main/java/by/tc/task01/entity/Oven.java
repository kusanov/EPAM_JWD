package by.tc.task01.entity;

public class Oven extends Appliance{

    public int power_Consumption;
    public double weight;
    public int capacity;
    public double depth;
    public double height;
    public double width;

    public Oven(String name) {
        super(name);
    }

    public Oven(String name, int power_Consumption, double weight, int capacity, double depth, double height, double width) {
        super(name);
        this.power_Consumption = power_Consumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    public Oven() {
        super("Oven");
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
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
        return "Oven{" +
                "power_Consumption=" + power_Consumption +
                ", weight=" + weight +
                ", capacity=" + capacity +
                ", depth=" + depth +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
