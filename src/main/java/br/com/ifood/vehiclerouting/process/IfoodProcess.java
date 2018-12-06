package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;

import java.util.List;

public interface IfoodProcess {

    public abstract <T> List<T> process(Object ... orders) throws IfoodProcessException;
    public abstract void setNextIfoodProcess(IfoodProcess process);


}
