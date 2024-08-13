package org.spring_core.with_xml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("This is with BeanFactory:\n");
		// BeanFactory will be useful for smaller applications
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("SpringCore.xml");
		Vehicle vehicle = (Vehicle) beanFactory.getBean("Vehicle1");
		vehicle.drive();
		vehicle = (Vehicle) beanFactory.getBean("VehiclewithConstructor");
		vehicle.drive();
		vehicle = (Vehicle) beanFactory.getBean("VehicleWithSetters");
		vehicle.drive();
		
		beanFactory = new ClassPathXmlApplicationContext("SpringCoreAnnotationBasedConfiguration.xml");
		vehicle = (Vehicle) beanFactory.getBean("car");
		vehicle.drive();
		
		System.out.println("\n\nThis is with ApplicationContext:\n");
		// ApplicationContext will be useful for enterprise level applications
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("SpringCore.xml");
		vehicle = (Vehicle) applicationContext.getBean("Vehicle1");
		vehicle.drive();
		vehicle = (Vehicle) applicationContext.getBean("VehiclewithConstructor");
		vehicle.drive();
		vehicle = (Vehicle) applicationContext.getBean("VehicleWithSetters");
		vehicle.drive();
		
		applicationContext = new ClassPathXmlApplicationContext("SpringCoreAnnotationBasedConfiguration.xml");
		vehicle = (Vehicle) applicationContext.getBean("bike");
		vehicle.drive();
		
		vehicle = (Vehicle) beanFactory.getBean("car");
		vehicle.drive();
		
		
	}
}
