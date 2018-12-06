package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;

import java.util.List;

public class AddOrderDriver implements IfoodProcess {


    private IfoodProcess nextIfoodProcess;

    @Override
    public List process(Object...orders) throws IfoodProcessException {
        return null;
    }

    @Override
    public void setNextIfoodProcess(IfoodProcess process) {

    }
}
