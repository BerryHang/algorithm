package handwriting.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        int times = 10000;
        int length = 10;
        int range = 100;

        for (int i = 0; i < times; i++) {

            Program[] arr = generator(length, range);
            int count1 = bestArrangeByGreedy(arr);
            int count2 = bestArrangeByForce(arr);

            if (count1 != count2) {
                System.out.printf("err");
            }

        }
    }

    public static int bestArrangeByForce(Program[] programs) {

        if (programs == null) {
            return 0;
        }
        if (programs.length == 1) {
            return 1;
        }

        return process(programs, 0, 0);
    }

    public static int process(Program[] programs, int done, int end) {

        if (programs.length == 0) {
            return done;
        }

        int max = done;

        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= end) {

                Program[] other = copyPrograms(programs, i);
                max = Math.max(max, process(other, done + 1, programs[i].end));
            }
        }

        return max;
    }

    public static Program[] copyPrograms(Program[] programs, int index) {
        Program[] newPrograms = new Program[programs.length - 1];

        int k = 0;

        for (int i = 0; i < programs.length; i++) {
            if (index != i) {
                newPrograms[k++] = programs[i];
            }
        }

        return newPrograms;
    }

    public static int bestArrangeByGreedy(Program[] programs) {

        if (programs == null) {
            return 0;
        }
        if (programs.length == 1) {
            return 1;
        }

        Arrays.sort(programs, new Compare());

        int start = 0;
        int count = 0;

        for (int i = 0; i < programs.length; i++) {

            Program program = programs[i];

            if (program.start >= start) {
                count++;
                start = program.end;
            }

        }

        return count;
    }

    public static class Compare implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {

            if (o1.end != o2.end) {
                return o1.end - o2.end;
            }

            return o1.start - o2.start;
        }
    }

    public static Program[] generator(int length, int range) {

        length = (int) (Math.random() * length + 1);

        Program[] programs = new Program[length];

        for (int i = 0; i < length; i++) {

            int start = (int) (Math.random() * range);

            int end = (int) (Math.random() * range);

            if (start == end) {
                programs[i] = new Program(start, end + 1);
            } else {
                programs[i] = new Program(Math.min(start, end), Math.max(start, end));
            }

        }

        return programs;
    }

}