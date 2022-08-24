package by.tc.task01.entity;

public class Appliance {

    private String name;
    private Appliance appliance;

    public Appliance(String name) {
        this.name = name;
    }

    public Appliance() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Appliance createAppliance(String st) {
        Appliance appliance = new Appliance();
        String[] words;
        String st2;
        String ap = st.split(" :")[0];
        switch (ap) {
            case "Oven":
                appliance = new Oven("Oven");
                st2 = st.split(" : ")[1]; // Oven
                words = st2.split("=");
                ((Oven) appliance).setPower_Consumption(Integer.parseInt(words[1].split(",")[0]));
                ((Oven) appliance).setWeight(Double.parseDouble(words[2].split(",")[0]));
                ((Oven) appliance).setCapacity(Integer.parseInt(words[3].split(",")[0]));
                ((Oven) appliance).setDepth(Double.parseDouble(words[4].split(",")[0]));
                ((Oven) appliance).setHeight(Double.parseDouble(words[5].split(",")[0]));
                ((Oven) appliance).setWidth(Double.parseDouble(words[6].split(";")[0]));
                return appliance;
            case "Laptop":
                appliance = new Laptop("Laptop");
                st2 = st.split(" : ")[1];
                words = st2.split("=");
                ((Laptop) appliance).setBattery_Capacity(Double.parseDouble(words[1].split(",")[0]));
                ((Laptop) appliance).setOS((words[2].split(",")[0]));
                ((Laptop) appliance).setMemory_Rom(Integer.parseInt(words[3].split(",")[0]));
                ((Laptop) appliance).setSystemMemory(Integer.parseInt(words[4].split(",")[0]));
                ((Laptop) appliance).setCPU(Double.parseDouble(words[5].split(",")[0]));
                ((Laptop) appliance).setDisplay_Inchs(Double.parseDouble(words[6].split(",")[0]));
                return appliance;
            case "Refrigerator":
                appliance = new Refrigerator("Refrigerator");
                st2 = st.split(" : ")[1]; // Oven
                words = st2.split("=");
                ((Refrigerator) appliance).setPower_Consumption(Integer.parseInt(words[1].split(",")[0]));
                ((Refrigerator) appliance).setWeight(Double.parseDouble(words[2].split(",")[0]));
                ((Refrigerator) appliance).setFreezer_Capacity(Integer.parseInt(words[3].split(",")[0]));
                ((Refrigerator) appliance).setOverall_Capacity(Double.parseDouble(words[4].split(",")[0]));
                ((Refrigerator) appliance).setHeight(Double.parseDouble(words[5].split(",")[0]));
                ((Refrigerator) appliance).setWidth(Double.parseDouble(words[6].split(";")[0]));
                return appliance;
            case "VacuumCleaner":
                appliance = new VacuumCleaner("VacuumCleaner");
                st2 = st.split(" : ")[1]; // Oven
                words = st2.split("=");
                ((VacuumCleaner) appliance).setPower_Consumption(Integer.parseInt(words[1].split(",")[0]));
                ((VacuumCleaner) appliance).setFilter_Type(words[2].split(",")[0]);
                ((VacuumCleaner) appliance).setBag_Type(words[3].split(",")[0]);
                ((VacuumCleaner) appliance).setWand_Type(words[4].split(",")[0]);
                ((VacuumCleaner) appliance).setMotor_Speed_Regulation(Integer.parseInt(words[5].split(",")[0]));
                ((VacuumCleaner) appliance).setCleaning_Width(Integer.parseInt(words[6].split(";")[0]));
                return appliance;
            case "TabletPC":
                appliance = new TabletPC("TabletPC");
                st2 = st.split(" : ")[1];
                words = st2.split("=");
                ((TabletPC) appliance).setBattery_Capacity(Integer.parseInt(words[1].split(",")[0]));
                ((TabletPC) appliance).setDisplay_Inches(Integer.parseInt(words[2].split(",")[0]));
                ((TabletPC) appliance).setMemory_Rom(Integer.parseInt(words[3].split(",")[0]));
                ((TabletPC) appliance).setFlash_Memory_Capacity(Integer.parseInt(words[4].split(",")[0]));
                ((TabletPC) appliance).setColor(words[5].split(",")[0]);
                return appliance;
            case "Speakers":
                appliance = new Speakers("Speakers");
                st2 = st.split(" : ")[1];
                words = st2.split("=");
                ((Speakers) appliance).setPower_Consumption(Integer.parseInt(words[1].split(",")[0]));
                ((Speakers) appliance).setNumber_Of_Speakers(Integer.parseInt(words[2].split(",")[0]));
                ((Speakers) appliance).setFrequency_Range(words[3].split(",")[0]);
                ((Speakers) appliance).setCord_Length(Integer.parseInt(words[4].split(",")[0]));
                return appliance;
            default:
                break;
        }
        return appliance;
    }
}