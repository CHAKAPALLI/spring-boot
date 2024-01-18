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
    private Covid vaccineMock;
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
                myUser = new Father(timeAndLocationMock,vaccineMock);
                break;
            }
            case "mother":{
                myUser = new Mother(timeAndLocationMock,vaccineMock);
                break;
            }
            case "spouse":{
                myUser = new Spouse(timeAndLocationMock,vaccineMock);
                break;
            }
            case "self":{
                myUser = new Self(timeAndLocationMock,vaccineMock);
                break;
            }
            default:{
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
            Assertions.assertFalse(user.IsVaccinated(), "Sorry but " + newUserType + " is already vaccinated.The User can only be vaccinated once.");
        }catch(RuntimeException e){
            throw new RuntimeException("Bean for the Selected User is not found from Application Context. The Bean id should be like " + newUserType + myVaccine);
        }
    }

    @Order(2)
    @DisplayName("Testing Constructor Dependency Injection for Selected User")
    @Test
    public void UserConstructorInjection() {
        Vaccine vaccine = myUser.getVaccineDetails();
        Assertions.assertNotNull(vaccine.getType(),"Constructor Dependency Injection failed");
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
        String actual= outstream.toString();
        System.out.println(actual);
        Assertions.assertTrue(validateOutput("Dummy name","12:00 AM at New Delhi on 2023-05-22",outstream.toString()),"Error in setAppointment() method.");
        Assertions.assertTrue(myUser.IsVaccinated(),"User is not vaccinated");
    }

    boolean validateOutput(String name,String timeAndLocation,String output){
        return !output.isBlank() && output.contains(name) && output.contains(timeAndLocation);
    }


}
