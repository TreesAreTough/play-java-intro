package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.Employees;
import models.RandomUser;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.randomUser;

import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static play.libs.Json.toJson;

public class EmployeesController extends Controller
{

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public EmployeesController(FormFactory formFactory, JPAApi jpaApi) {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result index()
    {
        List<Employees> employees = (List<Employees>) jpaApi.em().createQuery("Select e from Employees e").getResultList();

        return ok(views.html.employees.render(employees));
    }

    @Transactional(readOnly = true)
    public Result getEmployees()
    {
        List<Employees> employees = (List<Employees>) jpaApi.em().createQuery("select employeeId, lastName, firstName from Employees").getResultList();
        return ok(toJson(employees));
    }

    @Transactional (readOnly = true)
    public Result getPicture(Long id)
    {
        Employees employee =(Employees)jpaApi.em().createQuery("select e from Employees e where employeeId = :id").setParameter("id", id).getSingleResult();

        if (employee.photo == null)
        {
            return null;
        }
        else
        {
            return ok(employee.photo).as("image/bmp");
        }
    }
    @Transactional (readOnly = true)
    public Result editEmployee(Long employeeId)
    {
        Employees employee = (Employees)jpaApi.em().createQuery ("select e from Employees e where employeeId = :id").setParameter("id", employeeId).getSingleResult();

        return ok(views.html.updateEmployee.render(employee));
    }
    @Transactional
    public Result updateEmployee()
    {
        DynamicForm postedForm = formFactory.form().bindFromRequest();

        Long employeeId = new Long(postedForm.get("employeeId"));
        String firstName = postedForm.get("firstName");
        String lastName = postedForm.get("lastName");


        Employees employee = (Employees) jpaApi.em()
                .createQuery("select e from Employees e where employeeId = :id")
                .setParameter("id", employeeId).getSingleResult();
        employee.firstName = firstName;
        employee.lastName = lastName;

        jpaApi.em().persist(employee);

        return redirect(routes.EmployeesController.index());
    }

    @Transactional
    public Result addEmployee()
    {

        Employees employee = formFactory.form(Employees.class).bindFromRequest().get();

        jpaApi.em().persist(employee);

        return redirect(routes.EmployeesController.index());
    }

    @Transactional(readOnly = true)
    public Result newEmployee()
    {
        RandomUser randomUser = null;
        try
        {
            String myURL = "https://randomuser.me/api";

            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();


            ObjectMapper objectMapper = new ObjectMapper();
            randomUser = objectMapper.readerFor(RandomUser.class).readValue(url);
        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.newEmployee.render(randomUser));
    }

}
