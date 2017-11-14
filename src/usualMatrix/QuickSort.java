package usualMatrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class QuickSort extends Thread {
    private ArrayList<Integer> array;
    private int left, right;
    private static int threads;
    public QuickSort (ArrayList<Integer> array, int left, int right, int threads){
        super();
        this.threads = threads;
        this.left = left;
        this.right = right;
        this.array = array;
    }
    public QuickSort (ArrayList<Integer> array, int left, int right){
        super();
        this.array = array;
        this.left = left;
        this.right = right;
    }
    public void parallelQuickSort (){
        if (left >= right){
            return;
        }
        int idx = partition(array, left, right);
        LinkedList <QuickSort> threadsSort = new LinkedList<>();
        if (threads > 0){
            threadsSort.add(new QuickSort(array, left, idx - 1));
            threadsSort.getLast().start();
            threads--;
            if (threads > 0){
                threadsSort.add(new QuickSort(array, idx, right));
                threadsSort.getLast().start();
                threads--;
            }
            else quickSort(array, idx, right);
        }
        else quickSort(array, left, right);
        Iterator <QuickSort> it = threadsSort.listIterator();
        while (it.hasNext()){
            QuickSort tmp = it.next();
            try{
                if (tmp.isAlive()){
                    tmp.join();
                }
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
    }
    public static void quickSort(ArrayList<Integer> array, int left, int right){
        int idx = partition(array, left, right);
        if (left < idx - 1){
            quickSort(array, left, idx - 1);
        }
        if (idx < right){
            quickSort(array, idx, right);
        }
    }
    private static int partition(ArrayList<Integer> array, int left, int right){
        int i = left, j = right;
        int tmp;
        int pivot = array.get((left + right) / 2);
        while (i <= j){
            while (array.get(i) < pivot){
                i++;
            }
            while (array.get(j) > pivot){
                j--;
            }
            if (i <= j){
                tmp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }
    public void run(){
        try{
            parallelQuickSort();
        }catch(Exception e){
            e.getMessage();
        }
    }
}
