package com.nord.view;

import com.nord.view.interfaces.IUserInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class AbstractUserInterface implements IUserInterface {

  Scanner sc = new Scanner(System.in);
  protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  @Override
  public int getValidPhoneNo(){
    while (!sc.hasNext("\\d{6}")) {
      showInvalidInputString();
      sc.next();
    }
    return sc.nextInt();
  }

  @Override
  public long getValidAccountNo(){
    while (!sc.hasNext("\\d{10}")) {
      showInvalidInputString();
      sc.next();
    }
    return sc.nextLong();
  }

  @Override
  public int getIntegerInput() {
    while (!sc.hasNextInt()) {
      showInvalidInputNumber();
      sc.next();
    }
    return sc.nextInt();
  }
  @Override
  public String getStringLineInput() {
    sc.nextLine();
    return sc.nextLine();
  }

  @Override
  public String getStringInput() {
    while (!sc.hasNext("[A-Za-z]*")) {
      showInvalidInputString();
      sc.next();
    }
    return sc.next();
  }

  @Override
  public LocalDate getDateInputLessThanToday() {
    LocalDate result;
    boolean inValidDate = true;
    do {
      result = getValidDateInput();
      if (result.isBefore(LocalDate.now())) {
        inValidDate = false;
      } else {
        showInvalidDateGreaterThanToday();
      }
    } while (inValidDate);
    return result;
  }

  @Override
  public String getValidEmailInput() {
    while (!sc.hasNext("([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6})")) {
      showInvalidEmail();
      sc.next();
    }
    return sc.next();
  }
  public int getValidAge()
  {
    int age = sc.nextInt();
    while(age<0 || age >99)
    {
      showInvalidAge();
      age = sc.nextInt();
    }
    return age;
  }
  public int getValidSalary()
  {
    int salary = sc.nextInt();
    while(salary<0)
    {
      showInvalidSalary();
      salary= sc.nextInt();
    }
    return salary;
  }
  public int getvalidSpending()
  {
    int salary = sc.nextInt();
    while(salary<0)
    {
      showInvalidSpending();
      salary= sc.nextInt();
    }
    return salary;
  }
  @Override
  public String getStringInputNoValidation() {
    while (!sc.hasNext()) {
      fieldCannotBeEmpty();
      sc.next();
    }
    return sc.next();
  }

  @Override
  public void showDateInput() {
    System.out.println("Please Enter Date (dd/MM/yyyy) ");
  }

  @Override
  public LocalDate getValidDateInput() {
    LocalDate date;
    do {
      try {
        while (!sc.hasNext("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
          showInvalidDate();
          sc.next();
        }
         date = LocalDate.parse(sc.next(), dtf);
      }catch (DateTimeParseException ex){
        date = null;
        showInvalidDate();
        System.out.println(ex.getMessage());
      }
    }while (date == null);
    return date;
  }

  @Override
  public LocalDate getDateInputGreaterThanToday() {
    LocalDate result;
    boolean inValidDate = true;
    do {
      result = getValidDateInput();
      if (result.isAfter(LocalDate.now())) {
        inValidDate = false;
      } else {
        showInvalidDateLessThanToday();
      }
    } while (inValidDate);
    return result;
  }

  @Override
  public void showInvalidInputString() {
    System.out.println("Incorrect Format, Please try again");
  }

  @Override
  public void showInvalidDateGreaterThanToday() {
    System.out.println("Date should be less than today");
  }

  @Override
  public void showInvalidInputNumber() {
    System.out.println("Invalid input");
  }

  @Override
  public void showInvalidDate() {
    System.out.println("Please enter a valid date");
  }

  @Override
  public void showInvalidEmail() {
    System.out.println("Please enter a valid email");
  }

  @Override
  public void showInvalidDateLessThanToday() {
    System.out.println("Date should be greater than today. Try Again:");
  }

  @Override
  public void fieldCannotBeEmpty() {
    System.out.println("Field cannot be empty");
  }

  @Override
  public double getDoubleInput() {
    while (!sc.hasNext("[0-9]+(\\.){0,1}[0-9]*")) {
      showInvalidDoubleInput();
      sc.next();
    }
    return sc.nextDouble();
  }

  @Override
  public void showInvalidDoubleInput() {
    System.out.println("Amount entered must be an integer");
  }
  @Override
  public void showInvalidAge()
  {
    System.out.println("Invalid Age, Please enter again");
  }
  @Override
  public void showInvalidSalary()
  {
    System.out.println("Invalid Salary, cannot be negative Please enter again");
  }
  @Override
  public void showInvalidSpending()
  {
    System.out.println("Invalid Salary, cannot be negative Please enter again");
  }
  @Override
  public String getInput() {
    return sc.next();
  }

}
