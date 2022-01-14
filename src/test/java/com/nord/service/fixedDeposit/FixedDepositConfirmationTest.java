package com.nord.service.fixedDeposit;

import com.nord.persistence.DbConnection;
import com.nord.persistence.fixedDeposit.UserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.Context;
import com.nord.service.fixedDeposit.interfaces.IFixedDepositConfirmation;
import com.nord.service.fixedDeposit.mock.MockFixedDeposit;
import com.nord.service.fixedDeposit.mock.MockFixedDepositPlanPersistence;
import com.nord.service.fixedDeposit.mock.MockUserFdPersistence;
import com.nord.view.fixedDeposit.FixedDepositConfirmationView;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test valid confirmation for fixed deposits
 * @author Abdul Mueed Qadri
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FixedDepositConfirmationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final int TEST_USER_ID = 1;
    private IFixedDepositPlanPersistence plans;
    private IUserFixedDepositsPersistence userFd;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private MockFixedDeposit mock;

    @BeforeEach
    public void setUp() {
        mock = new MockFixedDeposit();
        Context.setLoggedInUserId(TEST_USER_ID);
        System.setOut(new PrintStream(outputStreamCaptor));
        userFd = new MockUserFdPersistence(mock.getMockFd());
        plans = new MockFixedDepositPlanPersistence();
    }

    @Test
    void fdAdditionTest(){
        String input = "1 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int initialFdCount = userFd.getAllFdsForUser().size();
        IFixedDepositConfirmation confirmation =
                new FixedDepositConfirmation()
                        .setView(new FixedDepositConfirmationView())
                        .setUserPersistence(userFd)
                        .setFixedDepositPlans(plans.getFds())
                        .setSelectedFd(mock.getMockFd())
                        .setUpgradeableFd(new UserFixedDepositModel());
        confirmation.fdConfirmation();
        userFd.getAllFdsForUser().size();
        assertEquals(initialFdCount + 1, userFd.getAllFdsForUser().size());
    }

    @Test
    void fdUpgradeTest() {
        String input = "3 1 0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        double prevInterest = mock.getMockFdWithSuggestion().getInterest();
        IFixedDepositConfirmation confirmation =
                new FixedDepositConfirmation()
                        .setView(new FixedDepositConfirmationView())
                        .setUserPersistence(userFd)
                        .setFixedDepositPlans(plans.getFds())
                        .setSelectedFd(mock.getMockFdWithSuggestion())
                        .setUpgradeableFd(new UserFixedDepositModel());
        confirmation.fdConfirmation();
        IUserFixedDepositModel upgradedFd = userFd.getAllFdsForUser().get(1);
        assertTrue(prevInterest < upgradedFd.getInterest());
    }

    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
        System.setOut(standardOut);
    }
}