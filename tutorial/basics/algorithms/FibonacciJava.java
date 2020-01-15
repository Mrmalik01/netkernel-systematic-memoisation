package tutorial.basics.algorithms;

import org.netkernel.layer0.nkf.INKFRequest;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class FibonacciJava extends StandardAccessorImpl {

    public FibonacciJava() {
        this.declareThreadSafe();
    }

    public static String fibNaive(int num, int steps) {
        steps++;
        System.out.println("Naive - " + steps);
        if (num == 0 || num == 1) {
            String result = num + "/" + steps;
            return result;
        }
        String[] res1 = fibNaive(num - 1, steps).split("/");
        String[] res2 = fibNaive(num - 2, steps).split("/");
        return (Integer.parseInt(res1[0]) + Integer.parseInt(res2[0])) + "/"
                + (Integer.parseInt(res1[1]) + Integer.parseInt(res2[1]));
    }

    // public void onSource(INKFRequestContext context) throws Exception{
    // int num = Integer.parseInt(context.getThisRequest().getArgumentValue("num"));
    // int steps =
    // Integer.parseInt(context.getThisRequest().getArgumentValue("steps"));
    // steps++;
    // if (num==0 || num ==1){
    // context.createResponseFrom(num); return;
    // }
    // int result =
    // Integer.parseInt(context.source("active:Fib+num@"+(num-1)).toString()+"+steps@"+steps)
    // +
    // Integer.parseInt(context.source("active:Fib+num@"+(num-2)).toString()+"+steps@"+steps);
    // context.createResponseFrom(result); return;
    // }
    public void onSource(INKFRequestContext context) throws Exception {
        int num = Integer.parseInt(context.getThisRequest().getArgumentValue("num"));
        int steps = Integer.parseInt(context.getThisRequest().getArgumentValue("steps"));
        steps++;

        System.out.println("Extensive - " + steps);

        if (num == 0 || num == 1) {
            String result = num + "/" + steps;
            context.createResponseFrom(result);
            return;
        }
        String[] res1 = context.source("active:Fib+num@" + (num - 1) + "+steps@" + steps).toString().split("/");
        String[] res2 = context.source("active:Fib+num@" + (num - 2) + "+steps@" + steps).toString().split("/");
        String result = (Integer.parseInt(res1[0]) + Integer.parseInt(res2[0])) + "/"
                + (steps + Integer.parseInt(res1[1]) + Integer.parseInt(res2[1]));
        context.createResponseFrom(result);
        return;
    }
}
