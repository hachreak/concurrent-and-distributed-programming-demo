/**
 * Copyright (C) 2014 Leonardo Rossi <leonardo.rossi@studenti.unipr.it>
 *
 * This source code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package es.unex.pcd.max_discover;

import java.util.ArrayList;
import java.util.List;

public class NumberSet {
	private List<Integer> numSet = null;
	private int pendientes = 0;
	private boolean finished = false;
	
	public NumberSet(List<Integer> list) {
		numSet = list;
	}

	public synchronized List<Integer> extract(){
		while(numSet.size() < 2)
			try {
				if(finished)
					return null;
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		List<Integer> ret = new ArrayList<Integer>();
		
		int i = (int) (Math.random() * numSet.size());

		ret.add(numSet.get(i));
		numSet.remove(i);

		i = (int) (Math.random() * numSet.size());

		ret.add(numSet.get(i));
		numSet.remove(i);

		pendientes += 2;
		
		return ret;
	}
	
	public synchronized void retornar(int num){
		numSet.add(num);
		
		pendientes -= 2;
		
		if(numSet.size() == 1 && pendientes == 0){
			finished = true;
			System.out.println("MAX "+numSet.get(0));
		}

		notifyAll();	
	}
}
