package com.gfg.jbdl10springintro;

import org.springframework.stereotype.Component;

@Component
public class DivisorImpl implements Divisor{
    public DivisorImpl() {
    }
    @Override
    public Float divide(Float a, Float b) {
        return a/b;
    }
}
