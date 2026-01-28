/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.utils;

import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 *
 * @author ely
 */
public class BufferedImageTranscoder extends ImageTranscoder {

    private BufferedImage image;

    @Override
    public BufferedImage createImage(int w, int h) {
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
        this.image = img;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}

