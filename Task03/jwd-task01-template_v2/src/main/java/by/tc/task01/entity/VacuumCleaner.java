package by.tc.task01.entity;

public class VacuumCleaner extends Appliance{

    public int power_Consumption;
    public String filter_Type;
    public String bag_Type;
    public String wand_Type;
    public int motor_Speed_Regulation;
    public int cleaning_Width;

    public VacuumCleaner(String name, int power_Consumption, String filter_Type, String bagType, String wand_Type, int motor_Speed_Regulation, int cleaning_Width) {
        super(name);
        this.power_Consumption = power_Consumption;
        this.filter_Type = filter_Type;
        this.bag_Type = bagType;
        this.wand_Type = wand_Type;
        this.motor_Speed_Regulation = motor_Speed_Regulation;
        this.cleaning_Width = cleaning_Width;
    }

    public int getPower_Consumption() {
        return power_Consumption;
    }

    public void setPower_Consumption(int power_Consumption) {
        this.power_Consumption = power_Consumption;
    }

    public String getFilter_Type() {
        return filter_Type;
    }

    public void setFilter_Type(String filter_Type) {
        this.filter_Type = filter_Type;
    }

    public String getBag_Type() {
        return bag_Type;
    }

    public void setBag_Type(String bag_Type) {
        this.bag_Type = bag_Type;
    }

    public String getWand_Type() {
        return wand_Type;
    }

    public void setWand_Type(String wand_Type) {
        this.wand_Type = wand_Type;
    }

    public int getMotor_Speed_Regulation() {
        return motor_Speed_Regulation;
    }

    public void setMotor_Speed_Regulation(int motor_Speed_Regulation) {
        this.motor_Speed_Regulation = motor_Speed_Regulation;
    }

    public int getCleaning_Width() {
        return cleaning_Width;
    }

    public void setCleaning_Width(int cleaning_Width) {
        this.cleaning_Width = cleaning_Width;
    }

    public VacuumCleaner(String name) {
        super(name);
    }

    public VacuumCleaner() {
        super("VacuumCleaner");
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "power_Consumption=" + power_Consumption +
                ", filter_Type='" + filter_Type + '\'' +
                ", bag_Type='" + bag_Type + '\'' +
                ", wand_Type='" + wand_Type + '\'' +
                ", motor_Speed_Regulation=" + motor_Speed_Regulation +
                ", cleaning_Width=" + cleaning_Width +
                '}';
    }
}
