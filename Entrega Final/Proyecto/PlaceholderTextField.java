package Proyecto;

import javax.swing.*;
import java.awt.*;

public class PlaceholderTextField extends JTextField {

    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        super(15); // igual que tu método original
        this.placeholder = placeholder;
        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        setOpaque(true);
        setBackground(new Color(72, 207, 174));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && placeholder != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(30, 30, 30));
            g2.setFont(getFont().deriveFont(Font.ITALIC));

            // Centrar verticalmente el texto
            FontMetrics fm = g2.getFontMetrics();
            int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            g2.drawString(placeholder, 8, textY);
            g2.dispose();
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    public String getPlaceholder() {

    	return placeholder;
    	
}
}
