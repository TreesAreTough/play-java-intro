package controllers;

import models.Employees;
import models.Orders;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class OrdersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public OrdersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getOrders()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().createQuery("select orderId from Orders").getResultList();

        return ok(toJson(orders));
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().createQuery("select o from Orders o").getResultList();

        return ok(views.html.orders.render(orders));
    }

}