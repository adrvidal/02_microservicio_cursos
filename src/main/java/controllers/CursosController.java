package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Curso;

@RestController
public class CursosController {
	List<Curso> cursosList = new ArrayList<Curso>();

	@PostConstruct
	public void inicalizacion() {
		cursosList.add(new Curso("Java", 6, "08:00 - 14:00"));
		cursosList.add(new Curso("CSS", 2, "15:00 - 17:00"));
		cursosList.add(new Curso("javaScript", 3, "17:00 - 20:00"));
		cursosList.add(new Curso("Java", 4, "sabado"));
		cursosList.add(new Curso("Java", 2, "domingo"));
	}

	// Devolvemos un curso cualquiera de un listado
	@GetMapping(value = "curso", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso curso() {
		// public Curso(String nombre, int duracion, String horario) {
		return new Curso("Java", 20, "08:00 - 14:00");
	}

	// Devolvemos el listado de cursos
	@GetMapping(value = "lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listCursos() {
		return cursosList;
	}

	// Devolvemos un determinado curso del listado
	@GetMapping(value = "lista/{nombrecurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursodeterminado(@PathVariable("nombrecurso") String nombrecurso) {
		List<Curso> listCoicidencias = new ArrayList<Curso>();
		for (Curso curso : cursosList) {
			if (nombrecurso.equals(curso.getNombre()))
				listCoicidencias.add(curso);
		}
		return listCoicidencias;
	}

	// Insertar un determinado curso en el listado
	@PostMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> insertarCurso(@RequestBody Curso curso) {
		cursosList.add(curso);
		return cursosList;
	}

	// Actualizar un determinado curso en el listado
	@PutMapping(value = "curso", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> actualizarCurso(@RequestBody Curso curso) {

		for (int i = 0; i < cursosList.size(); i++) {
			if (curso.getNombre().equals(cursosList.get(i).getNombre()))
				cursosList.set(i, curso);
		}
		return cursosList;
	}
	
	@DeleteMapping( value = "curso/{nombre}")
	public List<Curso> deleteCurso(@PathVariable  ("nombre") String nombre) {
		/*Expresión lambda: 
		 * c es el nombre del parámetro del método
		 * y lo que está a la derecha la implementación del método
		 * 
		 * Esta expresiones lambda permiten Impementar al vuelo una interfaz funcional.
		 * */
		cursosList.removeIf(c->c.getNombre().equals(nombre));
		return cursosList;		
	}

}
