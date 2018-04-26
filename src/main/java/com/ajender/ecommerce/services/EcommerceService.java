package com.ajender.ecommerce.services;

import com.ajender.ecommerce.repositories.GroupRepository;
import com.ajender.ecommerce.repositories.OrderRepository;
import com.ajender.ecommerce.repositories.ProductRepository;
import com.ajender.ecommerce.models.Order;
import com.ajender.ecommerce.models.Product;
import com.ajender.ecommerce.models.ProductGroup;
import com.ajender.ecommerce.models.ProductImage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcommerceService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private SessionFactory sessionFactory;


    /* PRODUCT */
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getProduct(long id){
        return productRepository.findOne(id);
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public String addProductImage(final String productId, final String filename){

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ProductImage image = new ProductImage();
        image.setProductId(Long.parseLong(productId));
        image.setPath(filename);

        try {
            String res = session.save(image).toString();
            session.getTransaction().commit();
            return res;
        } catch (HibernateException e) {
            System.out.print(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return "";
    }

    /* GROUPS */
    public List<ProductGroup> getGroups(){
        return groupRepository.findAll();
    }
    public ProductGroup getGroup(long id){
        return groupRepository.findOne(id);
    }
    public ProductGroup saveGroup(ProductGroup group){
        return groupRepository.save(group);
    }

    /* ORDERS */
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    public Order getOrder(long id){
        return orderRepository.findOne(id);
    }
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
}