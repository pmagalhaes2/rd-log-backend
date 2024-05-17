package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyDto;
import com.example.rd_log_api.domain.entities.CustomLogisticCompany;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomLogisticCompanyMapper {

    public static CustomLogisticCompany mapToEntityFromCreationRequest(LogisticCompanyCreationRequest request) {
        CustomLogisticCompany logisticCompany = new CustomLogisticCompany();
        logisticCompany.setName(request.getName());
        logisticCompany.setCnpj(request.getCnpj());
        logisticCompany.setOpeningHours(convertStringToTime(String.valueOf(request.getOpening_hours())));
        logisticCompany.setClosingHours(convertStringToTime(String.valueOf(request.getClosing_hours())));
        logisticCompany.setPhoneNumber(request.getPhone_number());
        logisticCompany.setEmail(request.getEmail());
        logisticCompany.setAcceptsDangerousLoads(request.getAccepts_dangerous_loads());
        logisticCompany.setPassword(request.getPassword());
        return logisticCompany;
    }

    public static CustomLogisticCompany mapToEntityFromLogisticDto(LogisticCompanyDto dto) {
        CustomLogisticCompany logisticCompany = new CustomLogisticCompany();
        logisticCompany.setId(dto.getId());
        logisticCompany.setName(dto.getName());
        logisticCompany.setCnpj(dto.getCnpj());
        logisticCompany.setOpeningHours(dto.getOpening_hours());
        logisticCompany.setClosingHours(dto.getClosing_hours());
        logisticCompany.setPhoneNumber(dto.getPhone_number());
        logisticCompany.setEmail(dto.getEmail());
        logisticCompany.setAcceptsDangerousLoads(dto.getAccepts_dangerous_loads());
        return logisticCompany;
    }

    public static LogisticCompanyDto mapToDto(CustomLogisticCompany entity) {
        LogisticCompanyDto dto = new LogisticCompanyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCnpj(entity.getCnpj());
        dto.setOpening_hours(entity.getOpeningHours());
        dto.setClosing_hours(entity.getClosingHours());
        dto.setPhone_number(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        dto.setAccepts_dangerous_loads(entity.getAcceptsDangerousLoads());
        return dto;
    }

    private static Time convertStringToTime(String timeString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date parsedDate = dateFormat.parse(timeString);
            return new Time(parsedDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter string para time: " + timeString, e);
        }
    }

    private static String convertTimeToString(Time time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(time);
    }
}
