package com.group19.javafxgame.utils;

public class Point2I {

    private int x;
    private int y;

    public Point2I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point2I getLeft() {
        return new Point2I(x - 1, y);
    }

    public Point2I getRight() {
        return new Point2I(x + 1, y);
    }

    public Point2I getUp() {
        return new Point2I(x, y - 1);
    }

    public Point2I getDown() {
        return new Point2I(x, y + 1);
    }
}
