package dev.fringe.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SuppressWarnings("resource")
public class App implements InitializingBean {
	
	@Autowired WebDriver driver;
	
	public static void main(String...args) {
		new AnnotationConfigApplicationContext(App.class);
	}

	public void afterPropertiesSet() throws Exception {
	    String baseUrl = "https://10.0.42.26:9043";
	    driver.get(baseUrl + "/ibm/console/logon.jsp");
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("kumjs");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("u9bU8pil");
	    driver.findElement(By.id("other")).click();
	    driver.switchTo().frame("navigation");
	    driver.findElement(By.linkText("Servers")).click();
	    driver.findElement(By.linkText("Server Types")).click();
	    driver.findElement(By.linkText("WebSphere application servers")).click();
        for (String handle1 : driver.getWindowHandles()) {
        	System.out.println(handle1);
        	driver.switchTo().window("4294967297");
        }
	    driver.findElement(By.name("button.restart")).click();
	    driver.findElement(By.linkText("Logout")).click();
	    driver.quit();
	}
}