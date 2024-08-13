package org.spring_core.with_annotation.with_component;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.maven.spring_core_withAnnotation.with_component")
public class Config {
}
