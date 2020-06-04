package io.priyak.app;

import io.priyak.app.context.ApplicationContext;
import io.priyak.app.operation.OperationSet;
import io.priyak.app.processor.RequestProcessor;
import io.priyak.app.processor.impl.FileProcessor;

import java.io.File;

/**
 * Main class to bootstrap the application
 *
 * @author priyakdey
 */
public class Main {

    private static final String PWD = System.getProperty("user.dir");

    public static void main(String[] args) {
        System.out.println("###########################################");
        System.out.println("#### Application Starting.....       ######");
        System.out.println("###########################################");

        if (args != null) {
            final String fileName = args[0];
            File file;
            if (fileName.startsWith("/")) {
                file = new File(fileName);
            } else {
                file = new File(PWD, fileName);
            }

            RequestProcessor processor = new FileProcessor(file);
            ApplicationContext context = new ApplicationContext();
            try {
                OperationSet operationSet = processor.process();
                //TODO: to be completed
                final String[] arr = operationSet.take();
                final int numberOfSpots = Integer.parseInt(arr[1]);
                context.getController(numberOfSpots);
            } catch (Exception e) {
                for (Throwable throwable : e.getSuppressed()) {
                    System.out.println(throwable.getMessage());
                }
            }
        }
    }

}
