package com.xu.textread.model.dto;

import com.xu.textread.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author aniki
 * @CreteDate 2023/2/8 20:16
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TextSearchQuery extends PageRequest {

    private String searchText;
}
