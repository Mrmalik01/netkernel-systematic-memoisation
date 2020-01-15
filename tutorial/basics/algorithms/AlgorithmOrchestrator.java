package tutorial.basics.algorithms;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class AlgorithmOrchestrator extends StandardAccessorImpl {
    @Override
    public void onSource(INKFRequestContext context) throws Exception {
        String url = context.source("httpRequest:/url", String.class);
        String[] parts = url.split("/");
        String type = parts[4];
        int num = Integer.parseInt(parts[5]);
        int resultNaive = 0;
        int stepsNaive = 0;
        int resultExtrinsic = 0;
        int stepsExtrinsic = 0;
        long totalTime = 0;
        if (!type.equalsIgnoreCase("both")) {
            if (type.equalsIgnoreCase("naive")) {
                String result1 = "";
                long t1 = System.nanoTime();
                result1 = FibonacciJava.fibNaive(num, 0);
                System.out.println("Naive result - " + result1);
                String[] res = result1.split("/");
                resultNaive = Integer.parseInt(res[0]);
                stepsNaive = Integer.parseInt(res[1]);
                long t2 = System.nanoTime();
                totalTime = t2 - t1;
                System.out.println("Total steps - naive - " + stepsNaive);
                createJsonResponse(context, totalTime, resultNaive, type, stepsNaive);
            } else {
                String result2 = "";
                long t1 = System.nanoTime();
                try {
                    result2 = context.source("active:Fib+num@" + num + "+steps@" + 0, String.class);
                } catch (Exception e) {
                    System.out.println("Error : Cannot send request using grammar - active : Fib");
                }
                long t2 = System.nanoTime();
                System.out.println("Ext result - " + result2);
                String[] res2 = result2.split("/");
                resultExtrinsic = Integer.parseInt(res2[0]);
                stepsExtrinsic = Integer.parseInt(res2[1]);
                totalTime = t2 - t1;
                System.out.println("Total steps - Extrinsic - " + stepsExtrinsic);
                createJsonResponse(context, totalTime, resultExtrinsic, type, stepsExtrinsic);
            }
        }

    }

    public static void createJsonResponse(INKFRequestContext aContext, long time, int result, String type, int steps) {
        String jsonResult = String.format(
                "{\"type\":\"" + type + "\",\"time\" : %o, \"measure\": \"ns\", \"result\": %o, \"steps\": %o}", time,
                result, steps);
        INKFResponse response = aContext.createResponseFrom(jsonResult);
        response.setMimeType("application/json");
    }
}
