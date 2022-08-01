import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static final String PATHNAME = "C:/JavaHome/savegames/";

    public static void main(String[] args) throws IOException {

        openZip(PATHNAME, "zip");
        openProgress(PATHNAME + "save1.data");
    }

    private static void openProgress(String fileWay) {

        try (FileInputStream fis = new FileInputStream(fileWay);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            GameProgress gameProgress = (GameProgress) ois.readObject();
            System.out.println(gameProgress);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void openZip(String zipWay, String nameFile) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipWay + nameFile + ".zip"));
        ZipEntry entry;
        String name;

        while ((entry = zis.getNextEntry()) != null) {
            name = entry.getName();
            FileOutputStream fos = new FileOutputStream(zipWay + name);
            for (int c = zis.read(); c != -1; c = zis.read()) {
                fos.write(c);
            }
            fos.flush();
            fos.close();
            zis.closeEntry();
        }
        zis.close();
    }
}
