package com.acme.interlab.resource;

import com.acme.interlab.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResource extends AuditModel {
    public long Id;
    public String Name;
    public String Description;
    public String Sector;
    public String Mail;
    public String Phone_number;
    public String Address;
    public String Country;
    public String City;

}