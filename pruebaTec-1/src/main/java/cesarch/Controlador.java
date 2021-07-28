package cesarch;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
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
			parametros solu = this.servicio.search(param);
			if (solu.getContains().equalsIgnoreCase("true")) {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + param.getContains() + "\","
                        + "\"start_row\": \"" + param.getStart_row() + "\","
                        + "\"start_col\": \"" + param.getStart_col() + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			} else {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + param.getContains() + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> informacionDiagonal(@RequestBody parametros param) {
		try {
			parametros solu = this.servicio.searchDiagonal(param);
			if (solu.getContains().equalsIgnoreCase("true")) {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + param.getContains() + "\","
                        + "\"start_row\": \"" + param.getStart_row() + "\","
                        + "\"start_col\": \"" + param.getStart_col() + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			} else {
				String datos = "{"
                        + "\"searchword\": \"" + param.getSearchword() + "\","
                        + "\"rows\": \"" + param.getRows() + "\","
                        + "\"word\": \"" + param.getWord() + "\","
                        + "\"contains\": \"" + param.getContains() + "\""
                        + "}";
				return new ResponseEntity<>(datos, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}

	}
}