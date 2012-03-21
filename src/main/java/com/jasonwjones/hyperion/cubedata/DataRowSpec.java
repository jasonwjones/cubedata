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

/**
 * Slightly misnamed class. Used to build a piece of the row based on the 
 * dimension of the member.
 * 
 * @author jasonwjones
 *
 */
public class DataRowSpec {

	private Dimension dimension;
	
	public DataRowSpec(Dimension dimension) {
		this.dimension = dimension;
	}

	public Dimension getDimension() {
		return dimension;
	}
	
	public String format(String member) {
		return member;
	}
	
	@Override
	public boolean equals(Object aThat) {
		if (this == aThat) return true;
		if (!(aThat instanceof DataRowSpec)) return false;
		DataRowSpec that = (DataRowSpec) aThat;
		return that.dimension.equals(dimension);
	}
	
}
