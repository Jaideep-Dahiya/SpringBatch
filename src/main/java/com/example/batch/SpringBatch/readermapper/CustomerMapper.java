package com.example.batch.SpringBatch.readermapper;

import com.example.batch.SpringBatch.dto.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;

public class CustomerMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fs) throws BindException {
        Customer customer = new Customer();
        customer.setFirstName(fs.readString(0));
        customer.setLastName(fs.readString(1));
        customer.setAddress(fs.readString(2));
        customer.setCity(fs.readString(3));
        customer.setState(fs.readString(4));
        customer.setPin(fs.readInt(5));
        return customer;
    }
}
