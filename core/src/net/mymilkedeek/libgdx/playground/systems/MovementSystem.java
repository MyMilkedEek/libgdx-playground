package net.mymilkedeek.libgdx.playground.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import net.mymilkedeek.libgdx.playground.components.PositionComponent;
import net.mymilkedeek.libgdx.playground.components.VelocityComponent;

/**
 * @author Michael Demey
 */
public class MovementSystem extends EntityProcessingSystem {

    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<VelocityComponent> vm;

    @SuppressWarnings( "unchecked" )
    public MovementSystem() {
        super(Filter.allComponents(
                PositionComponent.class, VelocityComponent.class));
    }

    @Override
    public void initialize() {
        pm = world.getMapper(PositionComponent.class);
        vm = world.getMapper(VelocityComponent.class);
    }

    @Override
    protected void process(Entity e) {
        // Get the components from the entity using component mappers.
        PositionComponent positionComponent = pm.get(e);
        VelocityComponent velocityComponent = vm.get(e);
        Vector2 position = positionComponent.position;
        // Update the position.
        position.x += velocityComponent.velocity.x * world.getDelta();
        position.y += velocityComponent.velocity.y * world.getDelta();
    }
}