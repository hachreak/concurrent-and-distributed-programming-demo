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

package es.unex.pcd.leorossi.SieveOfEratostenes;

import java.util.concurrent.Semaphore;

public class Filter extends Thread {

	private int max = 2;

	private int number_init;
	private int number;
	private Filter nextFilter = null;

	private boolean finished = false;
	private boolean founded = false;
		
	private Semaphore sync1 = new Semaphore(0);
	
	public Filter(int number_init, int max) {
		super();
		this.number_init = number_init;
		this.max = max;
	}

	@Override
	synchronized public void run() {
		while (!finished) {
			sync1.release();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (number == number_init && !founded) {
				System.out.println("[Filter " + number_init + "] prime number!");
				founded = true;
			}else if (number % number_init != 0) {
				if (number < max) {
					Filter next = getNextFilter(number);
					next.sendNewNumber(number);
				} else {
					System.out.println("[Consumidor] numero " + number);
				}
			}
		}
		if(nextFilter != null)
			nextFilter.terminate();
	}

	public void sendNewNumber(int number) {
		try {
			sync1.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		synchronized (this) {
			this.number = number;
			if(number == max){
				finished = true;
			}

			notify();
		}
	}
	
	synchronized public void terminate(){
		finished = true;
		notify();
	}

	private Filter getNextFilter(int num) {
		if (nextFilter == null) {
			nextFilter = new Filter(num, max);
			nextFilter.start();
		}
		return nextFilter;
	}

}
