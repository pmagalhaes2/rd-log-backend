package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.requests.AddressUpdateRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LoginResponse;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.mappers.LogisticCompanyMapper;
import com.example.rd_log_api.gateways.DistanceMatrixService;
import com.example.rd_log_api.gateways.ZipCodeService;
import com.example.rd_log_api.repositories.AddressRepository;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LogisticCompanyService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final LogisticCompanyRepository repository;
    private final AddressRepository addressRepository;
    private final ZipCodeService zipCodeService;
    private final DistanceMatrixService distanceMatrixService;
    private final DistanceExtractorService distanceExtractorService;

    @Autowired
    public LogisticCompanyService(LogisticCompanyRepository repository, AddressRepository addressRepository, ZipCodeService zipCodeService, DistanceMatrixService distanceMatrixService, DistanceExtractorService distanceExtractorService) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.zipCodeService = zipCodeService;
        this.distanceMatrixService = distanceMatrixService;
        this.distanceExtractorService = distanceExtractorService;
    }

    public List<LogisticCompanyDto> getAll() {
        return repository.findAll().stream().map(LogisticCompanyMapper::toLogisticDto).toList();
    }

    public LogisticCompanyDto getById(Long id) throws NotFoundException {
        return LogisticCompanyMapper.toLogisticDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LogisticCompanyDto.class, String.valueOf(id))));
    }

    public LogisticCompanyCreationRequest createLogisticCompany(LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompany newLogisticCompany = LogisticCompanyMapper.toEntityFromCreationRequest(logisticCompany);
        newLogisticCompany.setPassword(passwordEncoder.encode(newLogisticCompany.getPassword()));
        repository.save(newLogisticCompany);
        return LogisticCompanyMapper.toCreationRequestDto(newLogisticCompany);
    }

    @Transactional
    public LogisticCompanyDto updateLogisticCompany(Long id, LogisticCompanyUpdateRequest logisticCompany) throws NotFoundException {
        LogisticCompany existingLogisticCompany = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LogisticCompanyDto.class, String.valueOf(id)));

        updateLogisticCompanyFields(existingLogisticCompany, logisticCompany);

        Address existingAddress = getAddressFromLogisticCompany(existingLogisticCompany);
        AddressUpdateRequest addressRequest = logisticCompany.getAddress();
        updateAddressFields(existingAddress, addressRequest);
        addressRepository.save(existingAddress);

        LogisticCompany updatedLogisticCompany = repository.save(existingLogisticCompany);

        return LogisticCompanyMapper.toLogisticDto(updatedLogisticCompany);
    }

    private void updateLogisticCompanyFields(LogisticCompany logisticCompany, LogisticCompanyUpdateRequest updateRequest) {
        logisticCompany.setName(updateRequest.getName());
        logisticCompany.setOpeningHours(updateRequest.getOpening_hours());
        logisticCompany.setClosingHours(updateRequest.getClosing_hours());
        logisticCompany.setPhoneNumber(updateRequest.getPhone_number());
        logisticCompany.setEmail(updateRequest.getEmail());
        logisticCompany.setPriceKm(updateRequest.getPrice_km());
        logisticCompany.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
    }

    private Address getAddressFromLogisticCompany(LogisticCompany logisticCompany) {
        return logisticCompany.getAddress();
    }

    private void updateAddressFields(Address address, AddressUpdateRequest updateRequest) {
        address.setValue(updateRequest.getValue());
        address.setNumber(updateRequest.getNumber());
        address.setCity(updateRequest.getCity());
        address.setState(updateRequest.getState());
        address.setZipCode(updateRequest.getZipCode());
        address.setComplement(updateRequest.getComplement());
    }

    @Transactional
    public void deleteLogisticCompany(Long id) throws NotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(LogisticCompany.class, String.valueOf(id));
        }
    }

    public LoginResponse login(LoginDto loginDto) {
        LogisticCompany logisticCompany = repository.findByEmail(loginDto.getEmail());
        if (logisticCompany != null && logisticCompany.getPassword() != null && passwordEncoder.matches(loginDto.getPassword(), logisticCompany.getPassword())) {
            return new LoginResponse("Login realizado com sucesso.", logisticCompany.getId(), logisticCompany.getName());
        } else {
            throw new RuntimeException("Dados inválidos, tente novamente.");
        }
    }

    public List<LogisticCompanyDto> findCompaniesInSameState(String originState, String destinationState) {
        List<LogisticCompany> companiesInOriginState = repository.findByAddressState(originState);
        List<LogisticCompany> companiesInDestinationState = repository.findByAddressState(destinationState);

        companiesInOriginState.retainAll(companiesInDestinationState);

        return companiesInOriginState.stream()
                .map(LogisticCompanyMapper::toLogisticDto)
                .collect(Collectors.toList());
    }

    public List<LogisticCompanyDto> findNearestCompanies(String origin, String destination, String key) {
        List<LogisticCompanyDto> companies = repository.findByAddressState(destination).stream()
                .map(LogisticCompanyMapper::toLogisticDto)
                .collect(Collectors.toList());

        return companies.stream()
                .sorted((c1, c2) -> {
                    String zipCode1 = c1.getAddress().getZipCode();
                    String zipCode2 = c2.getAddress().getZipCode();

                    String response1 = distanceMatrixService.getDistance(origin, zipCode1, key);
                    String response2 = distanceMatrixService.getDistance(origin, zipCode2, key);

                    int distance1 = distanceExtractorService.extractDistanceFromResponse(response1);
                    int distance2 = distanceExtractorService.extractDistanceFromResponse(response2);

                    return Integer.compare(distance1, distance2);
                })
                .collect(Collectors.toList());
    }

}