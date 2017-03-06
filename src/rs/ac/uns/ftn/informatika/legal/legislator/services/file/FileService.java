package rs.ac.uns.ftn.informatika.legal.legislator.services.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.legislator.services.Service;

public class FileService extends Service {
	
	private static Logger log = Logger.getLogger(FileService.class);
	
	public FileService() {
		serviceName = "rs.ac.uns.ftn.informatika.metalex.services.file.FileService";
		
		log.info("FileService Initialized.");
	}

	public String getFileAsString(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			while (reader.ready()) {
				sb.append(reader.readLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		return sb.toString();
	}
}