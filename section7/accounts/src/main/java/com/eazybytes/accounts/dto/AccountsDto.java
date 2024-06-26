package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account and Account information"
)
public class AccountsDto {

    @NotEmpty(message = "AccountNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Account Number of Eazy Bank account", example = "12345678"
    )
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be null or empty")
    @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "branchAddress cannot be null or empty")
    @Schema(
            description = "Eazy Bank address", example = "123 New York"
    )
    private String branchAddress;
}
