package Java_Review;
import java.io.*;
public class FileStream_Exam {
    public static void main(String[] args) {
        String text = "Input Output Exam";
        int data;
        try {
            // 1. 파일에 데이터 쓰기 (출력 스트림)
            FileOutputStream fos = new FileOutputStream("./example.txt");
            fos.write(text.getBytes());
            fos.close();

            // 2. 파일에서 데이터 읽기 (입력 스트림)
            FileInputStream fis = new FileInputStream("./example.txt");


            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
