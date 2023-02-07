package br.com.ada.georg.filehandler.orchestrators;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageOrchestrator {
    private ImageDisplayer imageDisplayer;
    private BufferedImage bufferedImage;
    private URLHandler urlHandler;

    public ImageOrchestrator() {
        this.imageDisplayer = new ImageDisplayer();
        this.urlHandler = new URLHandler();
        this.bufferedImage = null;
    }

    public void displayImage() {
        imageDisplayer.displayImage(bufferedImage);
    }

    public boolean recoverImageFromURL(String url) {
        if (urlHandler.assignURL(url)) {
            return convertURLToBufferImage();
        }
        return false;
    }

    public boolean convertURLToBufferImage() {
        try {
            bufferedImage = ImageIO.read(urlHandler.getUrl());
            return true;
        } catch (IOException e) {
            System.out.println("No image found");
            return false;
        }
    }

    public void convertFileToBufferImage(File file) {
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("No image found");
        }
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
