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

/**
 * Builds the actual row of data based on a generated OlapMember
 * 
 * @author jasonwjones
 *
 */
public class DataRowCreator {

	private String delimiter = ",";
	private ArrayList<DataRowSpec> columnSpecs = new ArrayList<DataRowSpec>();
	
	public void addDataRowSpec(DataRowSpec dataRowSpec) {
		columnSpecs.add(dataRowSpec);
	}
	
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
	
	public String getDelimiter() {
		return delimiter;
	}
	
	public String createRow(OlapMember olapMember, String factValue) {
		StringBuilder sb = new StringBuilder();
		for (DataRowSpec colSpec : columnSpecs) {
			sb.append(colSpec.format(olapMember.getMemberForDimension(colSpec.getDimension())));
			sb.append(delimiter);
		}
		sb.append(factValue);
		return sb.toString();
	}
	
}
