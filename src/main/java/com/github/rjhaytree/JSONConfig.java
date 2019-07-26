package com.github.rjhaytree;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONConfig {

	// google json library
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	// create new config dir in the same location as the JVM, with name 'JsonConfig'
	private static File dir = new File(System.getProperty("user.dir"), "JsonConfig");
	private static Config config;
	
	public static void main(String[] args) {
		try {
			// create new json file called 'config.json'
			File configFile = new File(dir, "config.json");
			System.out.println("Trying to load config.json...");
			
			// check if file exists. If it doesn't exist, create new config.json file
			if (!configFile.exists()) {
				// using gson, convert the Config class to a json string, and then write this to the 'config.json' file
				FileUtils.writeStringToFile(configFile, gson.toJson(new Config()), StandardCharsets.UTF_8);
				
				// close application to allow the user to configure their application
				System.out.println("A config could not be found. A new config file has been created at: " + dir.getPath());
				System.exit(0);
			}
			
			// config file exists, so create a new config object using the information in the json file
			config = gson.fromJson(new FileReader(configFile), Config.class);
			System.out.println("Config has been loaded...");
			
			// print out host credential for testing
			System.out.println(config.getHost());
		}
		catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}

}
