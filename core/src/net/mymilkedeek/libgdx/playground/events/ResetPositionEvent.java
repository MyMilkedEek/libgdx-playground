package net.mymilkedeek.libgdx.playground.events;

import com.artemis.Entity;
import com.artemis.systems.event.SystemEvent;

/**
 * @author Michael Demey
 */
public class ResetPositionEvent extends SystemEvent {

    public Entity bouncer;

    @Override
    protected void resetForPooling() {
        bouncer = null;
    }
}