package died.guia06;

import java.io.IOException;

import died.guia06.util.CreditosInsuficientes;
import died.guia06.util.InscripcionConExito;
import died.guia06.util.LimiteCupo;
import died.guia06.util.LimiteMateriasCursadoRegularException;
import died.guia06.util.Registro;
import died.guia06.util.RegistroAuditoriaException;

public class App {

	public static void main(String[] args)  {
	
	Alumno a1 = new Alumno("bau",23);
	Alumno a2 = new Alumno("juan",24);
	Alumno a3 = new Alumno("agus",25);
	
	Curso c1 = new Curso(1,"Died",2023,2,5);
	Curso c2 = new Curso(2,"diseño",2023,5,0);
	//Inscribo un alumno con creditos insuficientes
	try {
		c1.inscribirAlumno(a1);
		
	} catch (RegistroAuditoriaException | LimiteMateriasCursadoRegularException | InscripcionConExito | LimiteCupo |CreditosInsuficientes e) {
		System.out.println(e.getMessage());
	}
	//Inscribo alumnos con creditos suficientes
	try {
		c2.inscribirAlumno(a1);
		c2.inscribirAlumno(a2);
		c2.inscribirAlumno(a3);
		
	} catch (RegistroAuditoriaException | LimiteMateriasCursadoRegularException | InscripcionConExito | LimiteCupo |CreditosInsuficientes e) {
		System.out.println(e.getMessage());
	}
	
	//Cantidad de mismo ciclo lectivo al curso "c2" que tiene un alumno
	System.out.println("Cantidad de mismo ciclo lectivo al curso c2 que tiene un alumno es: "+a2.cantCursosMismoCicloLectivo(c2.getCicloLectivo()));
	
	//Cantidad de cupos disponibles en el curso "c2"
	System.out.println("Cantidad de cupos disponibles en el curso c2 es: "+c2.getCupo());
	
	a1.aprobar(c2);
	System.out.println("Creditos totales aprobando su primer curso "+a1.creditosObtenidos());	
	
	try {
		c1.inscribirAlumno(a1);
	} catch (RegistroAuditoriaException | LimiteMateriasCursadoRegularException | InscripcionConExito | LimiteCupo |CreditosInsuficientes e) {
		System.out.println(e.getMessage());
	}
	a1.aprobar(c1);
	System.out.println("Creditos totales aprobando su segundo curso "+a1.creditosObtenidos());	
	
	//Cantidad de cupos disponibles en el curso "c2" luego de aprobar 1 alumno de ese curso
		System.out.println("Cantidad de cupos disponibles en el curso c2 es: "+c2.getCupo());
	
	
	
	c2.imprimirInscriptos();
}
	
}


