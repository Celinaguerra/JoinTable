package com.example.joinTable;

import com.example.joinTable.entidades.Profesor;
import com.example.joinTable.entidades.Alumno;
import com.example.joinTable.enuneraciones.Especialidades;
import com.example.joinTable.enuneraciones.Titulos;
import com.example.joinTable.repositorios.AlumnoRepository;
import com.example.joinTable.repositorios.PersonaRepository;
import com.example.joinTable.repositorios.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class JoinTableApplication {

	private static final Logger logger = LoggerFactory.getLogger(JoinTableApplication.class);

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private ProfesorRepository profesorRepository;
	@Autowired
	private AlumnoRepository alumnoRepository;

	public static void main(String[] args) {
		SpringApplication.run(JoinTableApplication.class, args);
		System.out.println("Aplicación en funcionamiento");
	}

	@Bean
	@Transactional
	CommandLineRunner init(PersonaRepository personaRepository,
						   AlumnoRepository alumnoRepository,
						   ProfesorRepository profesorRepository) {
		return args -> {
			// Crear dos profesores
			Profesor profesor1 = Profesor.builder()
					.nombre("Celina")
					.apellido("Guerra")
					.fechaIngreso(LocalDate.of(2023, 10, 18))
					.cantHijos(0)
					.titulo(Titulos.INGENIERO)
					.sueldo(new BigDecimal("150.00"))
					.build();

			Profesor profesor2 = Profesor.builder()
					.nombre("Vicky")
					.apellido("Torres")
					.fechaIngreso(LocalDate.of(2022, 8, 23))
					.cantHijos(2)
					.titulo(Titulos.LICENCIADO)
					.sueldo(new BigDecimal("500.00"))
					.build();

			// Crear tres alumnos
			Alumno alumno1 = Alumno.builder()
					.nombre("Valentina")
					.apellido("Artola")
					.legajo(62185)
					.especialidad(Especialidades.BACHILLER)
					.build();

			Alumno alumno2 = Alumno.builder()
					.nombre("Sofia")
					.apellido("Soler")
					.legajo(62186)
					.especialidad(Especialidades.PERITO_MERCANTIL)
					.build();

			Alumno alumno3 = Alumno.builder()
					.nombre("Alma")
					.apellido("Quinteros")
					.legajo(62187)
					.especialidad(Especialidades.BACHILLER)
					.build();

			// Persistir los profesores
			profesorRepository.save(profesor1);
			profesorRepository.save(profesor2);

			// Persistir los alumnos
			alumnoRepository.save(alumno1);
			alumnoRepository.save(alumno2);
			alumnoRepository.save(alumno3);

			logger.info("Profesores y alumnos creados y persistidos correctamente.");
		};
	}
}
