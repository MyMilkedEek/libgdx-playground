package net.mymilkedeek.libgdx.playground.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Michael Demey
 */
public class VelocityComponent implements Component {

    public final Vector2 velocity;

    public VelocityComponent() {
        this(0, 0);
    }

    public VelocityComponent(float x, float y) {
        velocity = new Vector2(x, y);
    }

    @Override
    public void reset() {
        velocity.set(0, 0);
    }
}