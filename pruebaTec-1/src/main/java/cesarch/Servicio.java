package cesarch;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Servicio {

	public static Object search(char[][] matrix, String word) {
		int leg = word.length();
		String palabra = "";
		ArrayList<Object> solu = new ArrayList();
		int count = 0;
		for (int x = 0; x < matrix.length; x++) {
			count = 0;
			palabra = "";
			for (int y = 0; y < matrix[x].length; y++) {
				palabra += Character.toString(matrix[x][y]);
				count++;
				if (count == word.length()) {
					if (palabra.equalsIgnoreCase(word)) {
						solu.add(x);
						y = y - (word.length()-1);
						solu.add(y);
						solu.add(true);
						return solu;
					} else {
						count = 0;
						palabra = "";
						y = y - (word.length()-1);
					}
				}
			}					
		}
		int a = matrix[0].length;
		int b = matrix.length;
		for (int x = 0; x < a; x++) {
			count = 0;
			palabra = "";
			for (int y = 0; y < b; y++) {
				palabra += Character.toString(matrix[y][x]);
				count++;
				if (count == word.length()) {
					if (palabra.equalsIgnoreCase(word)) {
						solu.add(y);
						solu.add(x);
						solu.add(true);
						return solu;
					} else {
						count = 0;
						palabra = "";
						y = y- (word.length()-1);
					}
				}
			}					
		}
		solu.add(null);
		solu.add(null);
		solu.add(false);
		return solu;
	}
}
