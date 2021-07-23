package com.group19.javafxgame.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

/**
 * A generic projectile component
 */
public class ProjectileComp extends Component {
    private int damage;
    public ProjectileComp(int damage) {
        super();
        this.entity = new Entity();
        this.damage = damage;
    }
    public ProjectileComp() {
        this(5);
    }

    public int getDamage() {
        return damage;
    }
}
