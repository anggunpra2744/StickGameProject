public class MyArray {

    // deklarasi variabel
    private int[] data;
    private int size;

    // deklarasi function constructor, di tiap kelas harus ada constructor
    public MyArray(int size) {
        this.size = size; // this.size mengacu variabel size yg di dalam class, sementara size mengacu
                           // pada argumen function
        this.data = new int[this.size];
    }

    public void disp() {
        for (int i = 0; i < this.size; i++)
            System.out.print(this.data[i] + " ");
        System.out.print("\n\n");
    }

    public void createRandom(int maxRandom) {
        for (int i = 0; i < this.size; i++)
            this.data[i] = (int) (maxRandom * Math.random());
    }
    
    private int intRand(int maxRand){
        int hasil;
        hasil = (int) (maxRand * Math.random());
        return hasil;
    }
    
    public void createRandom2(int maxRandom) {
        for (int i = 0; i < this.size; i++)
            this.data[i] = this.intRand(maxRandom);
    }
    
    public void shiftLeft(){
        for (int i = 0; i < this.size-1; i++)
            this.data[i] = this.data[i+1];
        this.data[this.size-1] = 0;
    }
}