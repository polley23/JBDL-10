package com.gfg.jbdl10springintro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Calculator {
    @Autowired
    @Qualifier("multiplierImpl")
    private Multiplier multiplier;
    @Autowired
    private Divisor divisor;

    public void setMultiplier(Multiplier multiplier) {
        this.multiplier = multiplier;
    }

    public void setDivisor(Divisor divisor) {
        this.divisor = divisor;
    }

    public Calculator() {
    }

    Float mul(Float a, Float b){
        return multiplier.multiply(a,b);
    }

    Float div(Float a, Float b){
        return divisor.divide(a,b);
    }
}
