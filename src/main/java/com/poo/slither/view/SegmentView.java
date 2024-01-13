package com.poo.slither.view;

import com.poo.slither.model.Segment;
import javafx.scene.canvas.GraphicsContext;

import static com.poo.slither.view.GameView.*;

public class SegmentView {
    public static final double SEGMENT_SIZE = 20; // Le rayon
    private final Segment segment;
    public SegmentView(Segment segment) {
        this.segment = segment;
    }
    public void dessineSegment(GraphicsContext gc, double offsetX, double offsetY) {
        gc.setFill(segment.getColor());

        double x = (segment.getX() + offsetX + WIDTH) % WIDTH;
        double y = (segment.getY() + offsetY + HEIGHT) % HEIGHT;

        gc.fillOval(x, y, SEGMENT_SIZE, SEGMENT_SIZE);
    }
}
