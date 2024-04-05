package org.spring_core.with_annotation.with_component;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(Config.class);
		Vehicle vehicle = (Car) beanFactory.getBean(Car.class);
		System.out.println(vehicle);
		vehicle.driving();
		Person person = (Person) beanFactory.getBean(Person.class);
		System.out.println(person);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		vehicle = (Bike) applicationContext.getBean("bike");
		System.out.println(vehicle);
		vehicle.driving();
	}
}
