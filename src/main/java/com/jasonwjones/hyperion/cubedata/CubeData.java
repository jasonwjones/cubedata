/*  Copyright 2012 Jason Jones
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.jasonwjones.hyperion.cubedata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Main class. Looks for data.properties or reads a file that is specified as a
 * command-line paramter.
 * 
 * @author jasonwjones
 *
 */
public class CubeData {

	public static void main(String[] args) {
		
		String propertyFile = args.length > 0 ? args[0] : "data.properties";
		
		Properties properties = new Properties();		
		try {
			FileInputStream fis = new FileInputStream(propertyFile);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find properties file " + propertyFile);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Error reading configuration file");
			System.exit(1);
		}
		
		String filename = properties.getProperty(DataSpec.OUTPUT_FILE);
		
		try {
			DataSpec dataspec = new DataSpec(properties);
			DataWriter dw =  new DataWriter(dataspec);
			if (filename == null) {
				System.err.println("No output file specified so writing to standard output.");
				dw.writeTo(System.out);
			} else {
				System.err.println("Writing to " + filename);
				dw.writeTo(filename);
				System.out.println("Wrote " + filename);
			}
			
		} catch (Exception e) {
			System.out.println("Error generating data: " + e.getMessage());
		}					
	}	
}
