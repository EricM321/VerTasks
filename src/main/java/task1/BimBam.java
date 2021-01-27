package task1;

public class BimBam {

    private static final int START = 1;
    private static final int END = 200;

    public void BimBam() {

        for (int i = START; i <= END; i++) {
            if(i % 3 == 0){
                if(i % 5 == 0){
                    System.out.println("BimBam");
                } else{
                    System.out.println("Bim");
                }
            } else if(i % 5 == 0){
                System.out.println("Bam");
            } else{
                System.out.println(i);
            }
        }
    }
}
