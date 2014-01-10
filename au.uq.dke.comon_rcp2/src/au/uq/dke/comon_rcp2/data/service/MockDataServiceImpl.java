package au.uq.dke.comon_rcp2.data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockDataServiceImpl implements IDataService {

	private static MockDataServiceImpl instance = null;

	private Map<Class, Collection> dataMap = new HashMap<Class, Collection>();

	private MockDataServiceImpl() {

	}

	public static MockDataServiceImpl getInstance() {
		if (instance == null) {
			instance = new MockDataServiceImpl();
		}
		return instance;
	}

	@Override
	public Collection getDataSet(Class beanType) {
		if (dataMap.containsKey(beanType)) {
			return dataMap.get(beanType);
		} else {

			Collection beanList = new ArrayList();
			for (int i = 0; i < 3; i++) {
				Object bean = null;
				try {
					bean = beanType.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				beanList.add(bean);
			}
			return beanList;
		}

	}

}
