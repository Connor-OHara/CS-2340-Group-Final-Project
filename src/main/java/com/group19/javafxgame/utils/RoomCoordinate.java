package com.group19.javafxgame.utils;

public class RoomCoordinate {

    private int xGrid;
    private int yGrid;

    public RoomCoordinate(int xGrid, int yGrid) {
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }

    public int getyGrid() {
        return yGrid;
    }

    public int getxGrid() {
        return xGrid;
    }
}
