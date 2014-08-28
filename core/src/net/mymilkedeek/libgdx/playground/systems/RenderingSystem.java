package net.mymilkedeek.libgdx.playground.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import net.mymilkedeek.random.components.PositionComponent;

/**
 * 27/08/2014 - 10:34
 *
 * @author Michael Demey
 */
public class RenderingSystem extends EntitySystem {

    private ComponentMapper<PositionComponent> componentMapper;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;

    public RenderingSystem(OrthographicCamera camera, Color color) {
        super(Filter.allComponents(PositionComponent.class));

        this.camera = camera;
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
    }

    @Override
    public void initialize() {
        componentMapper = world.getMapper(PositionComponent.class);
    }

    @Override
    protected void processEntities(Array<Entity> entities) {
        // update camera and shape renderer
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.identity();

        // render each entity
        for (Entity entity : entities) {
            PositionComponent positionComponent = componentMapper.get(entity);
            shapeRenderer.circle(positionComponent.position.x, positionComponent.position.y, 10);
        }
    }
}