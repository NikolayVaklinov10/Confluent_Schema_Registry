package com.nikolayvaklinov.avro.specific;

import com.example.Customer;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecordExamples {

    public static void main(String[] args) {

        // step 1: create specific record
        Customer.Builder customerBuilder = new Customer.Builder();
        customerBuilder.setAge(25);
        customerBuilder.setFirstName("John");
        customerBuilder.setLastName("Doe");
        customerBuilder.setHeight(175.5f);
        customerBuilder.setWeight(80.5f);
        customerBuilder.setAutomatedEmail(false);
        Customer customer = customerBuilder.build();

        System.out.println(customer);

        // step 2: write that generic record to a file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
            System.out.println("successfully wrote customer-specific.avro");
        } catch (IOException e){
            e.printStackTrace();
        }

        // step 3: read from file


        // step 4: interpret



    }
}
