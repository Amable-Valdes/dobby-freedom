package uo.sdi.infrastructure;

import uo.sdi.business.ServicesFactory;
import uo.sdi.business.impl.ServicesFactoryImpl;

public class Factories {

	public static ServicesFactory services = new ServicesFactoryImpl();
	//public static FinderFactory persistence = new SimplePersistenceFactory();
}
