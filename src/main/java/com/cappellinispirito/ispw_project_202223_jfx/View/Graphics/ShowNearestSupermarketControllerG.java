package com.cappellinispirito.ispw_project_202223_jfx.View.Graphics;

import com.cappellinispirito.ispw_project_202223_jfx.View.ShowNearestSupermarketsCustomerView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ShowNearestSupermarketControllerG {

    public Label market1;
    public Label market2;
    public Label market3;
    public Label market4;
    public Label market5;
    public List<Label> marketAddressesList = new ArrayList<>();

    public Label marketDist1;
    public Label marketDist2;
    public Label marketDist3;
    public Label marketDist4;
    public Label marketDist5;
    public List<Label> marketDistancesList = new ArrayList<>();

    public TextField search_text;

    private List<String> supermarketNames = new ArrayList<>();
    private List<String> supermarketAddresses = new ArrayList<>();
    private List<Float> supermarketDistances = new ArrayList<>();

    ShowNearestSupermarketsCustomerView view;

    public ShowNearestSupermarketControllerG(){}

    public void onSearchSupermarketsClicked() throws Exception {
        marketAddressesList.add(market1);
        marketAddressesList.add(market2);
        marketAddressesList.add(market3);
        marketAddressesList.add(market4);
        marketAddressesList.add(market5);

        marketDistancesList.add(marketDist1);
        marketDistancesList.add(marketDist2);
        marketDistancesList.add(marketDist3);
        marketDistancesList.add(marketDist4);
        marketDistancesList.add(marketDist5);

        view = new ShowNearestSupermarketsCustomerView();
        String search = search_text.getText();
        view.showNearestFrom(search);
        supermarketNames = view.getSupermarketsNames();
        supermarketAddresses = view.getSupermarketsAddresses();
        supermarketDistances = view.getSupermarketsDistances();
        int i;
        for(i=0;i<supermarketNames.size();i++){
            marketAddressesList.get(i).setText(String.format("%s, %s", supermarketNames.get(i),supermarketAddresses.get(i)));
            marketDistancesList.get(i).setText(String.valueOf(supermarketDistances.get(i)));
        }
    }
}
