package com.nord.service.fixedDeposit;

import com.nord.persistence.DbConnection;
import com.nord.persistence.fixedDeposit.interfaces.IFixedDepositPlanPersistence;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositModel;
import com.nord.persistence.fixedDeposit.interfaces.IUserFixedDepositsPersistence;
import com.nord.service.Context;
import com.nord.service.fixedDeposit.interfaces.IGetUserFixedDeposits;
import com.nord.service.fixedDeposit.mock.MockFixedDeposit;
import com.nord.service.fixedDeposit.mock.MockFixedDepositPlanPersistence;
import com.nord.service.fixedDeposit.mock.MockUserFdPersistence;
import com.nord.view.fixedDeposit.FixedDepositUserPlansView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test getting getting user fixed Deposits
 * @author Abdul Mueed Qadri
 */
public class GetUserFixedDepositsTests {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final int TEST_USER_ID = 1;
    private int TEST_FD_ID = 4;
    private IFixedDepositPlanPersistence plans;
    private IUserFixedDepositsPersistence userFd;
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
    void showAllFdsForUserTest() {
        Integer initialFdCount = userFd.getAllFdsForUser().size() + 1;
        String input = initialFdCount.toString() ;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        IGetUserFixedDeposits fixedDeposits =new GetUserFixedDeposits()
                .setDb(userFd)
                .setFdPlans(plans.getFds())
                .setView(new FixedDepositUserPlansView());
        List<IUserFixedDepositModel> fds = fixedDeposits.showAllFdsForUser();
        assertEquals(fds.get(0).getFdId(), TEST_FD_ID);
    }

    @Test
    void showSelectedFdDetails(){
        int initialFdCount = userFd.getAllFdsForUser().size();
        StringBuilder sb = new StringBuilder();
        String input = Integer.toString(initialFdCount);
        String secondInput = Integer.toString(initialFdCount+ 1);
        sb.append(input);
        sb.append(" 0 ");
        sb.append(secondInput);
        InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
        System.setIn(in);
        IGetUserFixedDeposits fixedDeposits = new GetUserFixedDeposits()
                .setDb(userFd)
                .setFdPlans(plans.getFds())
                .setView(new FixedDepositUserPlansView());
        fixedDeposits.showAllFdsForUser();
        assertEquals(fixedDeposits.showAllFdsForUser().get(0).getFdId(), TEST_FD_ID);
    }

    @Test
    void withdrawUserFd(){
        int initialFdCount = userFd.getAllFdsForUser().size();
        StringBuilder sb = new StringBuilder();
        String input = Integer.toString(initialFdCount);
        sb.append(input);
        sb.append(" 1 1 0");
        int initialSize = userFd.getAllFdsForUser().size();
        InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
        System.setIn(in);
        IGetUserFixedDeposits fixedDeposits = new GetUserFixedDeposits()
                .setDb(userFd)
                .setFdPlans(plans.getFds())
                .setView(new FixedDepositUserPlansView());
        fixedDeposits.showAllFdsForUser();
        assertEquals(initialSize - 1, userFd.getAllFdsForUser().size());
    }

    @AfterEach
    public void tearDown() {
        DbConnection.closeDbConnection();
        System.setOut(standardOut);
    }
}
