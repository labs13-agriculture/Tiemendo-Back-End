package com.lambdaschool.tiemendo.service;

import com.lambdaschool.tiemendo.exception.ResourceNotFoundException;
import com.lambdaschool.tiemendo.model.Retailer;
import com.lambdaschool.tiemendo.model.Turnover;
import com.lambdaschool.tiemendo.repository.RetailerRepository;
import com.lambdaschool.tiemendo.repository.TurnoverRepository;
import org.springframework.data.domain.Pageable;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TurnoverServiceImpl implements TurnoverService{

    private TurnoverRepository repo;
    private RetailerRepository retailerRepo;

    public TurnoverServiceImpl(TurnoverRepository repo, RetailerRepository retailerRepo) {
        this.repo = repo;
        this.retailerRepo = retailerRepo;
    }

    @Override
    public ArrayList<Turnover> findAll(Pageable p) {
        var goals = new ArrayList<Turnover>();
        repo.findAll(p).iterator().forEachRemaining(goals::add);
        return goals;
    }

    @Override
    public ArrayList<Turnover> findAllByRetailer(Pageable p, long id) {
        Retailer r = retailerRepo.findRetailerById(id);

        var goals = new ArrayList<Turnover>();
        repo.findAllByRetailer(p, r).iterator().forEachRemaining(goals::add);
        return goals;
    }

    @Override
    public Turnover findById(long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("Could not find turnover goal with id: " + id);
        });
    }

    @Override
    public Turnover add(Turnover turnover) {
        return repo.save(turnover);
    }

    @Override
    public Turnover update(Turnover turnover) {
        // this would be cool to rewrite with generics to create a utility class
        Turnover current = findById(turnover.getId());

        // get names of all fields of given class
        var fields = Turnover.class.getDeclaredFields();
        // iterate over the field names
        for(int i=0; i<fields.length; i++) {
            //save field name
            var field = fields[i].getName();
            try {
                // get the property descriptor and of the class based on field name
                PropertyDescriptor pd = new PropertyDescriptor(field, Turnover.class);
                // saves the getter method to variable getter
                Method getter = pd.getReadMethod();
                // invokes the method on the given instance of a class
                // after the class is the list of arguments if any
                // if object.getField != null
                // TODO make an array of field names to ignore?
                // String[] ignore = {"none"};
                if (getter.invoke(turnover) != null && getter.invoke(turnover) != "retailer"){
                    // get the setter method
                    Method setter = pd.getWriteMethod();
                    // use the setter method to set the value of the current object
                    // to the value of the new object
                    setter.invoke(current, getter.invoke(turnover));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return repo.save(current);
    }

    @Override
    public void delete(long id) {
        Turnover t = findById(id);
        repo.delete(t);
    }
}
