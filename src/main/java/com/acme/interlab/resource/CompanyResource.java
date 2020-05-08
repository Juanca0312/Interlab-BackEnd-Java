package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResource extends AuditModel {
    private long Id;
    private String Name;
    private String Description;
    private String Sector;
    private String Mail;
    private String Phone_number;
    private String Address;
    private String Country;
    private String City;

}