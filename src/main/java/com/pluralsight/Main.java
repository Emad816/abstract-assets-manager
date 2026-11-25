package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Asset> assets = new ArrayList<>();

        //Cash
        assets.add(new Cash("Mattress stash", "2026-01-15", new BigDecimal("2500")));


        // Houses
        assets.add(new House("My house", "2018-04-12", new BigDecimal("320000"),
                "12 Maple St, Brooklyn, NY", 1, 1800, 3000));

        assets.add(new House("My vacation home", "2021-07-01", new BigDecimal("210000"),
                "88 Lake Rd, Lake George, NY", 2, 1200, 10000));

        // Vehicles
        assets.add(new Vehicle("My car", "2022-03-20", new BigDecimal("24000"),
                "Toyota Camry", 2022, 45000));

        assets.add(new Vehicle("My truck", "2015-09-05", new BigDecimal("38000"),
                "Ford F-150", 2015, 130000));

        for (Asset asset : assets) {
            String message = "";

            if (asset instanceof House) {
                message = " address: " + ((House) asset).getAddress();

            } else if (asset instanceof  Vehicle) {
                message = " " + ((Vehicle) asset).getYear() + " " + ((Vehicle) asset).getMakeModel();
            }
            System.out.println(asset.getDescription() + message
                    + " date acquired: " + asset.getDateAcquired()
                    + " original cost: $" + asset.getOriginalCost()
                    + " current value: $" + asset.getValue());

        }

    }

}
