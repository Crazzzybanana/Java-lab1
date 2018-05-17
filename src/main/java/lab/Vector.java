package lab;

public class Vector {
    final int M;
    private int[] data;

    public Vector(int M) {
        if(M != 3) throw new IllegalArgumentException("Wrong vector");
        this.M = M;
        data = new int[M];
    }

    public Vector(int[] vector) {
        if(!vectorIsValid(vector)) throw new IllegalArgumentException("Wrong vector");
        this.M = vector.length;
        this.data = vector;
    }

    public int getM(){return M;}
    public int[] getData(){return data;}

    public Vector cross(Vector v) {
        if(v == null) throw new IllegalArgumentException("Wrong cross vector");
        return new Vector(new int[]{
                this.data[1] * v.data[2] - this.data[2] * v.data[1],
                this.data[2] * v.data[0] - this.data[0] * v.data[2],
                this.data[0] * v.data[1] - this.data[1] * v.data[0]
        });
    }

    public String toString(){
        return String.format("X: %d|Y: %d|Z: %d", data[0], data[1], data[2]);
    }

    @Override
    public boolean equals(Object B) {
        if(B == null) return false;
        if(B == this) return true;
        if(B instanceof Vector){
            Vector A = this;
            Vector C = (Vector)B;
            if (C.data.length != A.data.length) return false;
            for (int i = 0; i < A.data.length; i++)
                if (A.data[i] != C.data[i]) return false;
            return true;
        }
        return false;
    }

    public Vector multiply(Matrix a) {
        if(a == null) throw new IllegalArgumentException("Wrong multiply vector");
        Vector A = this;
        int m = a.getM();
        int n = a.getN();
        if (A.data.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        Vector V = new Vector(m);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                V.data[i] += a.getMatrix()[i][j] * A.data[j];
        return V;
    }

    public static boolean vectorIsValid(int[]vector){
        if(vector != null)
        return vector.length == 3 ? true : false;
        else return false;
    }
}
