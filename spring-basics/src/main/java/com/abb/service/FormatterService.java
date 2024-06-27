package com.abb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("formatter")
@Slf4j
public class FormatterService {

    public FormatterService() {
        log.debug("FormatterService constructor called");
    }

    public String formatAsBox(String text){

        StringBuffer sb = new StringBuffer(1000);
        sb.append("|");
        sb.append("-".repeat(text.length()+2));
        sb.append("|\n");
        sb.append("| ");
        sb.append(text);
        sb.append(" |\n");sb.append("|");
        sb.append("-".repeat(text.length()+2));
        sb.append("|\n");

        return sb.toString();
    }

}
