package com.example.CustomerServicedemo;

import com.example.Customers.PaymentDepartment;
import com.example.Customers.QueryDepartment;
import com.example.Customers.SalesDepartment;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class CustomerServicedemoApplication {

	public static void main(String[] args) {

		Random random = new Random();
		/*
		You need to complete this application as mentioned in the problem 
		statement build your own logic and perform the following tasks.

			 Tasks:
		 *  1. Load the beans from ApplicationContext.xml
		 *  2. Display all the departments available and get the input from user.
		 *  3. Get the message from user and store it into the respective department.
		 *  
		 */
		System.out.println("Welcome to our customer care application \n please enter your name");
		Scanner scanner= new Scanner(System.in);
		String name=scanner.nextLine();
		System.out.println("Welcome "+name+", please select a department to connect to");
		System.out.println("1. Payment Department \n 2. Query Department \n 3. Sales Department");
		int i = scanner.nextInt();
		scanner.nextLine();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
	switch (i){
		case 1:
		{
			PaymentDepartment paymentDepartment = (PaymentDepartment)context.getBean("paymentDepartment");
			System.out.println("Welcome "+name+" to our "+paymentDepartment.getDepartment()+"\n Please enter your issue");
			String issue = scanner.nextLine();
			paymentDepartment.setProblem(issue);
			System.out.println("Dear "+name+", your issue - "+paymentDepartment.getProblem()+" has been recorded, we will shortly get back to you. \n Your reference id is "+random.nextInt());
		break;
		}

		case 2:
		{
			QueryDepartment queryDepartment = (QueryDepartment)context.getBean("queryDepartment");
			System.out.println("Welcome "+name+" to our "+queryDepartment.getDepartment()+"\n Please enter your issue");

			String issue = scanner.nextLine();
			queryDepartment.setProblem(issue);
			System.out.println("Dear "+name+", your issue - "+queryDepartment.getProblem()+" has been recorded, we will shortly get back to you. \n Your reference id is "+random.nextInt());
		break;
		}
		case 3:
		{
			SalesDepartment salesDepartment = (SalesDepartment)context.getBean("salesDepartment");
			System.out.println("Welcome "+name+" to our "+salesDepartment.getDepartment()+"\n Please enter your issue");
			String issue = scanner.nextLine();
			salesDepartment.setProblem(issue);
			System.out.println("Dear "+name+", your issue - "+salesDepartment.getProblem()+" has been recorded, we will shortly get back to you. \n Your reference id is "+random.nextInt());
		break;
		}
		default:{
			System.out.println("Invalid choice");
			break;
		}
	}
	}
}
