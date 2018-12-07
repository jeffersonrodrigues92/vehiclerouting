package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.bean.RoutesBean;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateRouteDeliveryProcess extends IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    private static final Double UNITS_BY_5_MINUTES = 0.1;
    private static final int NUMBER_OF_ORDER_BY_DRIVER = 3;

    public List<RoutesBean> process(List<Orders> orders) throws IfoodProcessException {

        List<RoutesBean> routesBeanList = new ArrayList<>();
        routesBeanList.add(new RoutesBean());
        routesBeanList.get(0).setOrders(new ArrayList<>());


        List<Long> ids = new ArrayList<>();

        for(Orders order : orders){
            ids.add(order.getId());
        }

        routesBeanList.get(0).getOrders().addAll(ids);

        if("" == null){
            throw new IfoodProcessException("Erro ao Cacular Rotas");
        }

        System.out.println("Calculando Rotas");

        return routesBeanList;
    }

    public void setNextIfoodProcess(IfoodProcess ifoodProcess) {
        nextIfoodProcess = ifoodProcess;
    }

}
