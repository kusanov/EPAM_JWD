package by.tc.task01.service.validation;

import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.entity.criteria.SearchCriteria;

import java.io.IOException;

public class Validator {

	public static boolean criteriaValidator(Criteria criteria) throws IOException, IllegalAccessException {

		int counter = 0;
		int numberOfCriteria = criteria.getCriteria().size();

		for (String s : criteria.getCriteria().keySet()) {
			for (Class<?> criteriaClass : SearchCriteria.class.getClasses()) {
				for (Object enumConstant : criteriaClass.getEnumConstants()) {
					if (s.equals(enumConstant)) {
						counter++;
						break;
					}
				}
			}
		}
		return counter == numberOfCriteria;
	}
}