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

public class Main {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<10; i++){
			int num = (int) (Math.random() * 100);
			System.out.print(num+" ");
			list.add(num);
		}
		System.out.print("\n");
		
		NumberSet conjunto = new NumberSet(list);
		
		NumExtractor[] t = new NumExtractor[5];
		
		for(int i=0; i<t.length; i++){
			t[i] = new NumExtractor(conjunto);
		}
		
		for(int i=0; i<t.length; i++){
			t[i].start();
		}
		
		for(int i=0; i<t.length; i++){
			try {
				t[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
/**
sync poner()
  elem++;
  notifyall();
  
sync coger()
  while(elem < 2) wait();
  
  elem-=2;
  
ho anche una variabile "pendientes" per sapere quanti hanno preso 2 numeri e non hanno
ancora riposto il massimo.
la uso per sapere quando ho finito (elem == 1 && pendientes == 0)
*/