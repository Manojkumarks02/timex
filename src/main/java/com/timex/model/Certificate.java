package com.timex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CERTIFICATE_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Certificate name is required")
    private String certificateName;

    @NotBlank(message = "Completion ID is required")
    private String completionId;

    @Lob
    private byte[] fileData;
}
