package gui.adapted_components;

import application.Context;
import gui.Circle;
import gui.adapted_components.listeners.ClickCirclesListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class VisualPanel extends JPanel {

    public static final int WIDTH = 710;
    public static final int HEIGHT = 308;

    private float alpha;
    private static final float STEP_ANIMATION = 0.005F;
    private static final int SLEEP_ANIMATION = 1;

    private Map<Integer, Circle> circles;
    public Context context;

    public VisualPanel(Context context) {
        super();
        this.context = context;
        alpha = 0F;
        circles = new HashMap<>();
        setBackground(new Color(255, 255, 255));
        addMouseListener(new ClickCirclesListener(this));
    }

    public void visualization(Map<Integer, Circle> circles) {
        fadeOut();
        this.circles = circles;
        fadeIn();
    }

    public void fadeOut() {
        for (float i = 1.0F; i >= 0.0; i = i - STEP_ANIMATION) {
            alpha = i;
            repaint();
            try {
                Thread.sleep(SLEEP_ANIMATION);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void fadeIn() {
        for (float i = 0.0F; i <= 1.0; i = i + STEP_ANIMATION) {
            alpha = i;
            repaint();
            try {
                Thread.sleep(SLEEP_ANIMATION);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Map<Integer, Circle> getCircles() {
        return circles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

        for (Map.Entry<Integer, Circle> entry : circles.entrySet()) {
            Circle circle = entry.getValue();
            g.setColor(circle.getColor());
            ((Graphics2D) g).fill(new Ellipse2D.Double(circle.getX() - circle.getRadius(), HEIGHT - (circle.getY() + circle.getRadius()), 2 * circle.getRadius(), 2 * circle.getRadius()));
        }
    }
}
