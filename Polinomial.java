package com.company;

public class Polinomial {
    private int power;
    private int rate;

    public Polinomial(int power, int rate){
        this.power = power;
        this.rate = rate;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString(){
        return this.power + ", " + this.rate;
    }
}
