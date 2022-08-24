package by.tc.task01.main;

import static by.tc.task01.entity.criteria.SearchCriteria.*;

import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.service.ApplianceService;
import by.tc.task01.service.ServiceFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class Main<appliances> {

	private static Field[] fields;

	public static void main(String[] args) throws IOException, IllegalAccessException {

		List<Appliance> appliance;

		ServiceFactory factory = ServiceFactory.getInstance();
		ApplianceService service = factory.getApplianceService();

		//////////////////////////////////////////////////////////////////

		Criteria criteriaOven = new Criteria(Oven.class.getSimpleName());//"Oven"
		criteriaOven.add(OvenCriteria.CAPACITY.toString(), 32);
//		for (Map.Entry<String,Object> entry : criteriaOven.getCriteria().entrySet()) {
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
		appliance = service.find(criteriaOven);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////
		Criteria criteriaOven2 = new Criteria(Oven.class.getSimpleName());
		criteriaOven2.add(OvenCriteria.HEIGHT.toString(), 45.5);
		criteriaOven2.add(OvenCriteria.DEPTH.toString(), 60.0);

		appliance = service.find(criteriaOven2);

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaTabletPC = new Criteria(TabletPC.class.getSimpleName());
		criteriaTabletPC.add(TabletPC.DISPLAY_INCHES.toString(), 14);
		criteriaTabletPC.add(TabletPC.MEMORY_ROM.toString(), 8000);
		criteriaTabletPC.add(TabletPC.COLOR.toString(), "blue");

		appliance = service.find(criteriaTabletPC);// find(Object...obj)

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaLaptop = new Criteria(Laptop.class.getSimpleName());
		criteriaLaptop.add(Laptop.DISPLAY_INCHS.toString(), 18.0);

		appliance = service.find(criteriaLaptop);// find(Object...obj)

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaRefrigerator = new Criteria(Refrigerator.class.getSimpleName());
		criteriaRefrigerator.add(Refrigerator.FREEZER_CAPACITY.toString(), 20);

		appliance = service.find(criteriaRefrigerator);// find(Object...obj)

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaVacuumCleaner = new Criteria(VacuumCleaner.class.getSimpleName());
		criteriaVacuumCleaner.add(VacuumCleaner.BAG_TYPE.toString(), "A2");

		appliance = service.find(criteriaVacuumCleaner);// find(Object...obj)

		PrintApplianceInfo.print(appliance);

		//////////////////////////////////////////////////////////////////

		Criteria criteriaSpeakers = new Criteria(Speakers.class.getSimpleName());
		criteriaSpeakers.add(Speakers.CORD_LENGTH.toString(), 4);

		appliance = service.find(criteriaSpeakers);// find(Object...obj)

		PrintApplianceInfo.print(appliance);
	}

}
