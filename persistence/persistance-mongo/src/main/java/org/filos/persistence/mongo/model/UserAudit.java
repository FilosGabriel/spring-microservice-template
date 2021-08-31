package org.filos.persistence.mongo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAudit {
    @CreatedDate
    private LocalDateTime creationTime;
    @LastModifiedDate
    private LocalDateTime modificationTime;
}
