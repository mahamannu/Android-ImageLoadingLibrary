package com.purpletealabs.imdb.BitMapUtils;

import android.content.Context;

import java.io.File;

public class FileCache {
    private File cacheDir;

    public FileCache(Context context){

        cacheDir = context.getCacheDir();

    }

    public File getFile(String url){
        //Identify images by hashcode or encode by URLEncoder.encode.
        String filename=String.valueOf(url.hashCode());//#NameAddress
        //#Address

        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        // list all files inside cache directory
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        //delete all cache directory files
        for(File f:files)
            f.delete();
    }
}
