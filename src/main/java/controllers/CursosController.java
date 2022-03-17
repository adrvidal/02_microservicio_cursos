package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Curso;

@RestController
public class CursosController {
	List<Curso> cursosList=new ArrayList<Curso>();

	@PostConstruct
	public void inicalizacion() {
		cursosList.add(new Curso("Java", 6,"08:00 - 14:00"));
		cursosList.add(new Curso("CSS", 2,"15:00 - 17:00" ));
		cursosList.add(new Curso("javaScript", 3,"17:00 - 20:00" ));
		cursosList.add(new Curso("Java",4 ,"sabado" ));
		cursosList.add(new Curso("Java", 2,"domingo" ));
	}
	// Devolvemos un curso cualquiera de un listado 
	@GetMapping(value = "curso", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso curso() {
		// 	public Curso(String nombre, int duracion, String horario) {
		return new Curso("Java", 20,"08:00 - 14:00" );
	}
	
	// Devolvemos el listado de cursos
	@GetMapping(value = "lista", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listCurso() {
		return cursosList;
	}
	
	
	// Devolvemos un determinado curso del listado
	@GetMapping(value = "lista/{nombrecurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso>  cursodeterminado(@PathVariable("nombrecurso") String nombrecurso) {
		List<Curso> listCoicidencias = new ArrayList<Curso>();
		for (Curso curso : cursosList) {
			if(nombrecurso.equals(curso.getNombre()))listCoicidencias.add(curso);
		}
		return listCoicidencias;
	
	}
}
