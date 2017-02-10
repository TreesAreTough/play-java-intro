package controllers;

import models.Categories;
import models.Products;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class ProductsController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public ProductsController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getProducts()
    {
        List<Products> products = (List<Products>) jpaApi.em().createQuery("select productId, productName from Products").getResultList();

        return ok(toJson(products));
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Products> products = (List<Products>) jpaApi.em().createQuery("select p from Products p").getResultList();

        return ok(views.html.products.render(products));
    }

    @Transactional(readOnly = true)
    public Result newProducts()
    {
        List<Categories> categories = (List<Categories>) jpaApi.em().createQuery("select c from Categories c").getResultList();
        return ok(views.html.newProducts.render(categories));
    }

    @Transactional
    public Result addProducts()
    {
        Products product = formFactory.form(Products.class).bindFromRequest().get();

        DynamicForm requestData = formFactory.form().bindFromRequest();
        Long categoryId = new Long(requestData.get("categoryId"));

        product.category = (Categories)jpaApi.em().createQuery("select c from Categories c where categoryId = :id").setParameter("id", categoryId).getSingleResult();
        jpaApi.em().persist(product);
        return redirect(routes.ProductsController.index());
    }

    @Transactional(readOnly = true)
    public Result editProducts(Long productId)
    {
        Products product = (Products)jpaApi.em().createQuery("select p from Products p where productId = :id)").setParameter("id", productId).getSingleResult();

        List<Categories> categories = (List<Categories>) jpaApi.em().createQuery("select c from Categories c").getResultList();

        return ok(views.html.updateProducts.render(product, categories));
    }


    @Transactional
    public Result updateProducts()
    {
        //Gets the data from the form the user submitted
        DynamicForm requestData = formFactory.form().bindFromRequest();

        //Pull the values out that were on the submitted form
        Long productId = new Long(requestData.get("productId"));
        String productName = requestData.get("productName");
        Long categoryId = new Long(requestData.get("categoryId"));

        //Get the model I want to modify
        Products product = (Products)jpaApi.em().createQuery("select p from Products p where productId = :id)").setParameter("id", productId).getSingleResult();

        //Copy the data we got from the form into the model
        product.productName = productName;
        product.category = (Categories)jpaApi.em().createQuery("select c from Categories c where categoryId = :id").setParameter("id", categoryId).getSingleResult();

        //Save the updatesd model to the database
        jpaApi.em().persist(product);

        //Send the user to the list of products view
        return redirect(routes.ProductsController.index());
    }

}