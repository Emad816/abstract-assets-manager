package com.pluralsight;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Vehicle extends Asset {

    String makeModel;
    int year;
    int odometer;


    public Vehicle(String description, String dateAcquired, BigDecimal originalCost,
                   String makeModel, int year, int odometer) {
        super(description, dateAcquired, originalCost);
        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    @Override
    public BigDecimal getValue() {

        //counting car age on this current year
        LocalDate localDate = LocalDate.now();
        int yearsOld = localDate.getYear() - year;

        //convert car age to bigDecimal for
        BigDecimal age = BigDecimal.valueOf(yearsOld);

        BigDecimal value = null;
        BigDecimal reduceRate;

        if (yearsOld >= 0 && yearsOld <= 3) {
            reduceRate = age.multiply(BigDecimal.valueOf(0.03));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld >= 4 && yearsOld <= 6) {
            reduceRate = age.multiply(BigDecimal.valueOf(0.06));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld >= 7 && yearsOld <= 10) {
            reduceRate = age.multiply(BigDecimal.valueOf(0.08));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld > 10) {
            value = BigDecimal.valueOf(1000);
        }else {
            value = originalCost;
        }

        String model = makeModel.toLowerCase();
        if(odometer > 100000){
            if(!model.contains("honda") && !model.contains("toyota")){
                value = value.subtract(value.multiply(BigDecimal.valueOf(0.25)));
            }
        }

        return value;
    }
}
