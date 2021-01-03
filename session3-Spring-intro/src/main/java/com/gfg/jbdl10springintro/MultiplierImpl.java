package com.gfg.jbdl10springintro;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("multiplierImpl")
public class MultiplierImpl implements Multiplier{
    public MultiplierImpl() {
    }
    @Override
    public Float multiply(Float a, Float b) {
        return a*b;
    }
}
