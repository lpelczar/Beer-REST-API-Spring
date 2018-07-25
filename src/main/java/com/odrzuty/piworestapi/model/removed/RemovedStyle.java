package com.odrzuty.piworestapi.model.removed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "styles")
@Setter
@Getter
public class RemovedStyle implements Serializable {

    @Column(name = "cat_name")
    @Size(max = 255)
    private String categoryName;

    @Column(name = "style_name")
    @Size(max = 255)
    private String styleName;

    public RemovedStyle() {
    }

    public RemovedStyle(@Size(max = 255) String categoryName, @Size(max = 255) String styleName) {
        this.categoryName = categoryName;
        this.styleName = styleName;
    }
}
