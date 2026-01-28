/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.utils;

import java.io.InputStream;
import javax.swing.*;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderInput;

/**
 *
 * @author ely
 */
public class SvgUtils {

    public static ImageIcon loadSvg(String path, int width, int height) {
        try {
            InputStream is = SvgUtils.class.getResourceAsStream(path);
            if (is == null) {
                throw new IllegalArgumentException("SVG not found: " + path);
            }
            TranscoderInput input = new TranscoderInput(is);
                SvgUtils.class.getResourceAsStream(path);
            

            BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
            transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, (float) width);
            transcoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, (float) height);

            transcoder.transcode(input, null);
            

            return new ImageIcon(transcoder.getBufferedImage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

