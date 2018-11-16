package analyzer;

import sorters.Sorter;

import java.util.ArrayList;
import java.util.HashMap;

// TODO: 12.11.2018  Create single data warehouse
public class Data {

    private static Data instance;
    private HashMap<Sorter, ArrayList<ArrayList<Double>>> data = new HashMap<>();

    private Data() {}

    public static Data getInstance(){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public HashMap<Sorter, ArrayList<ArrayList<Double>>> getData() {
        return data;
    }

    public void setData( Sorter sorter, ArrayList<ArrayList<Double>> record) {
        this.data.put(sorter,record);
    }
}
