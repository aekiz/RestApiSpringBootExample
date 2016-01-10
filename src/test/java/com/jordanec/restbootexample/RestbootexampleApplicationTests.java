package com.jordanec.restbootexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import org.springframework.test.context.web.WebAppConfiguration;
import com.jordanec.restbootexample.client.ConfederacionApi;
import com.jordanec.restbootexample.client.EquipoApi;
import com.jordanec.restbootexample.client.EstadioApi;
import com.jordanec.restbootexample.client.JugadorApi;
import com.jordanec.restbootexample.client.PaisApi;
import com.jordanec.restbootexample.client.PatrocinadorApi;
import com.jordanec.restbootexample.model.Confederacion;
import com.jordanec.restbootexample.model.Equipo;
import com.jordanec.restbootexample.model.Estadio;
import com.jordanec.restbootexample.model.Jugador;
import com.jordanec.restbootexample.model.Pais;
import com.jordanec.restbootexample.model.Patrocinador;
import com.jordanec.restbootexample.model.Status;

import retrofit.Call;
import retrofit.Retrofit;
import retrofit.JacksonConverterFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestbootexampleApplication.class)
@WebAppConfiguration
public class RestbootexampleApplicationTests {
	ConfederacionApi confederacionApi;
	PaisApi paisApi;
	EquipoApi equipoApi;
	JugadorApi jugadorApi;
	EstadioApi estadioApi;
	PatrocinadorApi patrocinadorApi;
	Retrofit retrofit;
	
	@Before
	public void setUp() {
		retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:8080")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		confederacionApi = retrofit.create(ConfederacionApi.class);
		paisApi = retrofit.create(PaisApi.class);
		equipoApi = retrofit.create(EquipoApi.class);
		jugadorApi = retrofit.create(JugadorApi.class);
		estadioApi = retrofit.create(EstadioApi.class);
		patrocinadorApi = retrofit.create(PatrocinadorApi.class);
	}
	

	@Test
	public void contextLoads() {

		testConfederacion();
		//testPais();
		//testEquipo();
		//testEstadio();
		//testPatrocinador();
		//testJugador();
		
		/*
		 * call.enqueue(new Callback<Collection<Confederacion>>() {
		 * 
		 * @Override public void
		 * onResponse(retrofit.Response<Collection<Confederacion>> response,
		 * Retrofit retrofit) { if(response.isSuccess()) {
		 * Collection<Confederacion> confe = response.body();
		 * System.out.println(confe); } else {
		 * System.out.println("response.code()="+response.code());
		 * System.out.println("response.errorBody()="+response.errorBody()); }
		 * 
		 * }
		 * 
		 * @Override public void onFailure(Throwable t) {
		 * System.out.println(t.getMessage());
		 * 
		 * } });
		 */

	}

	private void testConfederacion() {
		//assertTrue(confederacionCreateTest());
		assertNotNull(confederacionReadTest(1));			//idConfederacion
		//assertTrue(confederacionFindByNameTest());
		//assertTrue(confederacionUpdateTest());
		//assertTrue(confederacionListTest());
		//assertTrue(confederacionDeleteTest());
	}
	
	private void testPais() {
		//assertTrue(paisCreateTest());
		assertNotNull(paisReadTest(1));						//idPais
		//assertTrue(paisFindByNameTest());
		//assertTrue(paisUpdateTest());
		//assertTrue(paisListTest());
		//assertTrue(paisDeleteTest());
	}
	
	public void testEquipo() {
		//assertTrue(equipoCreateTest());
		assertNotNull(equipoReadTest(1));			//idEquipo
		//assertTrue(paisFindByNameTest());
		//assertTrue(paisUpdateTest());
		//assertTrue(equipoListTest());
		//assertTrue(paisDeleteTest());
	}
	
	public void testEstadio() {
		//assertTrue(estadioCreateTest());
		assertNotNull(estadioReadTest(1));			//idEstadio
		//assertTrue(estadioFindByNameTest());
		//assertTrue(estadioUpdateTest());
		//assertTrue(estadioListTest());
		//assertTrue(estadioDeleteTest());
	}
	
	public void testPatrocinador() {
		//assertTrue(patrocinadorCreateTest());
		assertNotNull(patrocinadorReadTest(1));			//idConfederacion
		//assertTrue(patrocinadorFindByNameTest());
		//assertTrue(patrocinadorUpdateTest());
		//assertTrue(patrocinadorListTest());
		//assertTrue(patrocinadorDeleteTest());
	}
	
	public void testJugador() {
		//assertTrue(jugadorCreateTest());
		assertNotNull(jugadorReadTest(1));			//idJugador
		//assertTrue(jugadorFindByNameTest());
		//assertTrue(jugadorUpdateTest());
		//assertTrue(jugadorListTest());
		//assertTrue(jugadorDeleteTest());
	}
	
	
	
	
	private boolean equipoCreateTest() {
		Pais pais = paisReadTest(1);	//idPais
		
		Estadio estadio1 = estadioReadTest(1);
		Estadio estadio2 = estadioReadTest(2);
		Set<Estadio> estadios = new HashSet<>();
		estadios.add(estadio1);
		estadios.add(estadio2);
		
		Patrocinador patrocinador= patrocinadorReadTest(1);
		Set<Patrocinador> patrocinadores = new HashSet<>();
		patrocinadores.add(patrocinador);
		
		Equipo equipo = new Equipo(pais, "Cartago", 3);
		
		equipo.setEstadios(estadios);
		equipo.setPatrocinadores(patrocinadores);
		
		Call<Status> call = equipoApi.createEquipo(equipo);

		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println("response.message()="+response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private Equipo equipoReadTest(int idEquipo){
		Call<Equipo> call = equipoApi.readEquipo(idEquipo);
		
		try {
			retrofit.Response<Equipo> response = call.execute();
			if(response.isSuccess()) {
				Equipo equipo = response.body();
				System.out.println(equipo);
				return equipo;
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean equipoListTest() {
		Call<Collection<Equipo>> call = equipoApi.listEquipos();

		try {
			retrofit.Response<Collection<Equipo>> response = call.execute();
			if(response.isSuccess()) {
				Collection<Equipo> equipos = response.body();
				//System.out.println(equipos);
				return true;
			}
			System.out.println(response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean equipoUpdateTest() {
		Equipo equipo = equipoReadTest(1);
		equipo.setNombre(equipo.getNombre()+"_Update");
		equipo.setPais(paisReadTest(10));
		
		Call<Status> call = equipoApi.updateEquipo(equipo, equipo.getIdEquipo());
		
		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println(response.message());
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	private Jugador jugadorReadTest(int idJugador) {
		Call<Jugador> call = jugadorApi.readJugador(idJugador);
		
		try {
			retrofit.Response<Jugador> response = call.execute();
			if(response.isSuccess()) {
				Jugador jugador = response.body();
				System.out.println(jugador);
				return jugadorReadTest(idJugador);
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Estadio estadioReadTest(int idEstadio) {
		Call<Estadio> call = estadioApi.readEstadio(idEstadio);
		
		try {
			retrofit.Response<Estadio> response = call.execute();
			if(response.isSuccess()) {
				Estadio estadio = response.body();
				System.out.println(estadio);
				return estadio;
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Patrocinador patrocinadorReadTest(int idPatrocinador) {
		Call<Patrocinador> call = patrocinadorApi.readPatrocinador(idPatrocinador);
		
		try {
			retrofit.Response<Patrocinador> response = call.execute();
			if(response.isSuccess()) {
				Patrocinador patrocinador = response.body();
				System.out.println(patrocinador);
				return patrocinador;
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean paisDeleteTest() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean paisListTest() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean paisUpdateTest() {
		Pais pais = paisReadTest(1);
		pais.setNombre(pais.getNombre()+"_Updated");
		pais.setConfederacion(confederacionReadTest(5));
		
		Call<Status> call = paisApi.updatePais(pais, pais.getIdPais());
		
		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println(response.message());
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean paisFindByNameTest() {
		// TODO Auto-generated method stub
		return false;
	}

	private Pais paisReadTest(int idPais) {
		Call<Pais> call = paisApi.readPais(idPais);
		
		try {
			retrofit.Response<Pais> response = call.execute();
			if(response.isSuccess()) {
				Pais pais = response.body();
				System.out.println(pais);
				return pais;
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean paisCreateTest() {
		Confederacion confederacion = confederacionReadTest(1);
		Pais pais = new Pais(confederacion, "Panamá", 76);

		Call<Status> call = paisApi.createPais(pais);

		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println("response.message()="+response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	private boolean confederacionCreateTest() {
		Confederacion confederacion = new Confederacion("Antártida");
		confederacion.setIdConfederacion(7);
		confederacion.setCantidadDePaises(1);
		
		Call<Status> call = confederacionApi.createConfederacion(confederacion);

		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println("response.message()="+response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private Confederacion confederacionReadTest(int idConfederacion) {
		Call<Confederacion> call = confederacionApi.readConfederacion(idConfederacion);
		
		try {
			retrofit.Response<Confederacion> response = call.execute();
			if(response.isSuccess()) {
				Confederacion confederacion = response.body();
				System.out.println(confederacion);
				return confederacion;
			}
			System.out.println(response.message());
			return null;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean confederacionUpdateTest() {
		Confederacion confederacion = new Confederacion("Antártida_Updated");
		confederacion.setIdConfederacion(7);
		confederacion.setCantidadDePaises(5);
		
		Call<Status> call = confederacionApi.updateConfederacion(confederacion, 7);
		
		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println(response.message());
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean confederacionDeleteTest() {
		Call<Status> call = confederacionApi.deleteConfederacion(7);
		
		try {
			retrofit.Response<Status> response = call.execute();
			if(response.isSuccess()) {
				Status status = response.body();
				System.out.println("status.getCode()="+status.getCode()+"\nstatus.getMessage()="+status.getMessage());
				return true;
			}
			System.out.println(response.message());
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean confederacionListTest() {
		Call<Collection<Confederacion>> call = confederacionApi.listConfederaciones();

		try {
			retrofit.Response<Collection<Confederacion>> response = call.execute();
			if(response.isSuccess()) {
				Collection<Confederacion> confederaciones = response.body();
				System.out.println(confederaciones);
				return true;
			}
			System.out.println(response.message());
			return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean confederacionFindByNameTest() {
		Call<Confederacion> call = confederacionApi.findByNombre("CONMEBOL");
		
		try {
			retrofit.Response<Confederacion> response = call.execute();
			if(response.isSuccess()) {
				Confederacion confederacion = response.body();
				System.out.println(confederacion);
				return true;
			}
			System.out.println(response.message());
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
