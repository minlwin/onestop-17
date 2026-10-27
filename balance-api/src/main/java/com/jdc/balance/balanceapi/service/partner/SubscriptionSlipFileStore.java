package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Slf4j
public class SubscriptionSlipFileStore {

    @Value("${app.storage.path}")
    private String storagePath;
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @PostConstruct
    void init() {
        try {
            var storage = Path.of(storagePath);
            if(!Files.exists(storage)) {
                Files.createDirectories(storage);
            }
        } catch (IOException e) {
            log.error("Storage Creation Error", e);
        }
    }

    public String save(Company company, MultipartFile file) {
        try {
            var storage = Path.of(storagePath);
            var fileName = getFileName(company.getId(), file.getOriginalFilename());
            var filePath = storage.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            return fileName;
        } catch (IOException e) {
            log.error("Payment Slip Creation Error", e);
            throw new RuntimeException(e);
        }
    }

    private String getFileName(UUID companyId, String fileName) {
        var array = fileName.split("\\.");
        var timeStamp = LocalDateTime.now().format(DF);
        return "%s-%s.%s".formatted(timeStamp, companyId, array[array.length - 1]);
    }
}
