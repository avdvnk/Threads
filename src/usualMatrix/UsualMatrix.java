package usualMatrix;

public class UsualMatrix {
    protected int [][] m;
    protected int colSize;
    protected int rowSize;
    public UsualMatrix (int row_size, int col_size){
        m = new int[row_size][col_size];
        this.rowSize = row_size;
        this.colSize = col_size;
        for (int i = 0; i < row_size; i++){
            for (int j = 0; j < col_size ; j++) {
                this.setElement(i, j, 0);
                //m[i][j] = 0;
            }
        }
    }
    public int getRowSize(){return this.rowSize;}
    public int getColSize() {return this.colSize;}
    public UsualMatrix sum(UsualMatrix input){
        UsualMatrix res = new UsualMatrix(this.getRowSize(), this.getColSize());
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                res.setElement(i, j, this.getElement(i, j) + input.getElement(i, j));
            }
        }
        return res;
    }
    public UsualMatrix sub(UsualMatrix input){
        UsualMatrix res = new UsualMatrix(this.rowSize, this.colSize);
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                res.setElement(i, j, this.getElement(i, j) - input.getElement(i, j));
                //res.m[i][j] = this.m[i][j] - input.m[i][j];
            }
        }
        return res;
    }
    public void setElement(int row, int column, int value){
        m[row][column] = value;
    }
    public int getElement(int row, int column){
        return this.m[row][column];
    }
    public UsualMatrix product(UsualMatrix input){
        UsualMatrix res = new UsualMatrix(this.getRowSize(), input.getColSize());
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < input.getColSize(); j++) {
                for (int k = 0; k < this.getColSize(); k++) {
                    res.setElement(i, j, res.getElement(i, j) + this.getElement(i, k) * input.getElement(k, j));
                }
            }
        }
        return res;
    }
    public String toString(){
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                st.append(getElement(i, j));
                st.append(" ");
            }
            st.append("\n");
        }
        String str = st.toString();
        return str;
    }
    public boolean equals (Object input)
    {
        if (!(input instanceof UsualMatrix))
        {
            return false;
        }
        UsualMatrix tmp = (UsualMatrix)input;
        if (getRowSize() != tmp.getRowSize() || getColSize() != tmp.getColSize()) return false;
        for (int i = 0; i < getRowSize(); i++) {
            for (int j = 0; j < getColSize(); j++) {
                if (this.getElement(i,j) != tmp.getElement(i,j)) return false;
            }
        }
        return true;
    }
}
