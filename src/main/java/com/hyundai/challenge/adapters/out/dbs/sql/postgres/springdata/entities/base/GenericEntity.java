package com.hyundai.challenge.adapters.out.dbs.sql.postgres.springdata.entities.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericEntity implements Persistable<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private UUID id;
    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return result;
    }
}