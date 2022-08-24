package by.tc.task01.dao.impl;

import by.tc.task01.dao.ApplianceDAO;
import by.tc.task01.entity.Appliance;
import by.tc.task01.entity.criteria.Criteria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ApplianceDAOImpl implements ApplianceDAO {

	@Override
	public List<Appliance> find(Criteria criteria) throws IOException, IllegalAccessException {

		File file = new File("src\\main\\resources\\appliances_db.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		List<Appliance> applianceList = new ArrayList<>();

		while ((st = br.readLine()) != null) {
			String ap = st.split(" :")[0];
				Appliance appliance = new Appliance(ap).createAppliance(st);
				Class currentClassObject = appliance.getClass();
				Field[] fields = currentClassObject.getDeclaredFields();
				int size = criteria.getCriteria().size();
				int counter = 0;
				for (Field field : fields) {
					for (Map.Entry<String, Object> entry : criteria.getCriteria().entrySet()) {
						if (entry.getKey().equals(field.getName().toUpperCase(Locale.ROOT))) {
							if (entry.getValue().equals(field.get(appliance))) {
								counter++;
								if (size == counter) {
									applianceList.add(appliance);
								}
							}
						}
					}
			}
		}
		return applianceList;
	}
}
