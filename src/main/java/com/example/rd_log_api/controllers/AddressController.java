package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses.", tags = {"Address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of addresses retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)))
    })
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @Operation(summary = "Get address by ID", description = "Retrieves an address by its ID.", tags = {"Address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    @GetMapping("/{id}")
    public Optional<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @Operation(summary = "Create an address", description = "Creates a new address.", tags = {"Address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class)))
    })
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address);
    }

    @Operation(summary = "Update an address", description = "Updates an existing address.", tags = {"Address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Address.class))),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        return addressService.updateAddress(id, addressDetails);
    }

    @Operation(summary = "Delete an address", description = "Deletes an address by its ID.", tags = {"Address"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
