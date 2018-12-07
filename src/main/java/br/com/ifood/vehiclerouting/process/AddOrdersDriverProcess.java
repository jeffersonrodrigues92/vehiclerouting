package br.com.ifood.vehiclerouting.process;


import br.com.ifood.vehiclerouting.bean.RouteBean;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AddOrdersDriverProcess extends  IfoodProcess{

    private IfoodProcess nextIfoodProcess;

    public List<Integer> process(Set<ArrayList<RouteBean>> routesSameRestaurantsOrders) throws IfoodProcessException {

        List<Integer> teste = new ArrayList<>();
        return teste;
    }

    @Override
    public void setNextIfoodProcess(IfoodProcess process) {
        nextIfoodProcess = process;
    }
}
