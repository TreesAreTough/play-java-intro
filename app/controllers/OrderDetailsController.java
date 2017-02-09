package controllers;


import com.google.inject.Inject;
import models.OrderDetails;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

public class OrderDetailsController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;


    @Inject
    public OrderDetailsController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result getOrderDetails()
    {
        List<OrderDetails> orderDetails = (List<OrderDetails>) jpaApi.em().createQuery("select unitPrice, quantity from OrderDetails").getResultList();

        return ok(toJson(orderDetails));
    }

    @Transactional (readOnly = true)
    public Result index()
    {
        List<OrderDetails> orderDetails = (List<OrderDetails>) jpaApi.em().createQuery("Select od from OrderDetails od").getResultList();

        return ok(views.html.orderdetails.render(orderDetails));
    }

}
