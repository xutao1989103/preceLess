package com.tianyu.jty.collector.service.outputs;

import com.tianyu.jty.collector.entity.Site;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by xtao on 2015/11/8.
 */
public class FileOutput extends AbstractOutput {

    private static final String FILE_DIR = "D:\\files";

    @Override
    public void output(Site site) throws Exception{
        if(site.getResult() != null){
            Map<String, Object> result = site.getResult();
            for(String key :result.keySet()){
                String path = FILE_DIR + File.separator + key + ".txt";
                newFile(path, result.get(key).toString());
            }
        }
    }

    public static void newFile(String filePathAndName, String fileContent) throws IOException {
        String filePath = filePathAndName;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) {
            myFilePath.createNewFile();
        }
        FileWriter resultFile = new FileWriter(myFilePath);
        PrintWriter myFile = new PrintWriter(resultFile);
        String strContent = fileContent;
        myFile.println(strContent);
        resultFile.close();

    }
}
