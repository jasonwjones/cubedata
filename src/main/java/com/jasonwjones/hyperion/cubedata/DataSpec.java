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

import java.util.ArrayList;
import java.util.Properties;

/**
 * Main coordinator class that takes a set of properties and handles
 * generating a text file or otherwise outputting the generated data. 
 * 
 * Provided to a DataWriter class in order to generate the data.
 * 
 * @author jasonwjones
 *
 */
public class DataSpec {

	public static final String OUTPUT_DELIMITER = "delimiter";
	public static final String OUTPUT_FILE		= "outputfile";
	public static final String MAX_FACT 		= "maxfact";
	public static final String MIN_FACT 		= "minfact";
	public static final String LOAD_FACTOR 		= "loadfactor";
	
	private ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
	
	private String delimiter = ",";
	private int minFact = 0;
	private int maxFact = 1000;
	private boolean generateCurrency = true;
	private double loadFactor = 1.00f;
	
	/** 
	 * Attempts to build a data spec from a set of properties
	 * @param options
	 */
	public DataSpec(Properties properties) {
		
		String dimensionList = properties.getProperty("dimensions");
		
		if (dimensionList == null) {
			throw new IllegalArgumentException("Must have a 'dimensions' key");
		}
		
		String[] dimensionNames = dimensionList.split(",");		
		for (String currentDimension : dimensionNames) {
			System.err.println("Reading members for dimension " + currentDimension);
			
			Dimension newDimension = new Dimension(currentDimension);
			
			String currDimensionProp = properties.getProperty("members." + currentDimension);
			if (currDimensionProp == null) {
				throw new IllegalArgumentException("Dimensions key specifies a dimension of " + currentDimension + " but no key with that name was found");
			}
			
			String[] members = currDimensionProp.split(",");
			System.err.println("Setting " + members.length + " members for dimension");
			for (String currentDimensionMember : members) {
				newDimension.addMember(currentDimensionMember);
			}
			
			dimensions.add(newDimension);	
		}
		
		delimiter 	= keyOrDefault(properties, OUTPUT_DELIMITER, delimiter);
		loadFactor 	= keyOrDefault(properties, LOAD_FACTOR, loadFactor);
		
	}
	
	private String keyOrDefault(Properties properties, String keyName, String defaultValue) {
		if (properties.containsKey(keyName)) {
			return properties.getProperty(keyName);
		} else {
			return defaultValue;
		}
	}
	
	private double keyOrDefault(Properties properties, String keyName, double defaultValue) {
		if (properties.containsKey(keyName)) {
			return Double.valueOf(properties.getProperty(keyName));
		} else {
			return defaultValue;
		}
	}
	
	public void addDimension(Dimension dimension) {
		dimensions.add(dimension);
	}
	
	public int getDimensionCount() {
		return dimensions.size();
	}
	
	public Dimension getDimension(int index) {
		return dimensions.get(index);
	}
	
	public OlapMemberPlan createOlapMemberPlan() {
		return new OlapMemberPlan(this);
	}
	
	public DataRowCreator createDataRowCreator() {
		DataRowCreator drc = new DataRowCreator();
		drc.setDelimiter(delimiter);
		
		for (Dimension dimension : dimensions) {
			DataRowSpec drs = new DataRowSpec(dimension);
			drc.addDataRowSpec(drs);
		}
		return drc;
	}
	
	public String createFact() {
		if (generateCurrency) {
			return createFactTwoDecimal(minFact, maxFact);
		} else {
			return String.valueOf(createRandom(minFact, maxFact));
		}
	}
	
	public boolean shouldWriteFact() {
		return Math.random() <= loadFactor;
	}
	
	private int createRandom(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	private String createFactTwoDecimal(int min, int max) {
		return String.format("%d.%02d", createRandom(min, max), createRandom(0, 100));
	}
	
}
