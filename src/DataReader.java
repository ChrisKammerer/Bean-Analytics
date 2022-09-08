import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class DataReader {
    private Scanner beansDoc;
    private LinkedList beansList;

    public DataReader(String file) throws java.io.FileNotFoundException {
        beansDoc = new Scanner(new FileReader(file));
    }

    public LinkedList createBeanList() throws IOException {
        beansDoc.nextLine(); //skip first line
        beansList = new LinkedList();
        String line;
        while(beansDoc.hasNextLine()) {
            line = beansDoc.nextLine();
            String[] data = line.split(",");
            Double aspectRatio = Double.parseDouble(data[4]);
            Double roundness = Double.parseDouble(data[10]);
            beansList.add(new Bean(aspectRatio, roundness));
        }
        return beansList;
    }
}
