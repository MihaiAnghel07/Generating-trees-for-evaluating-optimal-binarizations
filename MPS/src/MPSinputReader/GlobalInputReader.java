package MPSinputReader;

import MPSImages.GlobalImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GlobalInputReader extends Reader {
    public int nrFilesRead;
    public final File[] files;
    public GlobalInputReader () {
        File folder = new File(GLOBAL_DIRECTORY);
        files = folder.listFiles();
        nrFilesRead = 0;
    }
    public GlobalImage readImage() {
        if (nrFilesRead == files.length)
            return null;

        ArrayList<Double> thresholds = new ArrayList<Double>();
        ArrayList<Double> fMeasures = new ArrayList<Double>();

        try {
            String location = GLOBAL_DIRECTORY + getDelimitator() + files[nrFilesRead].getName();
            BufferedReader br = new BufferedReader(new FileReader(location));

            String line = br.readLine();
            String[] records = line.split(",");

            for (int i = 0; i < THRESHOLD_NR; i++) {
                thresholds.add(Double.parseDouble(records[i]));
            }

            line = br.readLine();
            records = line.split(",");

            for (int i = 0; i < FMEASURES_NR; i++) {
                fMeasures.add(Double.parseDouble(records[i]));
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        GlobalImage image = new GlobalImage(files[nrFilesRead].getName(), thresholds, fMeasures);
        nrFilesRead += 1;
        return image;
    }
}