package com.example.demo.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.dto.StatsDTO;
import com.example.demo.entities.Persona;
import com.example.demo.entities.Stats;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.StatsRepository;

@Service
public class PersonaService {
	private PersonaRepository repository;
	private StatsRepository statsRepository;
//Si colocara el array adn como estatico me consumiria mucha memoria, en cambio los siguientes atributos no consumen tanto, y me permite no sobrecargar tanto los métodos que los usan.
	private static char aux = ' ', temp = ' ';
	private static Integer tam, cont = 1, cont2 = 0, j;

	public PersonaService(PersonaRepository repository, StatsRepository statsRepository) {
		super();
		this.repository = repository;
		this.statsRepository = statsRepository;
	}

	@Transactional
	public List<PersonaDTO> findAll() throws Exception {

		List<Persona> entities = repository.findAll();

		List<PersonaDTO> dtos = new ArrayList<>();

		try {

			for (Persona i : entities) {
				// cargo de la entidad al Dto
				PersonaDTO unDto = new PersonaDTO();

				unDto.setId(i.getId());
				unDto.setNombre(i.getNombre());
				unDto.setApellido(i.getApellido());
				unDto.setDni(i.getDni());
				unDto.setEdad(i.getEdad());
				unDto.setDomicilio(i.getDomicilio());
				// Convierte el String adn de la entidad a un array para colocarlo en el dto.
				// Obtengo cada posición en base al separador coma ","
				String[] adn = i.getAdn().split(",");
				unDto.setAdn(adn);
//			Cargo a cada elemento el dto
				dtos.add(unDto);
			}

			return dtos;

		} catch (Exception e) {

			throw new Exception();

		}

	}

	
	  @Transactional public PersonaDTO findById(int id) throws Exception {
	  
	  // se usa para atrapar un null 
		  Optional<Persona> entityOptional = repository.findById(id); 
		  // dto auxiliar 
	  PersonaDTO unDto = new PersonaDTO();
	  
	  try { // Lo convierto a entidad 
		  Persona entity = entityOptional.get();
	  
	  unDto.setId(entity.getId());
	  unDto.setNombre(entity.getNombre());
	  unDto.setApellido(entity.getApellido()); 
	  unDto.setDni(entity.getDni());
	  unDto.setEdad(entity.getEdad());
	  unDto.setEdad(entity.getEdad());
		String[] adn = entity.getAdn().split(",");
	  unDto.setDomicilio(entity.getDomicilio()); unDto.setAdn(adn);
	  return unDto ;
	  
	  } catch (Exception e) {
	  
	  throw new Exception();
	  
	  }
	  
	  
	  }
	  
	  
	 
	@Transactional
	public PersonaDTO save(PersonaDTO dto) throws Exception {

		// creo la entidad a persistir
		Persona entity = new Persona();
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDni(dto.getDni());
		entity.setEdad(dto.getEdad());
		entity.setDomicilio(dto.getDomicilio());
		// convierto el array en cadena, ya que la entidad no recibe un array. Cada
		// posición la separa con una coma
		String adn = String.join(",", dto.getAdn());
		entity.setAdn(adn);
		try {

			entity = repository.save(entity);
			// cargo el id generado
			dto.setId(entity.getId());
			return dto;

		} catch (Exception e) {

			throw new Exception();

		}

	}

	
	 
	  @Transactional public boolean delete(int id, boolean key) throws Exception { 
		  try { 
			  if (repository.existsById(id)) {
	  
	  repository.deleteById(id);
	  
	  //Actualizo el stats
	  Stats st= new Stats();
	  Optional<Stats> sto= statsRepository.findById(1);
	  st=sto.get();
	
	  st.setCount_mutant_dna(st.getCount_mutant_dna()-1);
	  if(key) st.setCount_human_dna(st.getCount_human_dna()+1); //para cuando edito y debo eliminar al convertirse en humano
	  double r=st.getCount_mutant_dna(), r2=st.getCount_human_dna(), ratio=0;
	  if(st.getCount_human_dna()!=0) {
		  ratio=r/r2;
		
	  }
	  st.setRatio(ratio);
	  statsRepository.save(st);
	  return true; 
	  } else {
	  
	  throw new Exception(); }
	  
	  } catch (Exception e) {
	  
	  throw new Exception(); } }
	
	
	@Transactional public PersonaDTO update (int id, PersonaDTO dto) throws
	  Exception {
	  
	  Optional<Persona> entityOptional = repository.findById(id);
	  

	  try {
	 
	  Persona entity = entityOptional.get();
	  
	  entity.setId(id); entity.setNombre(dto.getNombre());
	  entity.setApellido(dto.getApellido()); entity.setDni(dto.getDni());
	  entity.setEdad(dto.getEdad()); entity.setDomicilio(dto.getDomicilio());
		String adn = String.join(",", dto.getAdn());
	  entity.setAdn(adn); repository.save( entity);
	  
	  dto.setId(entity.getId());
	  
	  return dto;
	  
	  
	  
	  } catch (Exception e) {
	  
	  throw new Exception();
	  
	  }
	  
	 }
	  
	public boolean isMutant(String[] adn) {
		// Lo inicializamos en cero porque después de guardar un mutante, guardará
		// cualquier cosa, porque siempre será igual a dos o mayor.
		cont2 = 0;
		tam = adn.length; // Obtengo la longitud del arreglo
//Cada uno de los siguientes procedimientos modifica o no la variable cont2 que determina si es mutante.
		horizontal(adn);
		vertical(adn);
		oblicuo1A(adn);
		oblicuo1B(adn);
		oblicuo2A(adn);
		oblicuo2B(adn);

		// Si hay dos o más secuencias de cuatro letras iguales devuelve true
return cont2>=2;
	}

	private void horizontal(String[] adn) {
//En este método la variable "i" representa a las filas y "j" a las columnas
		for (int i = 0; i < tam; i++) { // El marcador se posiciona en una fila

			for (j = 1; j < tam; j++) { // Se recorren todas las columnas de la fila actual
				aux = adn[i].charAt(j - 1);// Primer posición
				temp = adn[i].charAt(j); // Posición siguiente
				if (comprobar(j)) { // Si se llega a una posición de columna donde es inútil recorrerlas y ya no se
									// han encontrado igualdades anteriores salta a la otra fila
					break;
				}
			}
		}

	}

	private void vertical(String[] adn) {
//"i" toma la posición de cada columna en este caso y "j" de las filas
		for (int i = 0; i < tam; i++) {

			for (j = 1; j < tam; j++) {
				aux = adn[j - 1].charAt(i);
				temp = adn[j].charAt(i);
				if (comprobar(j)) {
					break;
				}
			}
		}

	}

	private void oblicuo1A(String[] adn) {

		Integer i = 0; // Se creó este objetos en vez de una variable primitiva para no tapar el objeto
						// estatico "j" que es usado en el contexto del método comprobar
		for (int k = 0; (tam - k) != 3; k++) { // La "k" hace referencia a la cantidad de diagonales que se va a
												// recorer, la condición que presenta este for ya está explicada en
												// comprobar.
//"k" marca la posicion incial de cada diagonal y la cantidad de ellas, por lo tanto en el siguiente bucle j=k+1 porque se salta a otra columna, pero la anterior es j-1
//La "i" referencia a la fila y la "j" a la columna 
//"j" utiliza k+1 para porque estaria empezando de una columna anterior y debe ser mayor o igual a la posición de k, que es cada diagonal de izq a derecha.
			for (i = 1, j = k + 1; j < tam; j++, i++) { // para entender el recorrido guiarse en las imagnes adjuntadas
														// en este archivo. Este for recorre solo una diagonal, el de
														// arriba hace que se recorran las correspondientes.
				aux = adn[i - 1].charAt(j - 1); //
				temp = adn[i].charAt(j);
				if (comprobar(j)) {
					break;
				}

			}
		}

	}

	private void oblicuo1B(String[] adn) {

		Integer i = 0;
		for (int k = 1; (tam - k) != 3; k++) {

			for (i = k + 1, j = 1; i < tam; j++, i++) {
				aux = adn[i - 1].charAt(j - 1);
				temp = adn[i].charAt(j);
				if (comprobar(i)) {
					break;
				}

			}
		}

	}

	private void oblicuo2A(String[] adn) {

		Integer i = 0;
		for (int k = 3; k < tam; k++) {

			for (i = k - 1, j = 1; i >= 0; j++, i--) {
				aux = adn[i + 1].charAt(j - 1);
				temp = adn[i].charAt(j);
				if (comprobar(i)) {
					break;
				}

			}
		}

	}

	private void oblicuo2B(String[] adn) {

		Integer i = 0;
		for (int k = 1; (tam - k) != 3; k++) {

			for (i = tam - 2, j = k + 1; j < tam; j++, i--) {
				aux = adn[i + 1].charAt(j - 1);
				temp = adn[i].charAt(j);
				if (comprobar(j)) {
					break;
				}

			}
		}

	}

	private boolean comprobar(int j) {
		if (temp == aux) { // Si el caracter anterior y su siguiente son iguales aumenta el contador

			cont++;

			if (cont == 4) { // Cuando el contador llegué a cuatro significa que ha habido una secuencia de 4
								// caracteres iguales (que el contador no se ha reiniciado) antes.
				/*
				 * //El contador se coloca en uno porque cada vez que el caracter anterior y el
				 * siguiente son distintos el caracter siguiente toma el lugar del caracter
				 * anterior y la secuencia está en uno (hay, y no se sabe si habrá otro
				 * caracter, en caso de haberlo se sigue incrementando el contador)
				 */

				cont = 1;
				cont2++; // Aumenta por cada secuencia de cuatro letras iguales

			}

		} else {
			cont = 1; // Cuando el caracter anterior (o actual) es dintito al siguiente se reinicia el
						// contador.

			if ((tam - j) == 3) { /*
									 * Como ejemplo tenemos una matriz de 6x6, si se llegara a la posición 3 de la
									 * columna o fila y el caracter anterior y siguientes son distintos; no tendría
									 * sentido continuar por que la secuencia como máximo llegará a una longitud de
									 * 3, y nunca de 4. Todo lo anterior si obviamente se ha interrumpido el
									 * contador. Si no se hubierra interrumpido y estamos en tal posición sigue
									 * contando hasta armar la secuencia completa de 4 caracteres.
									 */

				return true; // retornamos true para romper el for del método actual y no consumir recursos
								// innecesarios.
			}

		}
		return false; // no se hace break;

	}

public void contar(boolean human) {
		Stats st = new Stats();
		List<Stats> sto;

		if (human) {
			if (statsRepository.count() == 0L) {

				st.setCount_human_dna(1);

			} else {
				sto=statsRepository.findAll();
				st=sto.get(0);
				
				//sto = statsRepository.findById(1); //si lo dejo asi al cambiar el id de la tabla me lanza un error
			

				st.setCount_human_dna(st.getCount_human_dna() + 1);
				// tuve que cargar el valor de los atributos de a uno, porque el ratio siempre
				// me daba cero al cargar todo junto
				// //st.getCount_mutant_dna()/st.getCount_human_dna()
				double r = 0, r2 = 0, ratio = 0;

				if (st.getCount_human_dna() == 0)
					st.setRatio(0);
				else {
					r = st.getCount_mutant_dna();
					r2 = st.getCount_human_dna();
					ratio = r / r2;
					st.setRatio(ratio);
				}
			}
		} else {
			if (statsRepository.count() == 0L) {

				st.setCount_mutant_dna(1);

			} else {
				sto=statsRepository.findAll();
				st=sto.get(0);
				
				st.setCount_mutant_dna(st.getCount_mutant_dna() + 1);
				double r = 0, r2 = 0, ratio = 0;

				if (st.getCount_human_dna() == 0) //si no pusiera la condición y dividiera directamente tendría un error infinity y fallaría el servidor, no caería pero la request fallaria
					st.setRatio(0);
				else {
					r = st.getCount_mutant_dna();
					r2 = st.getCount_human_dna();
					ratio = r / r2;
					st.setRatio(ratio);
				}

			}
		}

		statsRepository.save(st);
	}

public StatsDTO getStats() {
		StatsDTO dto = new StatsDTO();
		Stats st = new Stats();
		List<Stats> stl;
		

		
		try {
			stl=statsRepository.findAll();
		st=sto.get(0);
			dto.setCount_human_dna(st.getCount_human_dna());
			dto.setCount_mutant_dna(st.getCount_mutant_dna());
			dto.setRatio(st.getRatio());

			return dto;
		} catch (Exception e) {
			return dto;
		}

	}


}
