package cesarch;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/search-horandvert")
public class Controlador {

	@Autowired
	private Servicio servicio = new Servicio();

	@GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> informacionMatrix(@RequestBody parametros param) {
		try {

			String matriz = param.getSearchword();
			Double fila = param.getRows();
			String palabra = param.getWord();
			char[][] matrix = new char[fila.intValue()][fila.intValue() * 2];
			ArrayList<Character> sopaLetras = new ArrayList<>();
			for (int n = 0; n < matriz.length(); n++) {
				sopaLetras.add(matriz.charAt(n));
			}
			int count = 0;
			for (int x = 0; x < matrix.length; x++) {
				for (int y = 0; y < matrix[x].length; y++) {
					matrix[x][y] = sopaLetras.get(count);
					count++;
				}
			}
			Object solu = this.servicio.search(matrix, palabra);
			ArrayList<Object> resultados = new ArrayList();
			resultados = (ArrayList<Object>) solu;
			if ((boolean)resultados.get(2) == true) {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + "true" + "\","
                        + "\"start_row\": \"" + resultados.get(0) + "\","
                        + "\"start_col\": \"" + resultados.get(1) + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			} else {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + "false" + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
}