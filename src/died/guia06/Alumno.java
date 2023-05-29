package died.guia06;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import died.guia06.util.Registro;

public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando = new ArrayList<Curso>();
	private List<Curso> aprobados = new ArrayList<Curso>();
	private Registro log;

	public Alumno(String nombre, Integer nroLibreta) {
		super();
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
	}

	public Integer creditosObtenidos() {
		int creditosObt = 0;
		for (Curso c : aprobados) {
			creditosObt += c.getCreditos();
		}
		return creditosObt;
	}

	public Integer getNroLibreta() {
		return nroLibreta;
	}
	
	public void agregarCursoNuevo(Curso c) {
		cursando.add(c);
	}

public List<Curso> getCursando() {
	return cursando;
}	
	
	public void aprobar(Curso c) {
		aprobados.add(c);	
		cursando.remove(c);
		c.aprobarAlumno(this);
}
	

	public void inscripcionAceptada(Curso c) {
				cursando.add(c);
				c.getInscriptos().add(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(nroLibreta, other.nroLibreta);
	}

	@Override
	public int compareTo(Alumno o) {
		return nombre.compareTo(o.nombre);
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", nroLibreta=" + nroLibreta + "]";
	}
	
	public int cantCursosMismoCicloLectivo(int cicloLectivo) {
		int sum = 0;
		for(Curso unCurso : cursando) {
			if(unCurso.getCicloLectivo() == cicloLectivo) sum++;
		}
		return sum;
	
	}
	

//	NO SE COMO SE HACE EL INCISO PASO 02: Implementar la interface comparable, estableciendo que la
//	comparación de 2 alumnos se hace alfabéticamente por el atributo
//	nombre.

//	    public int compareTo(Alumno otroAlumno) {
//	        return this.nombre.compareTo(otroAlumno.nombre);
//	    }
//	    
//	    public void ordenarListaDeAlumnos(List<Alumno> alumnos) {
//			Collections.sort((List<Alumno>) alumnos);
//	    }

}
