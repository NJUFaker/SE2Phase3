package com.example.cinema.test.sales;


import com.example.cinema.blImpl.sales.TicketServiceImpl;
import com.example.cinema.vo.RefundTicketStrategyForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceImplTest {
    @Autowired
    private TicketServiceImpl ticketService;

    @Test
    public void getRefundStrategies() {
        List<RefundTicketStrategyForm> refundTicketStrategyForms=(List<RefundTicketStrategyForm>)ticketService.getRefundStrategies().getContent();
        for (int i = 0; i < refundTicketStrategyForms.size(); i++) {
            System.out.println(refundTicketStrategyForms.get(i).getId());
            System.out.println(refundTicketStrategyForms.get(i).getRefundPercentage());
        }
    }

    @Test
    public void refundTickets() {
    }

    @Test
    public void getRefundedTickets() {
    }

    @Test
    public void getInfoOfUnpaidTickets() {
    }
}
