package dev.fringe.qa.config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.fringe.qa.model.Browser;
import dev.fringe.qa.model.Platform;
import dev.fringe.qa.service.CommonService;

@Configuration
public class AppConfig {

	String uri = "https://github.com/mozilla/geckodriver/releases/download/%s/%s";
	String browser = "geckodriver";
	
	@Autowired private CommonService c;

	@Bean
	public WebDriver getWebDriver(){
		WebDriver d = this.setDriverAndSetProperty(Platform.win64, Browser.FIREFOX, "v0.19.1");
	    d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return d;
	}
	
	/**
	 * geckodriver download and set property driver location 
	 * @param platform
	 * @param b
	 * @param version
	 * @return
	 */
	public WebDriver setDriverAndSetProperty(Platform platform, Browser b, String version){
		switch (b) {
		case FIREFOX:
			String remoteFilename = String.format("%s-%s-%s.zip",this.browser,version, platform);
			String runtimeFile = this.browser + ".exe";
			File f = new File(runtimeFile);
			if(f.exists() == false){
				URL url = null;
				try {
					url = new URL(String.format(uri,version,remoteFilename));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				boolean exists = c.getFile(url,remoteFilename);
				if (exists) {
					c.unzip(remoteFilename, runtimeFile);
				}
			}
			Properties properties = new Properties();
			properties.setProperty("webdriver.gecko.driver", System.getProperty("user.dir"));
			return new FirefoxDriver();
		}
		return null;
	}
}
