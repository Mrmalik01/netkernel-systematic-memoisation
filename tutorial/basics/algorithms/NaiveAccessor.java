package tutorial.basics.algorithms;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class NaiveAccessor extends StandardAccessorImpl{
    @Override
    public void onSource(INKFRequestContext aContext) throws Exception {
        final int number = Integer.parseInt(aContext.getThisRequest().getArgumentValue("num"));
        long t1 = System.nanoTime();
        int result = FibonacciJava.fibNaive(number, 0);
        long t2 = System.nanoTime();

        String jsonResult = String.format("{\"time-taken\" : %o, \"time-measure\": \"ns\", \"result\": %o}",(t2-t1), result);
        INKFResponse response = aContext.createResponseFrom(jsonResult);
        response.setMimeType("application/json");
    }
}
