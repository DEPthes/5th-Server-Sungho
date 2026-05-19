package Java_Review;

public class Overloading_Exam {
    static class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        int add(int a, int b, int c) {
            return a + b + c;
        }

    }
    public static void main(String[] args) {
        Calculator cal = new Calculator();

        System.out.println(cal.add(10, 20));      // 30
        System.out.println(cal.add(10, 20, 30));  // 60
    }
}
