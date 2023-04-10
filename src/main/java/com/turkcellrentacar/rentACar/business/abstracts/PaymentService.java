package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import com.turkcellrentacar.rentACar.core.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}
