package challenge.core.services.main;

import com.hyundai.challenge.model.PostPurchaseReportResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ReportService {

    Mono<PostPurchaseReportResponse> postPurchaseReport(LocalDate date, String model, String cryptocurrency);
}
