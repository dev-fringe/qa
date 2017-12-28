package dev.fringe.qa.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

@Service
public class CommonService {


	public boolean unzip(String file, String runtime) {
		boolean exists;
		exists = ZipUtil.containsEntry(new File(file), runtime);
		if(exists){
			return ZipUtil.unpackEntry(new File(file), runtime, new File(runtime));
		}
		return false;
	}
	
	public boolean getFile(URL url, String file) {
		try {
			FileUtils.copyURLToFile(url, new File(file));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
