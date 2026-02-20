/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trip_manager_app.utils;

import java.io.File;

/**
 *
 * @author ely
 */
public class ImageClass {
    private File file;
    
    public ImageClass(File file){
        this.file = file;
        
    }
    
    public File getFile(){
        return file;
    }
}
