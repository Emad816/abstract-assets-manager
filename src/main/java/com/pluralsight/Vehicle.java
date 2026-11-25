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

        //Car's age = current year minus the model year
        LocalDate localDate = LocalDate.now();
        int yearsOld = localDate.getYear() - year;

        //BigDecimal version of the age, so the rate math stays exact
        BigDecimal age = BigDecimal.valueOf(yearsOld);

        BigDecimal value;
        BigDecimal reduceRate;


        if (yearsOld >= 0 && yearsOld <= 3) {
            // 0-3 years old: 3% off the cost for each year
            reduceRate = age.multiply(BigDecimal.valueOf(0.03));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld >= 4 && yearsOld <= 6) {
            // 4-6 years old: 6% off the cost for each year
            reduceRate = age.multiply(BigDecimal.valueOf(0.06));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld >= 7 && yearsOld <= 10) {
            // 7-10 years old: 8% off the cost for each year
            reduceRate = age.multiply(BigDecimal.valueOf(0.08));
            value = originalCost.subtract(originalCost.multiply(reduceRate));
        }else if (yearsOld > 10) {
            // Over 10 years old: flat $1000
            value = BigDecimal.valueOf(1000);
        }else {
            // Future model year (negative age) keep at same price
            value = originalCost;
        }

        // Over 100,000 miles: take 25% off, unless it's a Honda or Toyota
        String model = makeModel.toLowerCase();
        if(odometer > 100000){
            if(!model.contains("honda") && !model.contains("toyota")){
                value = value.subtract(value.multiply(BigDecimal.valueOf(0.25)));
            }
        }

        return value;
    }
}
