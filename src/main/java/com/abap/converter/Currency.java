package com.abap.converter;

public class Currency {

    private String numCode;
    private String charCode;
    private String name;
    private Float nominal = 1.0f;
    private Double value = 0.0;

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Float getNominal() {
        return nominal;
    }

    public void setNominal(Float nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public double exchangeRateOneCurrencyToRub(){
        return getValue()/getNominal();
    }


}
