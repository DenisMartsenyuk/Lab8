package gui.adapted_components.listeners;

import gui.Circle;
import gui.adapted_components.VisualPanel;
import gui.windows.ShowWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

public class ClickCirclesListener implements MouseListener {

    public VisualPanel visualPanel;

    public ClickCirclesListener(VisualPanel visualPanel) {
        this.visualPanel = visualPanel;
    }

    public int isHit(double x, double y) {
        for (Map.Entry<Integer, Circle> entry : visualPanel.getCircles().entrySet()) {
            if(getDistance(entry.getValue().getX(), entry.getValue().getY(), x, y) < entry.getValue().getRadius()) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        System.out.println(x + " " + y);
        y = visualPanel.HEIGHT - y;

        if(isHit(x, y) != -1) {
            ShowWindow showWindow = new ShowWindow(visualPanel.context, isHit(x, y));
            showWindow.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        return;
    }
}
