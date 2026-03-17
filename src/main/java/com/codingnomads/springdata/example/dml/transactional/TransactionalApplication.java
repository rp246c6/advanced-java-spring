/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.transactional;

import com.codingnomads.springdata.example.dml.transactional.models.Point;
import com.codingnomads.springdata.example.dml.transactional.services.PointService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class TransactionalApplication implements CommandLineRunner {

    @Autowired
    PointService pointService;

    public static void main(String[] args) {
        SpringApplication.run(TransactionalApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

        // @Transactional I

        //pointService.foo();
       // pointService.doSomeWork();

        // @Transactional II

       // pointService.timeOutAfter5();
         //pointService.triggerTimeout();
       // pointService.noExceptionExpected();
        //System.out.println(pointService.getPointById(1L).toString());
        //pointService.noExceptionExpected();

       /*try {
            pointService.rollbackFor();
        } catch (IOException e) {
            // do nothing... move on
        }

        /*try {
            pointService.noRollbackFor();
        } catch (InterruptedException e) {
            // do nothing... move on
        }*/

        // New transactional demo methods
        successfulTransactionDemo();
        rollbackTransactionDemo();

    }
    /**
     * REQUIRED propagation ensures a transaction is created if none exists.
     * This method will commit successfully.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void successfulTransactionDemo() {
        pointService.savePoint(); // delegates to PointService
        System.out.println("✅ Transaction committed successfully for point " );
    }

    /**
     * Demonstrates rollback behavior.
     * Any exception here will trigger rollback.
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {IOException.class, ArithmeticException.class})
    public void rollbackTransactionDemo() throws IOException, ArithmeticException {
        try {
            pointService.rollbackFor();
        } catch (IOException e) {
            // do nothing... move on
            throw new IOException("Transaction will roll back");
        }
    }

}
