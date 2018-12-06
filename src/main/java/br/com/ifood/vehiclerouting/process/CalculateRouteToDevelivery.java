package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.exception.IfoodProcessException;

import java.util.List;

public class CalculateRouteToDevelivery implements IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    private static final Double UNITS_BY_5_MINUTES = 0.1;

    public List process(Object...orders) throws IfoodProcessException {
        //VALIDATE PROCESS HERER
        if("" == null){
            throw new IfoodProcessException("Erro ao Cacular Rotas");
        }

        System.out.println("Calculando Rotas");
        //nextIfoodProcess.process(orders);

        return null;
    }

    public void setNextIfoodProcess(IfoodProcess ifoodProcess) {
        nextIfoodProcess = ifoodProcess;
    }

}
