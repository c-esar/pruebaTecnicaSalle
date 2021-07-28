package cesarch;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Servicio {

	public parametros search(parametros param) {
		char[][] matrix = new char[param.getRows().intValue()][param.getRows().intValue() * 2];
		ArrayList<Character> sopaLetras = new ArrayList<>();
		for (int n = 0; n < param.getSearchword().length(); n++) {
			sopaLetras.add(param.getSearchword().charAt(n));
		}
		int count = 0;
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				matrix[x][y] = sopaLetras.get(count);
				count++;
			}
		}
		int leg = param.getWord().length();
		String palabra = "";
		count = 0;
		for (int x = 0; x < matrix.length; x++) {
			count = 0;
			palabra = "";
			for (int y = 0; y < matrix[x].length; y++) {
				palabra += Character.toString(matrix[x][y]);
				count++;
				if (count == param.getWord().length()) {
					if (palabra.equalsIgnoreCase(param.getWord())) {
						param.setStart_row(String.valueOf(x));
						y = y - (param.getWord().length()-1);
						param.setStart_col(String.valueOf(y));
						param.setContains("true");
						return param;
					} else {
						count = 0;
						palabra = "";
						y = y - (param.getWord().length()-1);
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
				if (count == param.getWord().length()) {
					if (palabra.equalsIgnoreCase(param.getWord())) {
						param.setStart_row(String.valueOf(y));
						param.setStart_row(String.valueOf(x));
						param.setContains("true");
						return param;
					} else {
						count = 0;
						palabra = "";
						y = y- (param.getWord().length()-1);
					}
				}
			}					
		}
		param.setContains("false");
		return param;
	}
	
	public parametros searchDiagonal(parametros param) {
		char[][] matrix = new char[param.getRows().intValue()][param.getRows().intValue() * 2];
		ArrayList<Character> sopaLetras = new ArrayList<>();
		for (int n = 0; n < param.getSearchword().length(); n++) {
			sopaLetras.add(param.getSearchword().charAt(n));
		}
		int count = 0;
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				matrix[x][y] = sopaLetras.get(count);
				count++;
			}
		}
		int leg = param.getWord().length();
		String palabra = "";
		count = 0;
		if(leg <= param.getSearchword().length()) {
			for (int x = 0; x < matrix.length; x++) {
				count = 0;
				palabra = "";
				for (int y = 0; y < matrix[0].length; y++) {
					if((x+count) < matrix.length) {
						palabra += Character.toString(matrix[x+count][y]);	
					}else {
						break;
					}					
					count++;
					if (count == param.getWord().length()) {
						if (palabra.equalsIgnoreCase(param.getWord())) {
							param.setStart_row(String.valueOf(x));
							y = y- (param.getWord().length()-1);
							param.setStart_col(String.valueOf(y));
							param.setContains("true");
							return param;
						} else {
							y = y-(count-1);
							palabra = "";
							count =0;
						}
					}
				}					
			}	
			
			for (int x = matrix.length-1; x > 0; x--) {
				count = 0;
				palabra = "";
				for (int y = matrix[0].length -1; y > 0; y--) {
					if((x-count) >= 0) {
						palabra += Character.toString(matrix[x-count][y]);	
					}else {
						break;
					}					
					count++;
					if (count == param.getWord().length()) {
						if (palabra.equalsIgnoreCase(param.getWord())) {
							param.setStart_row(String.valueOf(x));
							y = y+ (count-1);
							param.setStart_col(String.valueOf(y));
							param.setContains("true");
							return param;
						} else {
							y = y+(count-1);
							palabra = "";
							count =0;
						}
					}
				}					
			}	
			
			
			for (int x = 0; x < matrix.length; x++) {
				count = 0;
				palabra = "";
				for (int y = matrix[0].length -1; y > 0; y--) {
					if((x+count) < matrix.length) {
						palabra += Character.toString(matrix[x+count][y]);	
					}else {
						break;
					}					
					count++;
					if (count == param.getWord().length()) {
						if (palabra.equalsIgnoreCase(param.getWord())) {
							param.setStart_row(String.valueOf(x));
							y = y+ (count-1);
							param.setStart_col(String.valueOf(y));
							param.setContains("true");
							return param;
						} else {
							y = y+(count-1);
							palabra = "";
							count =0;
						}
					}
				}					
			}	
			
			for (int x = matrix.length-1; x > 0; x--) {
				count = 0;
				palabra = "";
				for (int y = 0; y < matrix[0].length; y++) {
					if((x-count) >= 0) {
						palabra += Character.toString(matrix[x-count][y]);	
					}else {
						break;
					}					
					count++;
					if (count == param.getWord().length()) {
						if (palabra.equalsIgnoreCase(param.getWord())) {
							param.setStart_row(String.valueOf(x));
							y = y- (count-1);
							param.setStart_col(String.valueOf(y));
							param.setContains("true");
							return param;
						} else {
							y = y-(count-1);
							palabra = "";
							count =0;
						}
					}
				}					
			}	
			
		}
		param.setContains("false");
		return param;
	}
}
