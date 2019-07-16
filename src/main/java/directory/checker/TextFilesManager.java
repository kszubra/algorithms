package directory.checker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TextFilesManager {

    private FileWriter fileWriter;
    private BufferedReader fileRead;
    private List<String> allLines;


    public TextFilesManager(){

        FileWriter fileWriter = null;

    }

    public void saveToTxt(String dataToSave, String filePath){ //receives a string and saves to the filepath in given path

        try{
            fileWriter = new FileWriter(filePath);
            fileWriter.write(dataToSave);
        } catch(Exception e) {System.out.println("Exception: " + e.toString());}

            finally {

            if(fileWriter != null) {
                try{
                    fileWriter.close();
                } catch(Exception e) {System.out.println("Exception: " + e.toString());}
            }
        }

    }

    public void saveToTxt(List<String> list, String filePath){ //receives a string and saves to the filepath in given path

        try{
            fileWriter = new FileWriter(filePath);
            for (String string : list) {fileWriter.write(string);}

        } catch(Exception e) {System.out.println("Exception: " + e.toString());}

        finally {

            if(fileWriter != null) {
                try{
                    fileWriter.close();
                } catch(Exception e) {System.out.println("Exception: " + e.toString());}
            }
        }

    }

    public List<String> getTxt(String filePath){

        String fileLine;
        allLines = new ArrayList<>();
        try{
            fileRead = new BufferedReader(new FileReader(filePath));
            while((fileLine = fileRead.readLine()) != null){ // checks if there's a text in a line
                allLines.add(fileLine);
            }

        }catch(Exception e) {System.out.println("Exception: " + e.toString());}
            finally{

            if(fileRead != null){
                try{
                    fileRead.close();
                } catch(Exception e) {System.out.println("Exception: " + e.toString());}
            }


        }

        return allLines;
    }

    public void displayTxTFile(){

        for(String string : allLines) {System.out.println(string);}
    }

}
