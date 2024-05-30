package com.project.hana_piece.product.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(){
        super("Could not find Product");
    }

    public ProductNotFoundException(Long id){
        super("Could not find Product with id: " + id);
    }
}
