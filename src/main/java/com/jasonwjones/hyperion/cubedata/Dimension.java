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
import java.util.List;

/**
 * A dimension simply contains a list of members.
 * 
 * @author jasonwjones
 *
 */
public class Dimension {
	
	private String dimensionName;
	private ArrayList<String> members = new ArrayList<String>();
	
	public Dimension(String dimensionName) {
		this.dimensionName = dimensionName;
	}
	
	public String getDimensionName() {
		return dimensionName;
	}
	
	public void addMember(String memberId) {
		members.add(memberId);
	}
	
	public int size() {
		return members.size();
	}
	
	public String getMemberAtIndex(int index) {
		return members.get(index);
	}
	
	public List<String> getAllMembers() {
		return members;
	}
	
	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) return true;
		if (!(aThat instanceof Dimension)) return false;
		Dimension that = (Dimension) aThat;
		return that.dimensionName.equals(dimensionName);
	}
	
}
