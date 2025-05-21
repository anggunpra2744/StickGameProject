public class Kuliah2 {

    public static void main(String[] args) {
        
        MyArray x;
        MyArray y;
        
        x = new MyArray(5);
        y = new MyArray(10);
        
        x.disp();
        x.createRandom(100);
        x.disp();
        
        y.createRandom2(20);
        y.disp();
        
        y.shiftLeft();
        y.disp();
    }
}