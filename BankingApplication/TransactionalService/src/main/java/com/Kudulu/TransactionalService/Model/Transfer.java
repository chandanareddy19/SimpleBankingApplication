package com.Kudulu.TransactionalService.Model;

import java.time.LocalDateTime;

public class Transfer {
    private int id1;
    private int id2;
    private Double TransferFund;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private LocalDateTime time;

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public Double getTransferFund() {
        return TransferFund;
    }

    public void setTransferFund(Double transferFund) {
        TransferFund = transferFund;
    }
}
