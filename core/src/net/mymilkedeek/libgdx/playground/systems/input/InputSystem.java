package net.mymilkedeek.libgdx.playground.systems.input;

import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import net.mymilkedeek.random.components.PositionComponent;
import net.mymilkedeek.random.components.VelocityComponent;
import net.mymilkedeek.random.events.MoveCameraEvent;
import net.mymilkedeek.random.events.ResetPositionEvent;

/**
 * @author Michael Demey
 */
public class InputSystem extends EntityProcessingSystem implements InputProcessor, GestureDetector.GestureListener {

    private boolean moving = false;
    private boolean reset = false;
    private int lastDirection;

    public InputSystem() {
        super(Filter.allComponents(PositionComponent.class, VelocityComponent.class));
    }

    @Override
    protected void process(Entity e) {
        if (reset) {
            ResetPositionEvent event = world.createEvent(ResetPositionEvent.class);
            event.bouncer = e;
            world.postEvent(this, event);
        }
    }

    @Override
    public boolean keyDown(int i) {
        switch (i) {
            case Input.Keys.UP:
            case Input.Keys.Z:
                moveCamera(MoveCameraEvent.NORTH);
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                moveCamera(MoveCameraEvent.EAST);
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                moveCamera(MoveCameraEvent.SOUTH);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.Q:
                moveCamera(MoveCameraEvent.WEST);
                break;
            case Input.Keys.F12:
                reset = true;
                break;
            case Input.Keys.E:
                rotateCamera(MoveCameraEvent.ROTATE_LEFT);
                break;
            case Input.Keys.A:
                rotateCamera(MoveCameraEvent.ROTATE_RIGHT);
                break;
        }

        return moving || reset;
    }

    private void rotateCamera(int rotation) {
        MoveCameraEvent event = world.createEvent(MoveCameraEvent.class);
        event.action = rotation;
        world.postEvent(this, event);
    }

    private void moveCamera(int direction) {
        MoveCameraEvent event = world.createEvent(MoveCameraEvent.class);
        event.action = direction;
        world.postEvent(this, event);
    }

    @Override
    public boolean keyUp(int i) {
        reset = false;
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }


    /* CAMERA OPERATIONS */
    @Override
    public boolean scrolled(int i) {
        MoveCameraEvent event = world.createEvent(MoveCameraEvent.class);
        if (i == 1) {
            event.action = MoveCameraEvent.ZOOM_OUT;
        } else {
            event.action = MoveCameraEvent.ZOOM_IN;
        }
        world.postEvent(this, event);
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        MoveCameraEvent event = world.createEvent(MoveCameraEvent.class);
        if (v > v2) {
            event.action = MoveCameraEvent.ZOOM_OUT;
        } else {
            event.action = MoveCameraEvent.ZOOM_IN;
        }
        world.postEvent(this, event);
        return false;
    }

    @Override
    public boolean touchDown(float v, float v2, int i, int i2) {
        System.out.println("touchdown");
        return false;
    }

    @Override
    public boolean tap(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean longPress(float v, float v2) {
        return false;
    }

    @Override
    public boolean fling(float v, float v2, int i) {
        return false;
    }

    @Override
    public boolean pan(float v, float v2, float v3, float v4) {
        return false;
    }

    @Override
    public boolean panStop(float v, float v2, int i, int i2) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
        return false;
    }
}