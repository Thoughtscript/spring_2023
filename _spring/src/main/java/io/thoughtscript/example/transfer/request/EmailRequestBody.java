package io.thoughtscript.example.transfer.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
// Must include this annotation since Spring instantiates the @ResponseBody type with no args.
@NoArgsConstructor
public class EmailRequestBody {
    private String message;
}