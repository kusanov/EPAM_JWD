package by.tc.task01.entity;

public class TabletPC extends Appliance{

    public int battery_Capacity;
    public int display_Inches;
    public int memory_Rom;
    public int flash_Memory_Capacity;
    public String color;

    public TabletPC() {
        super("TabletPC");
    }

    public TabletPC(String tabletPC) {
        super(tabletPC);
    }


    public int getBattery_Capacity() {
        return battery_Capacity;
    }

    public void setBattery_Capacity(int battery_Capacity) {
        this.battery_Capacity = battery_Capacity;
    }

    public int getDisplay_Inches() {
        return display_Inches;
    }

    public void setDisplay_Inches(int display_Inches) {
        this.display_Inches = display_Inches;
    }

    public int getMemory_Rom() {
        return memory_Rom;
    }

    public void setMemory_Rom(int memory_Rom) {
        this.memory_Rom = memory_Rom;
    }

    public int getFlash_Memory_Capacity() {
        return flash_Memory_Capacity;
    }

    public void setFlash_Memory_Capacity(int flash_Memory_Capacity) {
        this.flash_Memory_Capacity = flash_Memory_Capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TabletPC(String name, int battery_Capacity, int display_Inches, int memory_Rom, int flash_Memory_Capacity, String color) {
        super(name);
        this.battery_Capacity = battery_Capacity;
        this.display_Inches = display_Inches;
        this.memory_Rom = memory_Rom;
        this.flash_Memory_Capacity = flash_Memory_Capacity;
        this.color = color;
    }

    @Override
    public String toString() {
        return "TabletPC{" +
                "battery_Capacity=" + battery_Capacity +
                ", display_Inches=" + display_Inches +
                ", memory_Rom=" + memory_Rom +
                ", flash_Memory_Capacity=" + flash_Memory_Capacity +
                ", color='" + color + '\'' +
                '}';
    }
}
