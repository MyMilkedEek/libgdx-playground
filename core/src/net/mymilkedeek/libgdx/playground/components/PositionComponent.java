package net.mymilkedeek.libgdx.playground.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Michael Demey
 */
public class PositionComponent implements Component {

    public final Vector2 position;

    public PositionComponent() {
        this(0, 0);
    }

    public PositionComponent(float x, float y) {
        position = new Vector2(x, y);
    }

    @Override
    public void reset() {
        position.set(0, 0);
    }
}