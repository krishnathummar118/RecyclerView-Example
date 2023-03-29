package com.krishna.recyclerviewexample.utils;

import com.krishna.recyclerviewexample.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ConstantData {
    private static final int TYPE_HOR = 0;
    private static final int TYPE_VER = 1;

    public static List<DataModel> getDataList() {
        List<DataModel> dataList = new ArrayList<>();
        dataList.add(new DataModel("Beach Sunset", "A beautiful sunset over the ocean.", "https://images.pexels.com/photos/9716773/pexels-photo-9716773.jpeg"));
        dataList.add(new DataModel("Mountain Range", "A breathtaking view of the mountains.", "https://images.pexels.com/photos/14287/pexels-photo-14287.jpeg"));
        dataList.add(new DataModel("Sandy Beach", "A long stretch of sandy beach with crystal clear water.", "https://images.pexels.com/photos/14737533/pexels-photo-14737533.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        dataList.add(new DataModel("Cityscape", "A panoramic view of a bustling city.", "https://images.pexels.com/photos/1105666/pexels-photo-1105666.jpeg"));
        dataList.add(new DataModel("Autumn Leaves", "A close-up shot of colorful autumn leaves.", "https://images.pexels.com/photos/14230132/pexels-photo-14230132.jpeg"));
            dataList.add(new DataModel("Mountain Lake", "A serene mountain lake surrounded by trees.", "https://images.pexels.com/photos/14806763/pexels-photo-14806763.jpeg"));
        dataList.add(new DataModel("Flower Garden", "A vibrant flower garden in full bloom.", "https://images.pexels.com/photos/4210903/pexels-photo-4210903.jpeg"));
        dataList.add(new DataModel("Canyon", "A majestic canyon with rocky cliffs.", "https://images.pexels.com/photos/3112628/pexels-photo-3112628.jpeg"));
        dataList.add(new DataModel("Sunflowers", "A field of sunflowers with a bright blue sky in the background.", "https://images.pexels.com/photos/544554/pexels-photo-544554.jpeg"));
        dataList.add(new DataModel("Old Pier", "A weathered old pier stretching out into the sea.", "https://images.pexels.com/photos/5445984/pexels-photo-5445984.jpeg"));

        return dataList;
    }


}
