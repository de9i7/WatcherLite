package com.sandbox.common.covariancy;

/**
 * Created by DKachurovskiy on 9/23/2014.
 */
public class Main {
    public static void main(String[] args) {
        Employee [] managers = new Employee [6];
        managers[0] = new Employee(new Dude());
        managers[1] = new Employee(new Boy());
        managers[2] = new Employee(new Girl());

        managers[3] = new Manager(new Dude());
        managers[4] = new Manager(new Boy());
        managers[5] = new Manager(new Girl());

        for (Employee m : managers){
            System.out.println("Sex: " + m.getPerson() );
        }
    }
}

class Manager extends Employee
{
    Manager(Person person){
        super(person);
    }

    @Override
    public Dude getPerson(){
        System.out.print("Manager.getPerson() --> ");
        return (Dude) person;
    }
}

class Employee
{
    protected Person person;
    Employee(Person person){
        this.person = person;
    }

    public Person getPerson()
    {
        System.out.print("Employee.getPerson() --> ");
        return person;
    }
}

abstract class Person {
    protected String sex="";
    @Override
    public abstract String toString();
}

class Dude extends Person {
    protected String sex = "asexual";

    @Override
    public String toString() { return sex; }
}

class Boy extends Dude
{
    protected String sex = "male";

    @Override
    public String toString() { return sex; }
}

class Girl extends Dude {
    protected String sex = "female";

    @Override
    public String toString() { return sex; }
}
