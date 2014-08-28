package net.mymilkedeek.libgdx.playground.systems;

import com.artemis.ComponentMapper;
import com.artemis.systems.event.EventVoidSystem;
import net.mymilkedeek.libgdx.playground.components.PositionComponent;
import net.mymilkedeek.libgdx.playground.events.ResetPositionEvent;

public class ResetPositionSystem extends EventVoidSystem<ResetPositionEvent> {

    private ComponentMapper<PositionComponent> pm;

    public ResetPositionSystem() {
        super(ResetPositionEvent.class);
    }

    public void initialize() {
        pm = world.getMapper(PositionComponent.class);
    }

    @Override
    public void processEvent(ResetPositionEvent event) {
        PositionComponent pc = pm.get(event.bouncer);
        pc.position.set(200, 200);
    }
}