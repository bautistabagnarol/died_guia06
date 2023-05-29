package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import died.guia06.util.Registro;
import died.guia06.util.RegistroAuditoriaException;
import died.guia06.util.CreditosInsuficientes;
import died.guia06.util.InscripcionConExito;
import died.guia06.util.LimiteCupo;
import died.guia06.util.LimiteMateriasCursadoRegularException;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su
 * nombre y ciclo lectivo. Un curso guarda una lista de los inscriptos actuales
 * que tienen. Un curso, al aprobarlo, otorga una cantidad de creditos definidas
 * en el curso. Un curso requiere que para inscribirnos tengamos al menos la
 * cantidad de creditos requeridas, y que haya cupo disponible
 * 
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo = 30; // le pongo un limite de cupo;
	private List<Alumno> inscriptos = new ArrayList<Alumno>();
	private Integer creditos;
	private Integer creditosRequeridos;
	private Registro log;

	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}

	public Curso(Integer id, String nombre, Integer cicloLectivo, Integer creditos, Integer creditosRequeridos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cicloLectivo = cicloLectivo;
		this.creditos = creditos;
		this.creditosRequeridos = creditosRequeridos;
		this.log = new Registro();

	}

	public List<Alumno> getInscriptos() {
		return inscriptos;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public Integer getCreditosRequeridos() {
		return creditosRequeridos;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Integer getCicloLectivo() {
		return cicloLectivo;
	}

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega
	 * al curso, agrega el curso a la lista de cursos en los que está inscripto el
	 * alumno y retorna verdadero. Caso contrario retorna falso y no agrega el
	 * alumno a la lista de inscriptos ni el curso a la lista de cursos en los que
	 * el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe a) tener como minimo los creditos
	 * necesarios b) tener cupo disponibles c) puede estar inscripto en simultáneo a
	 * no más de 3 cursos del mismo ciclo lectivo.
	 * 
	 * @param a
	 * @return
	 */
	public Boolean inscribir(Alumno a) throws Registro, CreditosInsuficientes {
		try {
			if (a.creditosObtenidos() >= this.creditosRequeridos && this.cupo > 0) {
				if (a.cantCursosMismoCicloLectivo(cicloLectivo) < 3) {
					this.inscriptos.add(a);
					a.inscripcionAceptada(this);
					log.registrar(this, "inscribir ", a.toString());
					return true;
				}
			}
		} catch (IOException e) {
			throw new CreditosInsuficientes(e.getMessage());
		}
		return false;

	}

	public void inscribirAlumno(Alumno a) throws RegistroAuditoriaException, LimiteMateriasCursadoRegularException,
			InscripcionConExito, LimiteCupo, CreditosInsuficientes {
		if (a.creditosObtenidos() >= this.creditosRequeridos) {
			if (this.cupo > 0) {
				if (a.cantCursosMismoCicloLectivo(cicloLectivo) < 3) {
					inscriptos.add(a);
					a.inscripcionAceptada(this);
					cupo--;
					try {
						log.registrar(this, "inscribir ", a.toString());
//						throw new InscripcionConExito("Inscripcion con exito del alumno " + a + " en el curso " + this);
					} catch (IOException e) {
						throw new RegistroAuditoriaException("Excepción de entrada salida de la clase registro");
					}
				} else {
					throw new LimiteMateriasCursadoRegularException(
							"El alumno está cursando todas las materias de cursado regular");
				}
			} else {
				throw new LimiteCupo("No hay cupos disponible. Limite maximo de cupos alcanzados");
			}
		} else {
			throw new CreditosInsuficientes(
					"El alumno " + a + "no tiene los creditos suficientes para inscribirse al curso " + this.nombre);
		}
	}

	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() {

		try {
			log.registrar(this, "imprimir listado", this.inscriptos.size() + " registros ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Collections.sort(inscriptos);
		System.out.println(inscriptos.toString());

	}
	
	public void aprobarAlumno(Alumno a) {
		try {
			log.registrar(this, "aprobar ",a.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		inscriptos.remove(a);
		cupo++;
	}

	

}
