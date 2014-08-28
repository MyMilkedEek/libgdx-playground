package net.mymilkedeek.libgdx.playground.events;

import com.artemis.systems.event.SystemEvent;

/**
 * @author Michael Demey
 */
public class MoveCameraEvent extends SystemEvent {

    public static final int NOTHING = -1;
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    public static final int ZOOM_OUT = 4;
    public static final int ZOOM_IN = 5;
    public static final int ROTATE_LEFT = 6;
    public static final int ROTATE_RIGHT = 7;

    public int action;

    @Override
    protected void resetForPooling() {
        // empty body
    }
}