package org.example.ecommercekafkaexample.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    @NotBlank
    private String shipmentNumber;

    @Email
    @NotBlank
    private String recipientEmail;

    @NotBlank
    private String recipientCountryCode;

    @NotBlank
    private String senderCountryCode;

    @Min(0)
    @Max(100)
    private int statusCode;

}