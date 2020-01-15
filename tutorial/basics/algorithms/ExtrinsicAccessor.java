package tutorial.basics.algorithms;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class ExtrinsicAccessor extends StandardAccessorImpl{

    @Override
    public void onSource(INKFRequestContext aContext) throws Exception {
        final int number = Integer.parseInt(aContext.getThisRequest().getArgumentValue("num"));
        long t1 = System.nanoTime();
        Object res2 = aContext.source("active:Fib+num@" + number);
        long t2 = System.nanoTime();
        String jsonResult = String.format("{\"time-taken\" : %o, \"time-measure\": \"ns\", \"result\": %o}",(t2-t1), res2.toString());
        INKFResponse response = aContext.createResponseFrom(jsonResult);
        response.setMimeType("application/json");
    }
}