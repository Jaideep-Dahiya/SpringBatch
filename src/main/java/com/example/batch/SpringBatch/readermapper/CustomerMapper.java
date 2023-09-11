package com.example.batch.SpringBatch.readermapper;

import com.example.batch.SpringBatch.dto.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class CustomerMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Customer customer = new Customer();
        customer.setFirstName(fieldSet.readString(0));
        customer.setLastName(fieldSet.readString(1));
        customer.setAddress(fieldSet.readString(2));
        customer.setState(fieldSet.readString(3));
        customer.setCity(fieldSet.readString(4));
        customer.setPin(fieldSet.readInt(5));
        return customer;
    }
}
