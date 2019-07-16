package directory.checker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryCheckResult {

    private final List<File> listOfFilesInDirectory;
    private final List<File> listOfFoldedrsInDirectory;
    private final Date dateOfCreation;
    private final int foldersNumber;


    public DirectoryCheckResult(DirectoryChecker inputDirectoryChecker){ //constructor receives list of files from the DirectoryChecker object and saves it as this. field

        this.listOfFilesInDirectory = inputDirectoryChecker.getFileList();
        this.listOfFoldedrsInDirectory = inputDirectoryChecker.getFolderList();
        this.dateOfCreation = new Date();
        this.foldersNumber = inputDirectoryChecker.getFolderNumber();
    }

    public int getNumberOfFiles() {return listOfFilesInDirectory.size();}

    public void displayResults(){

        System.out.println("In: " + this.foldersNumber + " folders were found: " + this.getNumberOfFiles() + " files. \r\n");

        for (File file : listOfFilesInDirectory) {
            SimpleDateFormat simpleModDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            System.out.println("FILE NAME: " + file.getName() + " SIZE: " + file.length() + " LAST MODIFIED: " + simpleModDate.format(file.lastModified()) );
        }
    }

    public String fileInfoToString(File file){ // THINK HOT TO CONVERT WHAT INFO

        SimpleDateFormat simpleModDate = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String singleFileInfo = file.getName() + " " + String.valueOf(file.length()) + " bytes Last modified: " + simpleModDate.format(file.lastModified()) + " " + file.getAbsolutePath() + "\r\n";

        return singleFileInfo;
    }

    public List<String> filesInfoToStringList(){

        List<String> listedStringFileInfo = new ArrayList<>();
        for (File file : listOfFilesInDirectory){

            listedStringFileInfo.add(fileInfoToString(file));
        }

        return listedStringFileInfo;

    }

}
