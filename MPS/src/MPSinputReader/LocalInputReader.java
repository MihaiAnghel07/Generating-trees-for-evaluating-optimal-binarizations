package MPSinputReader;

import MPSImages.LocalImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LocalInputReader extends Reader {
    public int nrFilesRead;
    public final File[] files;
    public LocalInputReader() {
        File folder = new File(LOCAL_DIRECTORY);
        files = folder.listFiles();
        nrFilesRead = 0;
    }
    public LocalImage readImage() {
        if (nrFilesRead == files.length)
            return null;

        ArrayList<Double> pixels = new ArrayList<>();
        ArrayList<Double> pixelClass = new ArrayList<>();
        ArrayList<Double[]> thresholds = new ArrayList<>();

        try {
            String line, location = LOCAL_DIRECTORY + getDelimitator() + files[nrFilesRead].getName();
            BufferedReader br = new BufferedReader(new FileReader(location));

            while ((line = br.readLine()) != null) {
                String[] records = line.split(",");

                double el = Double.parseDouble(records[0]);
                if (el >= MAX_THRESHOLD)
                    continue;

                pixels.add(el);
                pixelClass.add(Double.parseDouble(records[1]));

                Double[] thresholdsLine = new Double[THRESHOLD_LOCAL_NR];
                for (int i = 0; i < THRESHOLD_LOCAL_NR; i++) {
                    el = Double.parseDouble(records[i + 2]);

                    if (el >= MAX_THRESHOLD) {
                        pixels.remove(pixels.size() - 1);
                        pixelClass.remove(pixelClass.size() - 1);
                        continue;
                    }
                    thresholdsLine[i] = el;
                }
                thresholds.add(thresholdsLine);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LocalImage image = new LocalImage(files[nrFilesRead].getName(), pixels, pixelClass, thresholds);
        nrFilesRead += 1;
        return image;
    }
}