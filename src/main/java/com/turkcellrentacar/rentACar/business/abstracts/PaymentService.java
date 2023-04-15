package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreatePaymentRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdatePaymentRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreatePaymentResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllPaymentsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetPaymentResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdatePaymentResponse;
import com.turkcellrentacar.rentACar.common.dto.CreateRentalPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(int id);
    CreatePaymentResponse add(CreatePaymentRequest request);
    UpdatePaymentResponse update(int id, UpdatePaymentRequest request);
    void delete(int id);
    void processRentalPayment(CreateRentalPaymentRequest request);
}
