package com.kkdz.test.polymorphism;

public class DynamicDispatch {
 
    public static void main(String[] args) {
         
        Human man = new Man();
        Human women = new Women();
         
        man.sayHello();
        women.sayHello();
         
        man = new Women();
        man.sayHello();
 
    }
 
}
 
abstract class Human {
    protected abstract void sayHello();
}
 
class Man extends Human {
 
    @Override
    protected void sayHello() {
        System.out.println("hello man!");
    }
     
}
 
class Women extends Human {
 
    @Override
    protected void sayHello() {
        System.out.println("hello women!");
    }
     
}