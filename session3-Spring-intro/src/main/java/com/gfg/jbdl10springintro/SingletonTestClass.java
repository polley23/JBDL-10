package com.gfg.jbdl10springintro;
//During the application lifetime only one instance/object
//of the class will be present/created
//Example: Logger
public final class SingletonTestClass {
    private static SingletonTestClass singletonTestClass;
    private SingletonTestClass(){
    }
    public synchronized static SingletonTestClass getSingletonTestClass(){
        if(singletonTestClass == null){
            singletonTestClass=new SingletonTestClass();
        }
        return singletonTestClass;
    }
}
