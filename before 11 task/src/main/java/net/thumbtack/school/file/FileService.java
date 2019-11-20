package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;

public class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) {

        try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
            for (byte b : array) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) {

        writeByteArrayToBinaryFile(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) {

        byte[] array = null;
        try (FileInputStream fis = new FileInputStream(new File(fileName))) {
            array = new byte[fis.available()];
            for (int i = 0; i < array.length; i++) {
                array[i] = (byte) fis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static byte[] readByteArrayFromBinaryFile(File file) {

        return readByteArrayFromBinaryFile(file.getPath());
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {

        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write(array);
            bytes = baos.toByteArray();
        }
        byte[] bytes1 = new byte[bytes.length / 2];
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            for (int i = 0; i < bytes1.length; i++) {
                bytes1[i] = (byte) bais.read();
                bais.skip(1);
            }
        }
        return bytes1;
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
            for (byte b : array) {
                bos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) {

        writeByteArrayToBinaryFileBuffered(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) {

        byte[] array = null;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)))) {
            array = new byte[bis.available()];
            for (int i = 0; i < array.length; i++) {
                array[i] = (byte) bis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) {

        return readByteArrayFromBinaryFileBuffered(file.getPath());
    }

    public static void writeRectangleToBinaryFile(File file, Rectangle rect) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(file.getPath())))) {
            dos.writeInt(rect.getTopLeft().getX());
            dos.writeInt(rect.getTopLeft().getY());
            dos.writeInt(rect.getBottomRight().getX());
            dos.writeInt(rect.getBottomRight().getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromBinaryFile(File file) throws ColorException {

        Rectangle rectangle = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(new File(file.getPath())))) {
            rectangle = new Rectangle(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt(), Color.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rectangle;
    }

    public static void writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(file.getPath())))) {
            for (Rectangle rect : rects) {
                dos.writeInt(rect.getTopLeft().getX());
                dos.writeInt(rect.getTopLeft().getY());
                dos.writeInt(rect.getBottomRight().getX());
                dos.writeInt(rect.getBottomRight().getY());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle[] readRectangleArrayFromBinaryFileReverse(File file) throws ColorException {

        int count = 1;
        Rectangle rectangles[] = new Rectangle[(int) file.length() / 16];
        try (RandomAccessFile raf = new RandomAccessFile(new File(file.getPath()), "rw")) {
            for (int i = 0; i < file.length() / 16; i++) {
                raf.seek(file.length() - 16 * count);
                rectangles[i] = new Rectangle(raf.readInt(), raf.readInt(), raf.readInt(), raf.readInt(), Color.RED);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rectangles;
    }

    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) {

        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file.getPath()))))) {
            br.write(rect.getTopLeft().getX() + " ");
            br.write(rect.getTopLeft().getY() + " ");
            br.write(rect.getBottomRight().getX() + " ");
            br.write(rect.getBottomRight().getY() + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws ColorException {

        Rectangle rec = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.getPath()))))) {
            String str = br.readLine();
            String[] strArr = str.trim().split("\\s+");
            rec = new Rectangle(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]), Integer.parseInt(strArr[2]), Integer.parseInt(strArr[3]), Color.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rec;
    }

    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) {

        try (PrintStream ps = new PrintStream(new File(file.getPath()))) {
            ps.println(rect.getTopLeft().getX());
            ps.println(rect.getTopLeft().getY());
            ps.println(rect.getBottomRight().getX());
            ps.println(rect.getBottomRight().getY());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Rectangle readRectangleFromTextFileFourLines(File file) throws ColorException {

        Rectangle rec = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.getPath()))))) {
            rec = new Rectangle(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Color.RED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rec;
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) {

        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file.getPath())), "UTF-8"))) {
            br.write(trainee.getFirstName() + " ");
            br.write(trainee.getLastName() + " ");
            br.write(trainee.getRating() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws TrainingException {

        Trainee trainee = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.getPath()))))) {
            String str = br.readLine();
            String[] strArr = str.trim().split("\\s+");
            trainee = new Trainee(strArr[0], strArr[1], Integer.parseInt(strArr[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file.getPath())), "utf8"))) {
            bw.write(trainee.getFirstName() + "");
            bw.newLine();
            bw.write(trainee.getLastName() + "");
            bw.newLine();
            bw.write(trainee.getRating() + "");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee readTraineeFromTextFileThreeLines(File file) throws TrainingException {

        Trainee trainee = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.getPath())), "utf-8"))) {
            trainee = new Trainee(br.readLine(), br.readLine(), Integer.parseInt(br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())))) {
            oos.writeObject(trainee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws TrainingException {

        Trainee trainee = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(file.getPath())))) {
            trainee = (Trainee) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return trainee;
    }

    public static String serializeTraineeToJsonString(Trainee trainee) {

        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {

        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) {

        Gson gson = new Gson();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file.getPath())))) {
            gson.toJson(trainee, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) {
        Trainee trainee = null;
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(file.getPath())))) {
            trainee = gson.fromJson(br, Trainee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainee;
    }
}
