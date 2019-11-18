package com.github.romankh3.flightsmonitoring.scheduler;

import com.github.romankh3.flightsmonitoring.service.RecountMinPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SchedulerTasks {

    @Autowired
    private RecountMinPriceService recountMinPriceService;

    private static final long TEN_MINUTES = 1000 * 60 * 10;

    @Scheduled(fixedRate = TEN_MINUTES)
    public void recountMinPrice() {
        log.debug("recount minPrice Started");
        recountMinPriceService.recount();
        log.debug("recount minPrice finished");
    }

}
