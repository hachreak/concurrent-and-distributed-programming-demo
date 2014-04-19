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

package es.unex.pcd.print_letters;

public class Main {

	public static void main(String[] args) {
		char letra = 'a';
		Printer p = new Printer();
		int n = 3;
		Writer escritores[] = new Writer[n];
		for(int i=0; i<n; i++){
			System.out.println("escritor["+i+"]: "+letra);
			escritores[i] = new Writer(letra, p);
			letra = (char) ((char) letra + 1);
		}
		for(int i=n-1; i>=0; i--){
			System.out.println("[run] "+(char)('a'+i)+" numero "+i);
			Thread t = new Thread(escritores[i]);
			t.start();			
		}
	}

}
