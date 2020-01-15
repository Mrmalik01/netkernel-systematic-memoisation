//package tutorial.basics.algorithms;
//
//import org.netkernel.layer0.nkf.INKFRequestContext;
//import org.netkernel.layer0.nkf.INKFResponse;
//import org.netkernel.module.standard.endpoint.StandardAccessorImpl;
//
//import java.util.Date;
//
//public class FibTest extends StandardAccessorImpl {
//    @Override
//    public void onSource(INKFRequestContext aContext) throws Exception {
//        int number = 36;
//        long t1 = System.nanoTime();
//        System.out.println(t1);
//        int result1 = FibonacciJava.fibNaive(number, 0);
//        long t2 = System.nanoTime();
//        System.out.println(t2);
//        System.out.println("Actual time taken : "+(t2-t1));
//        long naiveTotalTime = t2-t1;
//        System.out.println("Naive algorithm is taking this amount of time : "+naiveTotalTime);
//
//
//
//
//        Object res2= null;
//        t1 = System.nanoTime();
//        print(t1);
//        try {
//            res2 = aContext.source("active:Fib+num@" + number);
//        }catch(Exception ex){
//            res2 = new String("-1");
//        }
//        t2 = System.nanoTime();
//        print(t2);
//        int result2 = Integer.parseInt(res2.toString());
//        long intrinsicTotalTime = t2-t1;
//
//        String fastest = "";
//
//        print(intrinsicTotalTime+" ms - Intrinsic  ");
//        print(naiveTotalTime+" ms - Naive\n Lets see which one is faster");
//        if (intrinsicTotalTime > naiveTotalTime){
//            print("naive is faster");
//            double fast = (double)(naiveTotalTime/intrinsicTotalTime);
//            print(fast);
//            double fastPercentage = fast;
//            System.out.println(fastPercentage+"ms - faster percentage");
//            fastest = "Naive "+Math.round(fastPercentage)+" times faster than Extrinsic";
//        }else{
//            print("Extrinsic is faster");
//            double fast = (double)(naiveTotalTime/intrinsicTotalTime);
//            print(fast);
//            double fastPercentage = fast;
//            System.out.println(fastPercentage+"ms - faster percentage");
//            fastest = "Extrinsic "+Math.round(fastPercentage)+" times faster than Naive";
//        }
//
//
//        String totalResult = "<html><body>" +
//                "<h1>Time difference between Naive Fibonacci algorithm and Extrinsic algorithm</h1>" +
//                "<p>Naive fibonacci took     : "+naiveTotalTime+" ms ------ Result :"+result1+" </p>"+
//                "<p>Extrinsic fibonacci took : "+intrinsicTotalTime+" ms ------ Result : "+result2+"</p>"+
//                "<p>Result : "+fastest+
//                "</body></html>";
//
//        INKFResponse response = aContext.createResponseFrom(totalResult);
//        response.setMimeType("text/html");
//    }
//
//    public static void print(Object msg){
//        System.out.println(msg);
//    }
//}
