package com.group19.javafxgame.component;



import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.CollisionHandler;


public class ExplosionCollisionHandler extends CollisionHandler {
    public ExplosionCollisionHandler(Object a, Object b) {
        super(a, b);
    }

    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
        a.getComponent(MonsterComponent.class)
                .subtractHealth(b.getComponent(ExplosionComponent.class).getDamage());
        System.out.println(a.getComponent(MonsterComponent.class).getHealth());

        if (a.getComponent(MonsterComponent.class).getHealth() <= 0) {
            a.removeComponent(IrremovableComponent.class);
            a.removeFromWorld();
        }
    }
}
