public class Quadratic {
    public static void main(String[] args){
        calc(1,1,-6);
        calc(0,5,10);
        calc(9,0,-16);
        calc(9,-3,0);
        calc((float) 2.5,4, (float) 1.6);
    }

    public static void calc(float a, float b, float c){
        float x1, x2;
        System.out.println("Решаем уравнение, где\n a = " + a + "\n b = " + b + "\n c = " + c );
        if (a==0){
            x1=(-c)/b;
            System.out.println("Уравнение не квадратное. X = " + x1);
        } else {

            float d = b * b - 4 * a * c;

            if (d < 0) {
                System.out.println("-");
            } else if (d == 0) {
                x1 = (-b)/(2*a);
                System.out.println("Уравнение имеет один корень. X = " + x1);
            } else {
                x1 = (float)(((-b)+Math.sqrt(d))/(2*a));
                x2 = (float)(((-b)-Math.sqrt(d))/(2*a));
                System.out.println("Уравнение имеет два корня. X1 = " + x1 + " X2 = " + x2);
            }
        }
        System.out.println("\n");
    }
}
