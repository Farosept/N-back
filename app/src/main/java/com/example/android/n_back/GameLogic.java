package com.example.android.n_back;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    private ArrayList<Integer> elements;
    private ArrayList<ElementInfo> resultElements;
    private infoResults results;
    private int countRights;
    private int N;

    public GameLogic(int N){
        elements = new ArrayList<Integer>();
        resultElements = new ArrayList<ElementInfo>();
        results = new infoResults();

        this.N=N;
    }

//    public void GeneralCheck(){
//        elements.add(random.nextInt(5)+1);
//        if(elements.size()<N+2) return;
//        for (int i=elements.size()-1;i<elements.size();i++) {
//                if (elements.get(i) == elements.get(i - (N + 1))) {
//                    ElementInfo addElement = new ElementInfo();
//                    addElement.setCount(addElement.getElement() + 1);
//                    addElement.setElement(elements.get(i));
//                    resultElements.add(addElement);
//
//                }
//        }
//    }
    public infoResults CheckOnButtonClick(int index){
        if(elements.size()<N+2) return results;
        if (elements.get(index) == elements.get(index - (N + 1))) {
            ElementInfo e = new ElementInfo();
            e.setElement(elements.get(index));
            int searchRes = resultElements.indexOf(e);
            if(searchRes!=-1){
                ElementInfo element = resultElements.get(searchRes);
                element.setCount(element.getCount()+1);
                countRights+=element.getCount();
                results.setRights(countRights);
                return results;
            }
            ElementInfo addElement = new ElementInfo();
            addElement.setCount(addElement.getCount() + 1);
            addElement.setElement(elements.get(index));
            resultElements.add(addElement);
            countRights += addElement.getCount();
            results.setRights(countRights);
            return results;
        }
        results.setBads(results.getBads()+1);
        return results;
    }
    public void SetRandomElement(int rand){
        elements.add(rand);
    }
    public int getLastIndex(){
        return elements.size()-1;
    }
    public int getAll(){
        return elements.size();
    }
}
