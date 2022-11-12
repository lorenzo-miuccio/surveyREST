package com.survey.tool;

import org.springframework.data.domain.Sort;

public class SortCriteria {
    private String active;
    private String direction;

    public SortCriteria() {

    }

    public SortCriteria(String active, String direction) {
        this.active     = active;
        this.direction = direction;
    }

    public String getActive()        { return this.active;     }
    public String getDirection()    { return this.direction; }

    public void setActive(String active) {
        this.active = active;
    }

    public void setDirection(String direction)  { this.direction = direction; }

    public Sort.Direction getSortDirection() {
        if (this.direction.equals("asc"))   { return Sort.Direction.ASC;  }
        if (this.direction.equals("desc"))  { return Sort.Direction.DESC; }

        return Sort.Direction.ASC;
    }

    @Override
    public String toString() {
        return "SortCriteria{" +
                "field='" + active + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
