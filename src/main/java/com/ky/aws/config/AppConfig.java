
package com.ky.aws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * AppConfig
 * 
 * @author kishora
 *
 */
@Configuration
@ComponentScan(basePackages =
{ "com.ky.aws" })
@PropertySource("classpath:application.properties")
public class AppConfig
{

}
