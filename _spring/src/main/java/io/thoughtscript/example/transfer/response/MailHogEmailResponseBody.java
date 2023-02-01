package io.thoughtscript.example.transfer.response;

import io.thoughtscript.example.transfer.request.GenericRequestBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailHogEmailResponseBody extends GenericRequestBody {
    private int status;
    private String message;
}
