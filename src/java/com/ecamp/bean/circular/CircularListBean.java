package com.ecamp.bean.circular;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.ReceiverGroupBean;

public class CircularListBean {
    
    private CircularBean circularBean;
    private ReceiverGroupBean receiverGroupBean;

    public CircularBean getCircularBean() {
        return circularBean;
    }

    public void setCircularBean(CircularBean circularBean) {
        this.circularBean = circularBean;
    }

    public ReceiverGroupBean getReceiverGroupBean() {
        return receiverGroupBean;
    }

    public void setReceiverGroupBean(ReceiverGroupBean receiverGroupBean) {
        this.receiverGroupBean = receiverGroupBean;
    }   
}
