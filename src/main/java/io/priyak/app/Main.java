package io.priyak.app;

import io.priyak.app.context.ApplicationContext;
import io.priyak.app.controller.ParkingController;
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
            processFile(args[0]);
        }
    }

    private static void processFile(String fileName) {
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
            final String[] arr = operationSet.take();
            final int numberOfSpots = Integer.parseInt(arr[1]);

            final ParkingController controller = context.getController(numberOfSpots);
            controller.setOperationSet(operationSet);

            controller.process();
        } catch (Exception e) {
            for (Throwable throwable : e.getSuppressed()) {
                System.out.println(throwable.getMessage());
            }
        }
    }

}
