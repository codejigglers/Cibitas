package com.example.varda.poolshop;

import java.util.ArrayList;

public class MyProperties {
    private static MyProperties mInstance= null;

    public int someValueIWantToKeep;
    public ArrayList<RequestDeliveryModel> data = new ArrayList<>();
    public ArrayList<RequestDeliveryModel> myData = new ArrayList<>();

    protected MyProperties(){
        RequestDeliveryModel r = new RequestDeliveryModel();
        RequestDeliveryModel r2 = new RequestDeliveryModel();

        r.setDate("19 April 2019");
        r.setLocation("Safeway, Santa Clara");
        r.setName("Saumya");
        r.setTime("11:00AM");

        r2.setDate("19 April 2019");
        r2.setLocation("Costco, Santa Clara");
        r2.setName("Pragyan");
        r2.setTime("11:00AM");

        data.add(r);
        data.add(r2);

        RequestDeliveryModel r3 = new RequestDeliveryModel();
        RequestDeliveryModel r4 = new RequestDeliveryModel();
    }

    public static synchronized MyProperties getInstance() {
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }
}
