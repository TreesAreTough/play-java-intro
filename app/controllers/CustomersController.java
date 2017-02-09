package controllers;


import com.google.inject.Inject;
import models.Customers;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.libs.Json.toJson;

public class CustomersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public CustomersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Customers> customers = (List<Customers>) jpaApi.em().createQuery("select c from Customers c").getResultList();

        return ok(views.html.customers.render(customers));
    }

    @Transactional(readOnly = true)
    public Result getCustomers()
    {
        List<Customers> customers = (List<Customers>) jpaApi.em().createQuery("select customerId, contactName, companyName from Customers").getResultList();
        return ok(toJson(customers));
    }
    @Transactional (readOnly = true)
    public Result editCustomers(Long customerId)
    {
        Customers customers = (Customers) jpaApi.em().createQuery ("select c from Customers c where customerId = :id").setParameter("id", customerId).getSingleResult();

        return ok(views.html.updateCustomer.render(customers));
    }
    @Transactional
    public Result updateCustomers()
    {
        DynamicForm postedForm = formFactory.form().bindFromRequest();

        String customerId = new String(postedForm.get("customerId"));
        String contactName = postedForm.get("contactName");
        String companyName = postedForm.get("companyName");


        Customers customers = (Customers) jpaApi.em()
                .createQuery("select c from Customers c where customerId = :id")
                .setParameter("id", customerId).getSingleResult();
        customers.contactName = contactName;
        customers.companyName = companyName;

        jpaApi.em().persist(customers);

        return redirect(routes.CustomersController.index());
    }

    @Transactional
    public Result addCustomers()
    {

        Customers customers = formFactory.form(Customers.class).bindFromRequest().get();

        jpaApi.em().persist(customers);

        return redirect(routes.CustomersController.index());
    }

    @Transactional(readOnly = true)
    public Result newCustomers()
    {
        return ok(views.html.newCustomer.render());
    }

}

