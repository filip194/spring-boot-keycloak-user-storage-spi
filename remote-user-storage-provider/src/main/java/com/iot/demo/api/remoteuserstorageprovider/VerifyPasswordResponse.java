package com.iot.demo.api.remoteuserstorageprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyPasswordResponse
{
    private boolean verified;
}
