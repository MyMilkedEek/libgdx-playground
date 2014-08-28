package net.mymilkedeek.libgdx.playground.events.systems;

import com.artemis.systems.event.EventVoidSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import net.mymilkedeek.random.events.MoveCameraEvent;
import net.mymilkedeek.libgdx.playground.util.Globals;

/**
 * @author Michael Demey
 */
public class MoveCameraSystem extends EventVoidSystem<MoveCameraEvent> {

    private float currentRotation = 0f;
    private final OrthographicCamera camera;

    public MoveCameraSystem(OrthographicCamera camera) {
        super(MoveCameraEvent.class);

        this.camera = camera;
    }

    @Override
    protected void processEvent(MoveCameraEvent event) {
        Vector2 toGo = null;
        switch ( event.action ) {
            case MoveCameraEvent.NORTH:
                toGo = new Vector2(0, Globals.CAMERA_MOVE_STEP);
                //camera.translate(0,Globals.CAMERA_MOVE_STEP);
                break;
            case MoveCameraEvent.EAST:
                toGo = new Vector2(Globals.CAMERA_MOVE_STEP, 0);
                //camera.translate(Globals.CAMERA_MOVE_STEP,0);
                break;
            case MoveCameraEvent.SOUTH:
                toGo = new Vector2(0, Globals.CAMERA_MOVE_STEP * -1);
                //camera.translate(0,Globals.CAMERA_MOVE_STEP * -1);
                break;
            case MoveCameraEvent.WEST:
                toGo = new Vector2(Globals.CAMERA_MOVE_STEP * -1, 0);
                //camera.translate(Globals.CAMERA_MOVE_STEP * - 1, 0);
                break;
            case MoveCameraEvent.ZOOM_OUT:
                if ( camera.zoom < Globals.CAMERA_ZOOM_MAX ) {
                    camera.zoom += Globals.CAMERA_ZOOM_STEP;
                }
                break;
            case MoveCameraEvent.ZOOM_IN:
                if ( camera.zoom > Globals.CAMERA_ZOOM_MIN) {
                    camera.zoom -= Globals.CAMERA_ZOOM_STEP;
                }
                break;
            case MoveCameraEvent.ROTATE_LEFT:
                camera.rotate(Globals.CAMERA_ROTATE_STEP);
                currentRotation += Globals.CAMERA_ROTATE_STEP;
                break;
            case MoveCameraEvent.ROTATE_RIGHT:
                camera.rotate(360 - Globals.CAMERA_ROTATE_STEP);
                currentRotation += ( 360 - Globals.CAMERA_ROTATE_STEP );
                break;
        }

        if ( toGo != null ) {
            toGo.rotate(currentRotation);
            camera.translate(toGo);
        }

        if ( currentRotation >= 360 ) {
            currentRotation -= 360;
        }

        System.out.println("currentRotation = " + currentRotation);
    }
}