
package com.ecamp.service.circular;
import com.ecamp.bean.circular.CircularBean;
import com.ecamp.bean.circular.CircularListBean;
import com.ecamp.bean.circular.ReceiverGroupBean;
import com.ecamp.dao.circular.CircularListDAO;
import java.util.ArrayList;
public class CircularListAdministrator {
    public String addCircularList(CircularListBean circularListBean){
        String result="FAILURE";
        if(circularListBean == null || circularListBean.getCircularBean().getCircularId()<= 0 || circularListBean.getReceiverGroupBean().getReceiverGroupId()<= 0) {
            result = "INVALID";
        }               
        else if(new CircularListDAO().addCircularList(circularListBean)){
            result = "SUCCESS";
        }
        return result;
    }
    
    public ArrayList<CircularListBean> viewCircularListByCircular(int circularId){
        
        CircularListBean circularListBean = null;
        ArrayList<CircularListBean> circularListBeanList = null;
        if(circularId > 0){
            circularListBeanList =  new CircularListDAO().viewCircularListByCircular(circularId);
        }        
        return circularListBeanList;
    }    
   public ArrayList<CircularListBean> viewCircularListByReceiverGroup(short receiverGroupId){
        CircularListBean circularListBean = null;
        ArrayList<CircularListBean> circularListBeanList = null;
        if(receiverGroupId > 0){
            circularListBeanList =  new CircularListDAO().viewCircularListByReceiverGroup((short)receiverGroupId);
        }        
       return circularListBeanList;
  } 
    public static void main(String[] args) {
        CircularListBean circularListBean=new CircularListBean();
        CircularBean circularBean = new  CircularBean();
        circularBean.setCircularId(1);
        circularListBean.setCircularBean(circularBean);
        ReceiverGroupBean receiverGroupBean = new ReceiverGroupBean();
        receiverGroupBean.setReceiverGroupId((short)1);
        circularListBean.setReceiverGroupBean(receiverGroupBean); 
        System.out.println(new CircularListAdministrator().addCircularList(circularListBean));
//        ArrayList<CircularListBean> list = new ArrayList<CircularListBean>();
//        //list = new CircularListAdministrator().viewCircularListByCircular(119);
//        //System.out.println(list);
//       //CircularListDAO circularListDAO = new CircularListDAO();
//        //for(int i=0;i<list.size();i++){ 
//           // System.out.println(list.get(i).getReceiverGroupBean().getReceiverGroupId());
//        //}
//        list = new CircularListAdministrator().viewCircularListByReceiverGroup((short)4);
//        System.out.println(list);
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i).getCircularBean().getCircularId());
  //     }     
    }
}
