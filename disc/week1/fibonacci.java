public class fibonacci {
    public static int fib(int n){
        if(n <= 1){
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fib2(int n, int k, int f0, int f1){
        // if(n <= 1){
        //     return n;
        // }
        // f1 = f1 + f0;
        // f0 = f1 - f0;
        // if(k == n - 1){
        //     return f1;
        // }
        // return fib2(n, k + 1, f0, f1);
        if (n == k){
            return f0;
        }else{
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }
    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(fib2(5, 0, 0, 1));
    }
}
