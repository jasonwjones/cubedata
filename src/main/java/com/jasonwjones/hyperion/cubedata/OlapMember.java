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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A particular OLAP member is a set of Dimension to String mappings. This is
 * analogous to a unique member intersection in a cube.
 *  
 * @author jasonwjones
 *
 */
public class OlapMember {

	protected Map<Dimension, String> members = new HashMap<Dimension, String>();
	
	public void setDimensionMember(Dimension dimension, String memberName) {
		members.put(dimension, memberName);
	}
	
	public String getMemberForDimension(Dimension dimension) {
		return members.get(dimension);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("OlapMember: ");
		for (Entry<Dimension, String> entry : members.entrySet()) {
			sb.append(entry.getKey().getDimensionName());
			sb.append(": ");
			sb.append(entry.getValue());
			sb.append(", ");
		}
		return sb.toString();
	}
	
}
