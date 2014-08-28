package net.mymilkedeek.libgdx.playground.systems.input;

import com.artemis.Entity;
import com.artemis.Filter;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import net.mymilkedeek.libgdx.playground.components.PositionComponent;
import net.mymilkedeek.libgdx.playground.components.VelocityComponent;
import net.mymilkedeek.libgdx.playground.events.MoveCameraEvent;
import net.mymilkedeek.libgdx.playground.events.ResetPositionEvent;

/**
 * @author Michael Demey
 */
public class InputSystem extends EntityProcessingSystem implements InputProcessor, GestureDetector.GestureListener {

    private boolean moving = false;
    private boolean reset = false;

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
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
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

    /* CAMERA OPERATIONS */
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

    private void zoomCamera(boolean out) {
        MoveCameraEvent event = world.createEvent(MoveCameraEvent.class);
        if (out) {
            event.action = MoveCameraEvent.ZOOM_OUT;
        } else {
            event.action = MoveCameraEvent.ZOOM_IN;
        }
        world.postEvent(this, event);
    }

    @Override
    public boolean keyUp(int keyCode) {
        reset = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float v, float v2) {
        zoomCamera(v > v2);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }


}