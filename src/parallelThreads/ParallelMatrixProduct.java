package parallelThreads;

import usualMatrix.UsualMatrix;

import java.util.Iterator;
import java.util.LinkedList;


public class ParallelMatrixProduct {
    private int threadVal = 1;
    private LinkedList<MyThread> threads;
    public ParallelMatrixProduct (int value){
        if (value > 0){
            threadVal = value;
        }
        threads = new LinkedList();
    }
    public UsualMatrix productMatrix (UsualMatrix input1, UsualMatrix input2){
        if (threadVal > input1.getRowSize()) threadVal = input1.getRowSize();
        UsualMatrix result = new UsualMatrix(input1.getRowSize(), input2.getColSize());
        int parallel = input1.getRowSize() / threadVal;
        int count = 0;
        for (int i = 0; i < threadVal; i++) {
            if (i + 1 == threadVal){
                threads.add(new MyThread(result, input1, input2, count, input1.getRowSize()));
                count = input1.getRowSize();
                i++;
            }
            else {
                threads.add(new MyThread(result, input1, input2, count, count + parallel));
                count += parallel;
            }
            threads.getLast().start();
        }
        Iterator <MyThread> it = threads.listIterator();
        while (it.hasNext()){
            MyThread tmp = it.next();
            try{
                if (tmp.isAlive()){
                    tmp.join();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
