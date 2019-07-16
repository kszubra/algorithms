package directory.checker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryChecker {

    private int folderNumber; // total number of sub-folders in the directory
    private List<File> fileList; // total list of files
    private List<File> folderList; // total list of sub-folders


    public DirectoryChecker(String startingDirectory){
        this.folderNumber = 0;
        this.fileList = new ArrayList<>();
        this.folderList = new ArrayList<>();

        checkDirectory(startingDirectory);

    }

    public int getFolderNumber(){return this.folderNumber;}
    public List<File> getFileList() {return this.fileList;}
    public List<File> getFolderList() {return this.folderList;}

    public void checkDirectory(String directory){ // makes a list of files in given directory

        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileList.add(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory()) {
                this.folderNumber++;
                folderList.add(listOfFiles[i]);
                checkDirectory(listOfFiles[i].getAbsolutePath());
            }
        }

    }

}
