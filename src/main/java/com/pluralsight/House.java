package com.pluralsight;

import java.math.BigDecimal;

public class House extends Asset {

    String address;
    int condition;
    int squareFoot;
    int lotSize;

    public House(String description, String dateAcquired, BigDecimal originalCost,
                 String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    @Override
    public BigDecimal getValue() {

        // Assigning house squar feet rate depending on condition number
        BigDecimal houseSqFtRate;
        switch (condition) {
            case 1 -> houseSqFtRate = BigDecimal.valueOf(180);
            case 2 -> houseSqFtRate = BigDecimal.valueOf(130);
            case 3 -> houseSqFtRate = BigDecimal.valueOf(90);
            case 4 -> houseSqFtRate = BigDecimal.valueOf(80);
            default -> houseSqFtRate = BigDecimal.ZERO;
        }

        //BigDecimal version of (house SqFt & Lot SqFt & Lot SqFt Rate)
        // so the math stays exact
        BigDecimal houseSqFt = BigDecimal.valueOf(squareFoot);
        BigDecimal lotSqFt = BigDecimal.valueOf(lotSize);
        BigDecimal lotSqFtRate = BigDecimal.valueOf(0.25);

        return houseSqFtRate.multiply(houseSqFt).add(lotSqFt.multiply(lotSqFtRate));
    }
}
