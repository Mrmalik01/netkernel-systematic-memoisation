package tutorial.basics.part2;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class ServletPatternAccessor extends StandardAccessorImpl {
    @Override
    public void onSource(INKFRequestContext context) throws Exception {

        String url = context.source("httpRequest:/url", String.class);
        String method = context.source("httpRequest:/method", String.class);
        String result = "Hello Servlet World" + "\nRequest Identifier = " + context.getThisRequest().getIdentifier()
                + "\nHTTP Method = " + method + "\nHTTP URL = " + url + "\nName : Khizar Malik";
        result += "\n\nDone on 8th January";


        result = "<html><body><h1>Hello World</h1><button>Click</button></body></html>";
        INKFResponse response = context.createResponseFrom(result);
        response.setMimeType("text/html");

    }
}
