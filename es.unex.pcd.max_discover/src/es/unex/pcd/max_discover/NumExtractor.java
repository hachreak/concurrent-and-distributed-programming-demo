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

import java.util.List;

public class NumExtractor extends Thread {

	private NumberSet numSet;

	public NumExtractor(NumberSet conjunto){
		this.numSet = conjunto;
	}
	
	@Override
	public void run() {
		while(true){
			List<Integer> ret = numSet.extract();
			
			if(ret == null)
				return;
			
			int num = 0;
			if(ret.get(0) > ret.get(1)){
				num = ret.get(0);
			}else{
				num = ret.get(1);
			}
				
			numSet.retornar(num);			
		}
	}

}
