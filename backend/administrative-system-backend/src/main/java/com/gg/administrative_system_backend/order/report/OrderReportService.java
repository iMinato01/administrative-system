package com.gg.administrative_system_backend.order.report;

import com.gg.administrative_system_backend.order.entity.Order;
import com.gg.administrative_system_backend.order.service.OrderService;
import com.gg.administrative_system_backend.shared.report.ReportRoute;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderReportService {
    private final ReportHelper reportHelper;
    private final OrderService orderService;

    public byte[] exportPdf(Long id) throws Exception{
        Order order = orderService.findOrder(id);
        return reportHelper.generatePdf(ReportRoute.ORDER, ReportUtils.getOrderHeader(order), order.getItems());
    }
}
