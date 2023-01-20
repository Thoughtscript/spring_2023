package io.thoughtscript.example.transfer.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UuidRequestBody extends RequestBody {
  private String id;
}
