package jpabook.jpashop.domain;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    private Date createdDate;
    private Date lastModifiedDate;
}
