package Util;

import com.hyundai.challenge.adapters.common.dto.vehicle.VehicleModelDto;
import com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.VehiclePurchase;
import com.hyundai.challenge.adapters.common.dto.price.livecoin.DataPriceLiveDto;
import com.hyundai.challenge.adapters.common.dto.price.livecoin.PriceLiveDto;
import com.hyundai.challenge.domain.base.ModelVehicleDomain;
import com.hyundai.challenge.domain.catalog.CatalogVehicleDomain;
import com.hyundai.challenge.domain.purchase.PurchaseVehicleDomain;
import com.hyundai.challenge.domain.report.ReportPurchaseVehicleDomain;
import com.hyundai.challenge.model.*;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

public class MockData {
    public static final Object RETRIEVE_REQUEST_STRING = "{\"data\":{\"model\":\"TUCSON\",\"cryptoCurrency\":\"ETH\"}}";
    public static final String REPORT_REQUEST_STRING = "{\"data\":{\"date\":\"2023-07-02\",\"model\":\"TUCSON\",\"cryptocurrency\":\"ETH\"}}";

    public static String getPurchaseRequestString(String convertionId) {
        return "{\"data\":{\"convertionId\":\"" + convertionId + "\",\"fullName\":\"Juan Perez 2\",\"version\":\"ALL NEW TUCSON TA HGS\",\"model\":\"TUCSON\",\"cryptocurrency\":\"BTC\"}}";
    }
    public static ServerWebExchange buildServerWebExchange() {
        MockServerHttpRequest request = MockServerHttpRequest.get("http://localhost")
                .header("X-CF-Forwarded-Url", "https:example.com")
                .header("Accept", "application/json")
                .build();
        return MockServerWebExchange.from(request);
    }

    public static PostPurchaseReportRequest getPostPurchaseReportRequest() {
        PostPurchaseReportRequestData requestData = new PostPurchaseReportRequestData();
        requestData.setDate(LocalDate.of(2023, 6, 1));
        requestData.setModel("TUCSON");
        requestData.setCryptocurrency("BTC");
        return new PostPurchaseReportRequest().data(requestData);
    }

    public static PostPurchaseReportResponse getPostPurchaseReportResponse() {
        PostPurchaseReportResponseData responseData = new PostPurchaseReportResponseData();
        responseData.setDate("2023-06-02");
        responseData.setModel("TUCSON");
        responseData.setCryptocurrency("BTC");
        responseData.setUsdAmount(new BigDecimal("245000"));
        responseData.setCryptocurrencyAmount(new BigDecimal("5"));
        return new PostPurchaseReportResponse().data(Arrays.asList(responseData));
    }

    public static PostPurchaseVehicleModelResponse getPostPurchaseVehicleModelResponse() {
        PostPurchaseVehicleModel vehicleModel = new PostPurchaseVehicleModel();
        vehicleModel.setFullName("Juan Perez");
        vehicleModel.setVersion("TUCSON TL");
        vehicleModel.setModel("TUCSON");
        vehicleModel.setCryptocurrency("BTC");
        vehicleModel.setPriceUsd(new BigDecimal("25000"));
        vehicleModel.setPriceCryptocurrency(new BigDecimal("1"));
        vehicleModel.setDate("2023-06-02T12:01:45");
        vehicleModel.setPurchaseId("yyy-yyy-yyy-yyy");
        return new PostPurchaseVehicleModelResponse().data(vehicleModel);
    }

    public static PostPurchaseVehicleModelRequest getPostPurchaseVehicleModelRequest() {
        PostPurchaseVehicleModelRequestData requestData = new PostPurchaseVehicleModelRequestData();
        requestData.setConvertionId("xxx-xxx-xxx-xxxx");
        requestData.setFullName("Juan Perez");
        requestData.setVersion("TUCSON TL");
        requestData.setModel("TUCSON");
        requestData.setCryptocurrency("BTC");
        return new PostPurchaseVehicleModelRequest().data(requestData);
    }

    public static PostVehicleModelRetrieveRequest getPostVehicleModelRetrieveRequest() {
        DataRequest requestData = new DataRequest();
        requestData.setModel("TUCSON");
        requestData.setCryptoCurrency("BTC");
        return new PostVehicleModelRetrieveRequest().data(requestData);
    }

    public static VehicleVersion getVehicleVersion() {
        VehicleVersion vehicleVersion = new VehicleVersion();
        vehicleVersion.setModel("TUCSON");
        vehicleVersion.setVersion("TL");
        vehicleVersion.setPriceUsd(new BigDecimal("25000.00"));
        vehicleVersion.setPriceCryptocurrency(new BigDecimal("2.5"));
        vehicleVersion.setCryptocurrency("BTC");
        return vehicleVersion;

    }

    public static PostVehicleModelRetrieveResponse getPostVehicleModelRetrieveResponse() {

        DataResponse dataResponse = new DataResponse();

        dataResponse.setConvertionId("123456");
        dataResponse.setConversionTimelife("2023-07-02T14:31:10");
        VehicleVersion vehicleVersion1 = new VehicleVersion();
        vehicleVersion1.setModel("TUCSON");
        vehicleVersion1.setVersion("TL");
        vehicleVersion1.setPriceUsd(new BigDecimal("25000.00"));
        vehicleVersion1.setPriceCryptocurrency(new BigDecimal("2.5"));
        vehicleVersion1.setCryptocurrency("BTC");

        VehicleVersion vehicleVersion2 = new VehicleVersion();
        vehicleVersion2.setModel("SANTA FE");
        vehicleVersion2.setVersion("GL");
        vehicleVersion2.setPriceUsd(new BigDecimal("30000.00"));
        vehicleVersion2.setPriceCryptocurrency(new BigDecimal("3.0"));
        vehicleVersion2.setCryptocurrency("ETH");
        dataResponse.addVersionsItem(vehicleVersion1);
        dataResponse.addVersionsItem(vehicleVersion2);
        return new PostVehicleModelRetrieveResponse().data(dataResponse);
    }

    public static VehiclePurchase getVehiclePurchase() {
        VehiclePurchase vehiclePurchase = new VehiclePurchase();
        vehiclePurchase.setId(UUID.randomUUID());
        vehiclePurchase.setFullName("John Doe");
        vehiclePurchase.setVersion("1.0");
        vehiclePurchase.setModel("TUCSON");
        vehiclePurchase.setCryptocurrency("Bitcoin");
        vehiclePurchase.setPriceUsd(new BigDecimal("25000.00"));
        vehiclePurchase.setPriceCryptocurrency(new BigDecimal("5.50"));
        vehiclePurchase.setDate(LocalDate.now());
        return vehiclePurchase;
    }


    public static PriceLiveDto getPriceLiveDto() {
        return PriceLiveDto.builder().data(DataPriceLiveDto.builder().lastPrice(BigDecimal.valueOf(20000)).build()).build();
    }

    public static ModelVehicleDomain getModelVehicleDomain() {
        ModelVehicleDomain modelVehicleDomain = new ModelVehicleDomain();
        modelVehicleDomain.setId(UUID.randomUUID());
        modelVehicleDomain.setVersion("1.5");
        modelVehicleDomain.setModel("TUCSON");
        modelVehicleDomain.setCryptocurrency("BTC");
        modelVehicleDomain.setPriceUsd(new BigDecimal("25000"));
        modelVehicleDomain.setPriceCryptocurrency(new BigDecimal("5"));
        return modelVehicleDomain;
    }
    public static ReportPurchaseVehicleDomain getReportPurchaseVehicleDomain() {
        ReportPurchaseVehicleDomain reportPurchaseVehicleDomain= new ReportPurchaseVehicleDomain();
        reportPurchaseVehicleDomain.setModelVehicleDomainList(Arrays.asList(getModelVehicleDomain()));
        return reportPurchaseVehicleDomain;
    }

    public static VehicleModelDto getVehicleModelDto() {
        VehicleModelDto vehicleModelDto = new VehicleModelDto();
        vehicleModelDto.setCode(123);
        vehicleModelDto.setName("Hyundai Tucson");
        vehicleModelDto.setYear(2022);
        vehicleModelDto.setPriceUsd(25000.00);
        vehicleModelDto.setBond(1000.00);
        vehicleModelDto.setFinalPrice(24000.00);
        vehicleModelDto.setDisability(0);
        vehicleModelDto.setSecurityCode("ABC123");
        return vehicleModelDto;
    }

    public static PurchaseVehicleDomain getPurchaseVehicleDomain() {
        PurchaseVehicleDomain purchaseVehicleDomain= new PurchaseVehicleDomain();
        purchaseVehicleDomain.setModelVehicleDomain(getModelVehicleDomain());
        purchaseVehicleDomain.setConvertionId("1");
        purchaseVehicleDomain.setId(UUID.randomUUID());
        purchaseVehicleDomain.setFullName("fullName");
        return purchaseVehicleDomain;
    }

    public static CatalogVehicleDomain getCatalogVehicleDomain() {
        CatalogVehicleDomain catalogVehicleDomain= new CatalogVehicleDomain();
        catalogVehicleDomain.setVersions(Arrays.asList(getModelVehicleDomain()));
        catalogVehicleDomain.setConversionTimelife("20 seg");
        catalogVehicleDomain.setConversionTimelifeValue(20L);
        catalogVehicleDomain.setId(UUID.randomUUID());
        catalogVehicleDomain.setConvertionId("11");
        return catalogVehicleDomain;
    }
}
