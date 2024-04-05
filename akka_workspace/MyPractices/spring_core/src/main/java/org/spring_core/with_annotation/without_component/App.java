package org.spring_core.with_annotation.without_component;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Set set = new TreeSet();
    	BeanFactory beanFactory = new AnnotationConfigApplicationContext(Config.class);
    	Car car = (Car) beanFactory.getBean(Car.class);
        System.out.println(car);
    	
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        car = (Car) applicationContext.getBean(Car.class);
        System.out.println(car);
    }
}
