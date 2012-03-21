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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Class that actually writes the data to a file or other output destination.
 * Is created with a given DataSpec instance that determines what and how
 * everything is output.
 * 
 * @author jasonwjones
 *
 */
public class DataWriter {

	private DataSpec dataspec;
	
	public DataWriter(DataSpec dataspec) {
		this.dataspec = dataspec;
	}
	
	public void writeTo(String filename) throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(filename));
		writeTo(output);
	}
	
	public void writeTo(OutputStream outputStream) throws IOException {
		writeTo(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}
	
	public void writeTo(BufferedWriter writer) throws IOException {
		long rows = 0;
		try {
			OlapMemberPlan mp = dataspec.createOlapMemberPlan();
			DataRowCreator drc = dataspec.createDataRowCreator();
		
			for (OlapMember olapMember : mp) {
				if (dataspec.shouldWriteFact()) {
					String row = drc.createRow(olapMember, dataspec.createFact());
					writer.write(row);
					writer.newLine();
					rows++;
				}
			}
		} finally {
			writer.close();
			System.err.println("Wrote " + rows + " rows.");
		}
	}
}
