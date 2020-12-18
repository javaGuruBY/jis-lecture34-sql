package by.jrr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionalOperationsTest {

    TransactionalOperations transactionalOperations;

    @BeforeEach
    public void setUp() {
        transactionalOperations = new TransactionalOperations();
    }

    @Test
    void justPrints() throws Exception {
        transactionalOperations.printSelected(36, 37);
    }

    @Test
    void transactionalOperation() throws Exception {
        transactionalOperations.printSelected(36, 37);
        transactionalOperations.transactionalOperation("F", 36, 37);
        transactionalOperations.printSelected(36, 37);
    }

    @Test
    void savepointOperation() throws Exception {
        transactionalOperations.printSelected(36, 37, 38);
        transactionalOperations.savepointOperation("M", 36, 37, 38);
        transactionalOperations.printSelected(36, 37, 38);
    }

    @Test
    void riskyOperation() throws Exception {
        transactionalOperations.printSelected(36, 37);
        transactionalOperations.riskyOperation("F", 36, 37);
        transactionalOperations.printSelected(36, 37);
    }


}
