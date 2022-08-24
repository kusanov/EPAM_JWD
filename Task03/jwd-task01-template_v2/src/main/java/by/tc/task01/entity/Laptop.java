package by.tc.task01.entity;

public class Laptop extends Appliance{

    public double battery_Capacity;
    public String OS;
    public int memory_Rom;
    public int systemMemory;
    public double CPU;
    public double display_Inchs;

    public Laptop(String name, double battery_Capacity, String OS, int memory_Rom, int system_Memory, double CPU, double display_Inchs) {
        super(name);
        this.battery_Capacity = battery_Capacity;
        this.OS = OS;
        this.memory_Rom = memory_Rom;
        this.systemMemory = system_Memory;
        this.CPU = CPU;
        this.display_Inchs = display_Inchs;
    }

    public Laptop() {
        super("Laptop");
    }

    public Laptop(String laptop) {
    }

    public double getBattery_Capacity() {
        return battery_Capacity;
    }

    public void setBattery_Capacity(double battery_Capacity) {
        this.battery_Capacity = battery_Capacity;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public int getMemory_Rom() {
        return memory_Rom;
    }

    public void setMemory_Rom(int memory_Rom) {
        this.memory_Rom = memory_Rom;
    }

    public int getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(int systemMemory) {
        this.systemMemory = systemMemory;
    }

    public double getCPU() {
        return CPU;
    }

    public void setCPU(double CPU) {
        this.CPU = CPU;
    }


    public double getDisplay_Inchs() {
        return display_Inchs;
    }

    public void setDisplay_Inchs(double display_Inchs) {
        this.display_Inchs = display_Inchs;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "battery_Capacity=" + battery_Capacity +
                ", OS='" + OS + '\'' +
                ", memory_Rom=" + memory_Rom +
                ", systemMemory=" + systemMemory +
                ", CPU=" + CPU +
                ", display_Inchs=" + display_Inchs +
                '}';
    }
}

