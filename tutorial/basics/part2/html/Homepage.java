package tutorial.basics.part2.html;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.nkf.INKFResponse;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

import java.io.*;
import java.util.Scanner;

public class Homepage extends StandardAccessorImpl {
    @Override
    public void onSource(INKFRequestContext aContext) throws Exception {
        System.out.println("Current directory : "+ System.getProperty("user.dir"));
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader("project-modules/urn.org.netkernel.java.tutorial/tutorial/basics/part2/html/homepage.html"));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Reading from file unsuccessful");
        }
        String content = contentBuilder.toString();
        System.out.println("Web page content = \n"+content);
        System.out.println(content);
        INKFResponse response = aContext.createResponseFrom(content);
        response.setMimeType("text/html");
    }
}
