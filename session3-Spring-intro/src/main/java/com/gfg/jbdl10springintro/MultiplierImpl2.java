package com.gfg.jbdl10springintro;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("multiplierImpl2")
public class MultiplierImpl2 implements Multiplier{
    public MultiplierImpl2() {
    }
    @Override
    public Float multiply(Float a, Float b) {
        System.out.println("In second method");
        return a*b;
    }
}
