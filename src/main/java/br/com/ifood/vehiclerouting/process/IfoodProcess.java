package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.bean.RoutesBean;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;

import java.util.List;

public abstract class IfoodProcess {

    public <T> List<T> process() throws IfoodProcessException{

        return null;
    }

    public List<RoutesBean> process(List<Orders> orders) throws IfoodProcessException {

        return null;
    }

    public abstract void setNextIfoodProcess(IfoodProcess process);


}
