package com.group19.javafxgame.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.group19.javafxgame.Constants;
import javafx.geometry.Point2D;

public class PlayerInteractionComponent extends Component {

    private double leftVelocity = 0;
    private double rightVelocity = 0;
    private double upVelocity = 0;
    private double downVelocity = 0;
    private PhysicsWorld physicsWorld;
    private String currDir = null;
    private String lastDir = null;

    public PlayerInteractionComponent(PhysicsWorld physicsWorld) {
        this.physicsWorld = physicsWorld;
    }

    private PhysicsComponent getPhysics() {
        return getEntity().getComponent(PhysicsComponent.class);
    }

    public void translateLeft() {
        translateLeft(Constants.getDefaultVelocity());
    }
    public void stopLeft() {
        translateLeft(0);
    }
    public void translateLeft(double velocity) {
        leftVelocity = velocity;
        updatePhysics();
    }

    public void translateRight() {
        translateRight(Constants.getDefaultVelocity());
    }
    public void stopRight() {
        translateRight(0);
    }
    public void translateRight(double velocity) {
        rightVelocity = velocity;
        updatePhysics();
    }

    public void translateUp() {
        translateUp(Constants.getDefaultVelocity());
    }
    public void stopUp() {
        translateUp(0);
    }
    public void translateUp(double velocity) {
        upVelocity = velocity;
        updatePhysics();
    }

    public void translateDown() {
        translateDown(Constants.getDefaultVelocity());
    }
    public void stopDown() {
        translateDown(0);
    }
    public void translateDown(double velocity) {
        downVelocity = velocity;
        updatePhysics();
    }

    public double getXVelocity() {
        return rightVelocity - leftVelocity;
    }

    public double getYVelocity() {
        return downVelocity - upVelocity;
    }

    public void setCurrDir(String newDir) {
        currDir = newDir;
    }
    public void setLastDir(String newDir) {
        lastDir = newDir;
    }

    public String getCurrDir() {
        return currDir;
    }
    public String getLastDir() {
        return lastDir;
    }











    public void setPosition(Point2D location) {
        double newX = physicsWorld.toMeters(location.getX());
        double newY = physicsWorld.toMeters(Constants.getScreenHeight())
                - physicsWorld.toMeters(location.getY());
        Vec2 vec = new Vec2(newX, newY);
        getPhysics().getBody().setTransform(vec, 0);
    }

    public void updatePhysics() {
        getPhysics().setLinearVelocity(getXVelocity(), getYVelocity());
    }

}
