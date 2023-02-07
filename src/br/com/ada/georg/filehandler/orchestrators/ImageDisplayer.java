package br.com.ada.georg.filehandler.orchestrators;

import javax.swing.*;
import java.awt.*;

public class ImageDisplayer {

    private JFrame frame;
    private JLabel label;

    public ImageDisplayer() {
        frame = new JFrame();
        label = new JLabel();
    }

    public void displayImage(Image image) {
        if (image == null) {
            System.out.println("Error: Image not found");
            return;
        }
        frame.setSize(image.getWidth(null), image.getHeight(null));
        label.setIcon(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
}
