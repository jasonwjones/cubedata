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
 * Generates permutations based on a specified array indicating how many
 * options there are for each.
 * 
 * @author jasonwjones
 *
 */
public class PermutationCounter implements Iterable<Permutation> {

	private int[] currentCounts;
	private int[] maxCounts;
	
	private int size;
	private boolean done = false;
	
	/**
	 * Creates a new PermutationCounter. This object is given an array of 
	 * integers and provides the ability to iterate over all the different
	 * combinations.
	 * @param maxCounts
	 */
	public PermutationCounter(int[] maxCounts) {
		this.maxCounts = maxCounts.clone();
		currentCounts = new int[maxCounts.length];
		size = maxCounts.length - 1;
	}
	
	public int[] getCounter() {
		return currentCounts;
	}
	
	public void increment() {
		for (int column = size; column >= 0; column--) {
			if (++currentCounts[column] > maxCounts[column]) {
				if (column == 0) {
					done = true;
				}
				currentCounts[column] = 0;
			} else {
				break;
			}
		}
	}
	
	public boolean hasNext() {
		return !done;
	}
	
	public Permutation next() {
		Permutation nextPermutation = new Permutation(currentCounts);
		increment();
		return nextPermutation;
	}

	public Iterator<Permutation> iterator() {
		return new Iterator<Permutation>() {
			public boolean hasNext() {
				return PermutationCounter.this.hasNext();
			}

			public Permutation next() {
				return PermutationCounter.this.next();
			}

			public void remove() {
		        throw new UnsupportedOperationException();				
			}
		};
	}
	
}
