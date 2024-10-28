package com.koreait.exam.springbatch_app_10.app.calculate.service;

import com.koreait.exam.springbatch_app_10.app.calculate.entity.CalculateOrderItem;
import com.koreait.exam.springbatch_app_10.app.calculate.repository.CalculateOrderItemRepository;
import com.koreait.exam.springbatch_app_10.app.order.entity.OrderItem;
import com.koreait.exam.springbatch_app_10.app.order.service.OrderService;
import com.koreait.exam.springbatch_app_10.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculateService {
    private final OrderService orderService;
    private final CalculateOrderItemRepository calculateOrderItemRepository;
    public void makeDate(String yearMonth) {
        int monthEndDay = Ut.date.getEndDayOf(yearMonth);
        String fromDateStr = yearMonth + "-01 00:00:00.000000";
        String toDateStr = yearMonth + "-%02d 23:59:59.999999".formatted(monthEndDay);
        LocalDateTime fromDate = Ut.date.parse(fromDateStr);
        LocalDateTime toDate = Ut.date.parse(toDateStr);
        // 데이터 가져오기
        List<OrderItem> orderItems = orderService.findAllByPayDateBetween(fromDate, toDate);
        // 변환하기
        List<CalculateOrderItem> calculateOrderItems = orderItems
                .stream()
                .map(this::toCalculateOrderItem)
                .collect(Collectors.toList());
        // 저장하기s
        calculateOrderItems.forEach(this::makeCalculateOrderItem);
    }
    public void makeCalculateOrderItem(CalculateOrderItem item) {
        CalculateOrderItem oldCalculateOrderItem = calculateOrderItemRepository.findByOrderItemId(item.getOrderItem().getId()).orElse(null);
        if (oldCalculateOrderItem != null) {
            calculateOrderItemRepository.delete(oldCalculateOrderItem);
        }
        calculateOrderItemRepository.save(item);
    }
    public CalculateOrderItem toCalculateOrderItem(OrderItem orderItem) {
        return new CalculateOrderItem(orderItem);
    }
}
