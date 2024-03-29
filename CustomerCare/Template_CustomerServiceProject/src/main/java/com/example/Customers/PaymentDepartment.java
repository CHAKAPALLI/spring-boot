package com.example.Customers;

/*
     This class is an implementation of a CustomerCare Interface based on the selection 
     in the console the department type is selected.You need to complete this class 
     based on the following tasks.

     Tasks:
       1. Override the methods of CustomerCare Interface:
       2. Build your logic for all the method based on the description given in CustomerCare Interface.
 */
public class PaymentDepartment implements CustomerCare {
	
    private String department= "Payment Department";
    private String customerName;
    private String issue; 
    private double refId;

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public String getService() {
return "Welcome "+customerName+" to the "+department+". Please tell us your query";
    }

    @Override
    public void setCustomerName(String name) {
this.customerName=name;
    }

    @Override
    public void setProblem(String problem) {
this.issue=problem;
    }

    @Override
    public String getProblem() {
return this.issue;
    }
}
