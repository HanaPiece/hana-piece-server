package com.project.hana_piece.product.exception;

import com.project.hana_piece.common.exception.ValueInvalidException;

public class InvalidCategoryException extends ValueInvalidException {
    public InvalidCategoryException(String category){
        super("Invalid category: " + category);
    }
}
