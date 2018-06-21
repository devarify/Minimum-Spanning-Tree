package MinimumSpanningTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Arify
 * 
 * TUBES DAA IF-EXT-40-01
 * Nama Anggota : Arif Yulianto | 1301668560
 *                Amira Nur Khalipah | 1301168559
 *                Ardhi Akmaludin Jadhira | 1301168552
 * Kelas: IF-EXT-40-02
 * 
 * Topik : 3. Penyelesaian Minimum Spanning Tree menggunakan Algoritma Prim dan Kruskal
 */

public class mst {

	static ArrayList<Integer> weights = new ArrayList<Integer>();
	// input file harus berbentuk matrix
	/* Misalkan graph dari matrix seperti dibawah
            2    3
        (0)---(1)---(2)         
         |    / \   |
        6|  8/   \5 |7
         |  /     \ |
        (3)---------(4)
               9     */
         /*
          Maka matrixnya seperti ini
            0 2 0 6 0
            2 0 3 8 5
            0 3 0 0 7
            6 8 0 0 9
            0 5 7 9 0
          */        
	int dimension;
	File file = null;
        
	public static void main(String args[]) {
		mst sys = new mst();       
                sys.load("input.txt");
		sys.prim();
		sys.kruskal();
	}
        //Fungsi meload file input.txt
        //input.txt diletakan di folder project. misal di /NetbeansProjects/Tubes_DAA_MST/
	public void load(String filepath) {
		file = new File(filepath);
		try {
			Scanner in = new Scanner(file);
			int counter = 0;
			while (in.hasNext()) {
				weights.add(in.nextInt());
				counter++;
				dimension = (int) Math.sqrt(counter);
			}
			in.close();
		} catch (FileNotFoundException f) {
			System.out.println("File input.txt tidak ditemukan.\n");
		}
	}
        //Fungsi problem Minimal Spanning Tree dengan algoritma Prim
	public void prim() {
		try {
			String fileDir = file.getParent(); //membaca inputan
			File file2 = new File(fileDir, "prim_output.txt"); //membuat file output
			PrintWriter writer = new PrintWriter(file2);
                        //membaca inputan
			for (int i = 0; i < weights.size(); i++) {
				writer.print(weights.get(i) + " ");
				System.out.printf(weights.get(i) + "\t");
                                if ((i + 1) % dimension == 0) {
					writer.println("");
                                        System.out.println("");
				}
			}
			writer.println("");
                        System.out.println("");

			int[][] matrix = new int[dimension][dimension];
			int[] visited = new int[dimension];
			int min = 21; // inisiasi berat maksimal
			int u = 0, v = 0; // iterasi untuk edge
			int total = 0;
			int count = 0; // iterasi array list dari berat (weight)
                        
                        //Mencari nilai weight tekecil
			for (int i = 0; i < dimension; i++) {
				visited[i] = 0;
				for (int j = 0; j < dimension; j++) {
					matrix[i][j] = weights.get(count);
					count++;
					if (matrix[i][j] == 0) {
						matrix[i][j] = 21;
					}
				}
			}
			visited[0] = 1;
                        
                        //Mencari nilai weight terkecil
			for (int counter = 0; counter < dimension - 1; counter++) {
				min = 21;
				for (int i = 0; i < dimension; i++) {
					if (visited[i] == 1) {
						for (int j = 0; j < dimension; j++) {
							if (visited[j] == 0) {
								if (min > matrix[i][j]) {
									min = matrix[i][j];
									u = i;
									v = j;
								}
							}
						}
					}
				}
				visited[v] = 1;
				total += min;
				matrix[u][v] = 21;
				writer.println("Edge " + u + " -> " + v + " : " + min);//menuliskan output ke file prim_output.txt
                                System.out.println("Edge " + u + " -> " + v + " : " + min);//menuliskan hasil ke line output
			}
			writer.println("Total berat problem Minimum Spanning Tree Dengan menggunakan algoritma Prim: "
					+ total + ".");
                        System.out.println("Total berat problem Minimum Spanning Tree Dengan menggunakan algoritma Prim: "
					+ total + ".");
			writer.println();
                        System.out.println();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
        //Fungsi problem Minimal Spanning Tree dengan menggunakan algoritma Kruskal
	public void kruskal() {
		try {
			String fileDir = file.getParent();
			File file2 = new File(fileDir, "kruskal_output.txt");
			PrintWriter writer = new PrintWriter(file2);
			int[][] matrix = new int[dimension][dimension];
			int[] parent = new int[dimension];
			int min;
			int u = 0;
			int v = 0;
			int edges = 1;
			int total = 0;
			int count = 0;
			
			for (int i = 0; i < weights.size(); i++) {
				writer.print(weights.get(i) + " ");
                                System.out.printf(weights.get(i) + "\t");
				if ((i + 1) % dimension == 0) {
					writer.println("");
                                        System.out.println("");
				}
			}
			writer.println("");
                        System.out.println("");

			for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					matrix[i][j] = weights.get(count);
					count++;
				}
			}
			for (int i = 0; i < dimension; i++) {
				parent[i] = 0;
				for (int j = 0; j < dimension; j++) {
					if (matrix[i][j] == 0) {
						matrix[i][j] = 21;
					}
				}
			}
			while (edges < dimension) {
				min = 21;
				for (int i = 0; i < dimension; i++) {
					for (int j = 0; j < dimension; j++) {
						if (matrix[i][j] < min) {
							min = matrix[i][j];
							u = i;
							v = j;
						}
					}
				}
				if (v != u) {
					edges++;
					writer.println("Edge " + u + " -> " + v + " : " + min);
					System.out.println("Edge " + u + " -> " + v + " : " + min);
                                        total += min;
					parent[v] = u;
				}
				matrix[u][v] = 21;
				matrix[v][u] = 21;

			}
			writer.println("Total berat problem Minimum Spanning Tree Dengan menggunakan algoritma Kruskal: "
				+ total + ".");
                        System.out.println("Total berat problem Minimum Spanning Tree Dengan menggunakan algoritma Kruskal: "
				+ total + ".");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
