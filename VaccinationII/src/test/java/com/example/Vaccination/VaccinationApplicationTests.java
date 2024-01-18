package com.example.Vaccination;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testing Vaccine Application")
@SpringBootTest
class VaccinationApplicationTests {
	private User myUser;
	@MockBean
	private TimeAndLocation timeAndLocationMock;
	@MockBean
	private Vaccine vaccineMock;
	String myVaccine="";
	String userType="";
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	private String randomVaccineCall() {
		ArrayList<String> vaccines = new ArrayList<>();
		vaccines.add("Covid");
		vaccines.add("Polio");
		vaccines.add("Typhoid");
		Random random = new Random();
		return vaccines.get(random.nextInt(vaccines.size()));
	}
	private String randomUserCall() {
		ArrayList<String> newUser = new ArrayList<>();
		newUser.add("father");
		newUser.add("spouse");
		newUser.add("mother");
		newUser.add("self");
		Random random = new Random();
		return newUser.get(random.nextInt(newUser.size()));
	}

	@BeforeEach
	public void setup() {
		myVaccine = randomVaccineCall();
		userType= randomUserCall();
		when(vaccineMock.getType()).thenReturn(myVaccine);
		when(timeAndLocationMock.getDetails()).thenReturn("12:00 AM at New Delhi on 2023-05-22");
		switch(userType){
			case "father":{
				Father father = new Father();
				father.setVaccine(vaccineMock);
				father.setTimeAndLocation(timeAndLocationMock);
				myUser = father;
				break;
			}
			case "mother":{
				Mother mother = new Mother();
				mother.setVaccine(vaccineMock);
				mother.setTimeAndLocation(timeAndLocationMock);
				myUser = mother;
				break;
			}
			case "spouse":{
				Spouse spouse = new Spouse();
				spouse.setVaccine(vaccineMock);
				spouse.setTimeAndLocation(timeAndLocationMock);
				myUser= spouse;
				break;
			}
			case "self":{
				Self self = new Self();
				self.setVaccine(vaccineMock);
				self.setTimeAndLocation(timeAndLocationMock);
				myUser= self;
				break;
			}
			default: {
				throw new RuntimeException("UserType is not found");
			}
		}
	}

	@Order(1)
	@DisplayName("Testing Bean Creation for Selected User")
	@Test
	void UserBeanGeneration(){
		String newUserType= userType;
		try{
			User user = (User) context.getBean(newUserType + myVaccine);
			Assertions.assertFalse(user.IsVaccinated(), "Sorry but " + newUserType + " is already vaccinated.A user can only be vaccinated once.");
		}catch(RuntimeException e){
			throw new RuntimeException("Bean for the selected user is not found from Application Context.The Bean id should be like " + newUserType + myVaccine);
		}
	}

	@Order(2)
	@DisplayName("Testing Setter Dependency Injection for Selected User")
	@Test
	public void UserSetterInjection() {
		Vaccine vaccine = myUser.getVaccineDetails();
		Assertions.assertNotNull(vaccine.getType(),"Setter Dependency Injection failed");
	}

	@Order(3)
	@DisplayName("Testing Output for the Selected User")
	@Test
	public void UserOutput() {
		final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		final PrintStream out = System.out;
		myUser.setUserDetails("Dummy name",23,timeAndLocationMock);
		System.setOut(new PrintStream(outstream));
		myUser.setAppointment();
		System.setOut(out);
		System.out.println(outstream.toString());
		Assertions.assertTrue(validateOutput("Dummy name","12:00 AM at New Delhi on 2023-05-22",outstream.toString()),"Error in getting setAppointment()");
		Assertions.assertTrue(myUser.IsVaccinated(),"User is not vaccinated");
	}

	boolean validateOutput(String name,String timeAndLocation,String output){
		return !output.isBlank() && output.contains(name) && output.contains(timeAndLocation);
	}

}
