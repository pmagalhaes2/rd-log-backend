package com.example.rd_log_api.controllers;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses.")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @Operation(summary = "Get address by ID", description = "Retrieves an address by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @Operation(summary = "Create an address", description = "Creates a new address.")
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @Operation(summary = "Update an address", description = "Updates an existing address.")
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return addressService.updateAddress(id, addressDetails);
    }

    @Operation(summary = "Delete an address", description = "Deletes an address by its ID.")
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
