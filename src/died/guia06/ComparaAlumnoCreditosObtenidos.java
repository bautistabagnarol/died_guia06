package died.guia06;

import java.util.Comparator;
import java.util.List;

public class  ComparaAlumnoCreditosObtenidos implements Comparator<Alumno> {

	
	public int compare(Alumno o1, Alumno o2) {
		return o1.creditosObtenidos().compareTo(o2.creditosObtenidos());
	}
}
	