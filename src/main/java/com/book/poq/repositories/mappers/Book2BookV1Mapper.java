package com.book.poq.repositories.mappers;

import com.book.poq.model.BookV0;
import com.book.poq.model.BookV1;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface Book2BookV1Mapper {

    BookV1 bookV0ToBookV1(BookV0 book);

    BookV0 bookV1ToBookV0(BookV1 book);

}
