package net.mymilkedeek.libgdx.playground;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.systems.event.BasicEventDeliverySystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import net.mymilkedeek.libgdx.playground.components.PositionComponent;
import net.mymilkedeek.libgdx.playground.events.systems.MoveCameraSystem;
import net.mymilkedeek.libgdx.playground.systems.RenderingSystem;
import net.mymilkedeek.libgdx.playground.systems.ResetPositionSystem;
import net.mymilkedeek.libgdx.playground.systems.input.InputSystem;

public class GameInstance extends Game {

    public static final int GAME_WIDTH = 400;
    public static final int GAME_HEIGHT = 400;

    private World world;
    private OrthographicCamera camera;

    @Override
    public void create() {
        // create a camera
        camera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);
        createWorld();
        // create a few of entities
        createEntity(200, 250, 80, 80);
        createEntity(200, 200, -80, 80);
        createEntity(200, 150, 80, -80);
        createEntity(200, 100, -80, -80);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.setDelta(Gdx.graphics.getDeltaTime());
        world.process();
    }

    @Override
    public void resize(int width, int height) {
        float centerX = width / 2.0f;
        float centerY = height / 2.0f;
        this.camera.position.set(centerX, centerY, 0);
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;
    }

    /**
     * Creates the world and adds entity systems to it.
     */
    private void createWorld() {
        world = new World();
        world.setEventDeliverySystem(new BasicEventDeliverySystem());
        //world.setSystem(new MovementSystem());
        world.setSystem(new ResetPositionSystem());
        world.setSystem(new RenderingSystem(camera, Color.WHITE));

        InputSystem inputSystem = new InputSystem();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputSystem);
        inputMultiplexer.addProcessor(new GestureDetector(inputSystem));
        Gdx.input.setInputProcessor(inputMultiplexer);
        world.setSystem(inputSystem);
        world.setSystem(new MoveCameraSystem(camera));

        world.initialize();
    }

    private void createEntity(float x, float y, float vx, float vy) {
        Entity e = world.createEntity();
        PositionComponent pc = world.createComponent(PositionComponent.class);
        pc.position.set(x, y);
        e.addComponent(pc);
        /*VelocityComponent vc = world.createComponent(VelocityComponent.class);
        vc.velocity.set(vx, vy);
        e.addComponent(vc);*/
        e.addToWorld();
    }
}
