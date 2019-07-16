package directory.checker;

public class DirectoryCheckerMainRunner {
    public static void main(String[] args) {

        DirectoryCheckResult testResult = new DirectoryCheckResult(new DirectoryChecker("D:\\RV7\\Stuff"));
        testResult.displayResults();
        TextFilesManager tsl = new TextFilesManager();
        tsl.saveToTxt(testResult.filesInfoToStringList(), "D:\\RV7\\test.txt");
        tsl.getTxt("D:\\RV7\\test.txt");
        tsl.displayTxTFile();
    }
}
