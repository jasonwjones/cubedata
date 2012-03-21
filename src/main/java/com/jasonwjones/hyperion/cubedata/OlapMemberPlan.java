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

import java.util.Iterator;

/**
 * Somewhat oddly named clas that simply lets us iterate over a list of members
 * that are a result of the DataSpec configuration and a few other options.
 * 
 * @author jasonwjones
 *
 */
public class OlapMemberPlan implements Iterable<OlapMember> {

	private PermutationCounter pc;
	private DataSpec dataSpec;
	
	public OlapMemberPlan(DataSpec dataSpec) {
		
		if (dataSpec == null || dataSpec.getDimensionCount() == 0) {
			throw new IllegalArgumentException("Data spec must not be null and must have at least one dimension.");
		}
		
		this.dataSpec = dataSpec;
		int numDimensions = dataSpec.getDimensionCount();
		int[] dimSizes = new int[numDimensions];
		for (int dimIndex = 0; dimIndex < numDimensions; dimIndex++) {
			dimSizes[dimIndex] = dataSpec.getDimension(dimIndex).getAllMembers().size() - 1;
		}
		pc = new PermutationCounter(dimSizes);
	}
	
	public boolean hasNext() {
		return pc.hasNext();
	}
	
	public OlapMember next() {
		return createOlapMemberFromPermutation(pc.next());
	}
	
	public Iterator<OlapMember> iterator() {
		return new Iterator<OlapMember>() {
			public boolean hasNext() {
				return OlapMemberPlan.this.hasNext();
			}

			public OlapMember next() {
				return OlapMemberPlan.this.next();
			}

			public void remove() {
		        throw new UnsupportedOperationException();				
			}
		};

	}

	private OlapMember createOlapMemberFromPermutation(Permutation permutation) {
		OlapMember olapMember = new OlapMember();
		for (int permIndex = 0; permIndex < permutation.size(); permIndex++) {
			Dimension dimension = dataSpec.getDimension(permIndex);
			olapMember.setDimensionMember(dimension, dimension.getMemberAtIndex(permutation.getAt(permIndex)));
		}
		return olapMember;		
	}
	
}
