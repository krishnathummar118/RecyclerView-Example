package com.krishna.recyclerviewexample.presenter;

import com.krishna.recyclerviewexample.interfaces.DataView;
import com.krishna.recyclerviewexample.model.DataModel;
import com.krishna.recyclerviewexample.utils.ConstantData;

import java.util.ArrayList;
import java.util.List;



public class DataPresenter {
    private DataView view;
    private List<DataModel> dataModel;

    public DataPresenter(DataView view) {
        this.view = view;
    }

    public void loadUser() {
        // fetch user data from repository or API
        dataModel = new ArrayList<>();
        dataModel.addAll(ConstantData.getDataList());
        view.showData(dataModel);
    }
}
