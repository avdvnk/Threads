import parallelThreads.*;
import usualMatrix.QuickSort;
import usualMatrix.UsualMatrix;

import java.util.ArrayList;

//Быстрая сортировка в многопотчном режиме при том, чтобы размер массива выиграла реализацию
//у однопоточного метода
//
public class Main {
    public static void main(String[] args) {
        ParallelMatrixProduct pm = new ParallelMatrixProduct(10);
        UsualMatrix matrix = new UsualMatrix(1000, 1000);
        UsualMatrix matrix1 = new UsualMatrix(1000, 1000);
        UsualMatrix res;
        long tm, finish1, finish2;
        for (int i = 0; i < matrix.getRowSize(); i++) {
            for (int j = 0; j < matrix.getColSize(); j++) {
                matrix.setElement(i, j, (int)(Math.random() * 10));
                matrix1.setElement(i, j, (int)(Math.random() * 10));
            }
        }
        /*for (int i = 0; i < matrix1.getRowSize(); i++) {
            for (int j = 0; j < matrix1.getColSize(); j++) {
                matrix1.setElement(i, j, (int)(Math.random() * 10));
            }
        }
        /*matrix.setElement(0,0, 1);
        matrix.setElement(0, 1,2);
        matrix.setElement(0,2,3);
        matrix.setElement(1,0,0);
        matrix.setElement(1,1,4);
        matrix.setElement(1,2,5);
        matrix.setElement(2,0,2);
        matrix.setElement(2,1,1);
        matrix.setElement(2,2,2);
        matrix1.setElement(0,0, 0);
        matrix1.setElement(0, 1,1);
        matrix1.setElement(0,2,2);
        matrix1.setElement(1,0,4);
        matrix1.setElement(1,1,1);
        matrix1.setElement(1,2,1);
        matrix1.setElement(2,0,3);
        matrix1.setElement(2,1,2);
        matrix1.setElement(2,2,1);
        System.out.println(matrix.toString());
        System.out.println(matrix1.toString());*/
        tm = System.currentTimeMillis();
        //System.out.println(tm);
        res = matrix.product(matrix1);
        //System.out.println(res.toString());
        finish1 = System.currentTimeMillis() - tm;
        tm = System.currentTimeMillis();
        res = pm.productMatrix(matrix, matrix1);
        //System.out.println(res.toString());
        finish2 = System.currentTimeMillis() - tm;
        System.out.println("Usual product: " + finish1);
        System.out.println("Multithreaded product: " + finish2);
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            int value = (int)(Math.random() * 101);
            arrayList.add(value);
            array.add(value);
        }
        //System.out.println(arrayList.toString());
        tm = System.currentTimeMillis();
        QuickSort qs1 = new QuickSort(array, 0, array.size() - 1);
        qs1.quickSort(array, 0, array.size() - 1);
        finish1 = System.currentTimeMillis() - tm;
        QuickSort qs = new QuickSort(arrayList, 0, arrayList.size() - 1, 2);
        tm = System.currentTimeMillis();
        qs.parallelQuickSort();
        finish2 = System.currentTimeMillis() - tm;
        System.out.println("QuickSort time: " + finish1);
        System.out.println("Parallel quickSort time: " + finish2);
        //System.out.println(array.toString());
        //System.out.println(arrayList.toString());
    }
}
