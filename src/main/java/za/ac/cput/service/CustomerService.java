package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.Customer;
import za.ac.cput.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService{

    private static CustomerService customerService=null;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer read(String id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if(this.customerRepository.existsById(customer.getId()))
            return this.customerRepository.save(customer);
        return null;
    }

    @Override
    public boolean delete(String id) {
        this.customerRepository.deleteById(id);

        if(this.customerRepository.existsById(id))
            return false;
        else
            return true;
    }

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll().stream().collect(Collectors.toList());
    }
}
