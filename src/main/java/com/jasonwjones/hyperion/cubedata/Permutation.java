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
 * Represents a single permutation generated from a PermutationCounter. Knows
 * nothing about members and whatnot -- the permutations are based on an array
 * of integers that specifies the count of each element.
 * 
 * @author jasonwjones
 *
 */
public class Permutation {

	private int[] permutation;
	
	public Permutation(int[] permutation) {
		this.permutation = permutation.clone();
	}
	
	public int getAt(int index) {
		return permutation[index];
	}
	
	public int size() {
		return permutation.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < permutation.length; i++) {
			sb.append(i);
			sb.append(":");
			sb.append(permutation[i]);
			sb.append(" ");
		}
		return sb.toString();
	}
	
}
