package com.grocerystore.controller.model;

import com.grocerystore.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressData {
    private Long addressId;
    private String streetAddress;
    private String city;
    private int zip;
    private String state;
    private String country;

    public AddressData(Address address) {
        this.addressId = address.getAddressId();
        this.streetAddress = address.getStreetAddress();
        this.city = address.getCity();
        this.zip = address.getZip();
        this.state = address.getState();
        this.country = address.getCountry();
    }

    public void setAddress(Address address){
        AddressData addressData= new AddressData();
        address.setAddressId(addressData.getAddressId());
        address.setStreetAddress(addressData.getStreetAddress());
        address.setCity(addressData.getCity());
        address.setZip(addressData.getZip());
        address.setState(addressData.getState());
        address.setCountry(addressData.getCountry());

    }
}
