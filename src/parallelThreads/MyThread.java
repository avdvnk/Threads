package parallelThreads;

import usualMatrix.UsualMatrix;

public class MyThread extends Thread {
    UsualMatrix result, input1, input2;
    int from, to;
    public MyThread (UsualMatrix result, UsualMatrix input1, UsualMatrix input2, int from, int to){
        super();
        this.result = result;
        this.input1 = input1;
        this.input2 = input2;
        this.from = from;
        this.to = to;
    }
    public void run (){
        for (int i = from; i < to; i++) {
            for (int j = 0; j < input2.getColSize(); j++) {
                for (int k = 0; k < input1.getColSize(); k++) {
                    result.setElement(i, j, result.getElement(i, j) + input1.getElement(i, k) * input2.getElement(k, j));
                }
            }
        }
    }
}
