package com.project.hana_piece.common.scheduler;

import com.project.hana_piece.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoDebitScheduler {

    private final AccountService accountService;

    /**
     * 매일 0시 자동이체 일괄 처리
     */
    @Scheduled(cron = "0 0 0 * * ?")  //매일 0시
    //@Scheduled(cron = "0 * * * * ?")    // TODO 테스트 용 1분마다
    public void runAutoDebit() {
        accountService.executeTodayAccountAutoDebit();
    }
}
