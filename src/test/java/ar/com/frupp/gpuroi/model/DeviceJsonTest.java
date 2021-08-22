package ar.com.frupp.gpuroi.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceJsonTest {

    @Test
    void jacksonShouldMapWithoutErrors() throws JsonProcessingException {
        var json = "{\"id\": \"41f10416-60f5-4e97-904f-48caf2b6b727\",\"name\": \"NVIDIA TITAN XP\",\"niceName\": \"nvidia-titan-xp\",\"nhmId\": \"titan xp\",\"category\": \"GPU\",\"power\": 240,\"speeds\": \"{\\\"SCRYPT\\\":\\\"0\\\",\\\"SHA256\\\":\\\"0\\\",\\\"SCRYPTNF\\\":\\\"0\\\",\\\"X11\\\":\\\"0\\\",\\\"X13\\\":\\\"0\\\",\\\"KECCAK\\\":\\\"\\\",\\\"X15\\\":\\\"0\\\",\\\"NIST5\\\":\\\"\\\",\\\"NEOSCRYPT\\\":\\\"1.791\\\",\\\"LYRA2RE\\\":\\\"0\\\",\\\"WHIRLPOOLX\\\":\\\"0\\\",\\\"QUBIT\\\":\\\"0\\\",\\\"QUARK\\\":\\\"0\\\",\\\"AXIOM\\\":\\\"0\\\",\\\"LYRA2REV2\\\":\\\"\\\",\\\"SCRYPTJANENF16\\\":\\\"0\\\",\\\"BLAKE256R8\\\":\\\"0\\\",\\\"BLAKE256R14\\\":\\\"0\\\",\\\"BLAKE256R8VNL\\\":\\\"0\\\",\\\"HODL\\\":\\\"0\\\",\\\"DAGGERHASHIMOTO\\\":45.39,\\\"DECRED\\\":\\\"5.324\\\",\\\"CRYPTONIGHT\\\":\\\"\\\",\\\"LBRY\\\":\\\"\\\",\\\"EQUIHASH\\\":\\\"\\\",\\\"PASCAL\\\":\\\"1.958\\\",\\\"X11GOST\\\":\\\"0\\\",\\\"SIA\\\":\\\"3.28\\\",\\\"BLAKE2S\\\":\\\"\\\",\\\"SKUNK\\\":\\\"0\\\",\\\"CRYPTONIGHTV7\\\":\\\"\\\",\\\"CRYPTONIGHTHEAVY\\\":\\\"0\\\",\\\"LYRA2Z\\\":\\\"0\\\",\\\"X16R\\\":\\\"0\\\",\\\"CRYPTONIGHTV8\\\":\\\"0\\\",\\\"SHA256ASICBOOST\\\":\\\"0\\\",\\\"ZHASH\\\":77.13,\\\"BEAM\\\":\\\"0\\\",\\\"GRINCUCKAROO29\\\":\\\"0\\\",\\\"GRINCUCKATOO31\\\":1.72,\\\"LYRA2REV3\\\":48.6,\\\"CRYPTONIGHTR\\\":\\\"0\\\",\\\"CUCKOOCYCLE\\\":\\\"0\\\",\\\"X16RV2\\\":42.53,\\\"GRINCUCKAROOD29\\\":9.35,\\\"BEAMV2\\\":53.31,\\\"CUCKAROOM\\\":5.19,\\\"GRINCUCKATOO32\\\":0.61,\\\"HANDSHAKE\\\":0.26,\\\"KAWPOW\\\":25.88,\\\"BEAMV3\\\":25}\",\"powers\": \"{}\",\"meta\": \"{\\\"nhqm\\\":true,\\\"nhos\\\":true}\",\"hidden\": false,\"sortOrder\": 0,\"createdTs\": \"2019-08-12T11:12:31.062Z\",\"nhFirmware\": null,\"qmOptions\": null,\"lockedSpeeds\": \"{}\",\"paying\": 0.000072}";
        json = json.replace("\\\"", "\"");
        json = json.replace("\"{", "{");
        json = json.replace("}\"", "}");

        var mapper = new ObjectMapper();

        var device = mapper.readValue(json, DeviceJson.class);

        assertNotNull(device);
        assertNotNull(device.getId());
        assertTrue(device.isGpu());
    }
}