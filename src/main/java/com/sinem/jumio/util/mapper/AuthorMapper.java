package com.sinem.jumio.util.mapper;

import com.sinem.jumio.dataTransferObject.AuthorDTO;
import com.sinem.jumio.domainObject.AuthorDO;

public class AuthorMapper
{
    public static AuthorDO makeAuthorDO(AuthorDTO authorDTO)
    {
        return new AuthorDO(authorDTO.getFirstName(), authorDTO.getLastName(), authorDTO.getBooksOfAuthor());
    }


    public static AuthorDTO makeAuthorDTO(AuthorDO authorDO)
    {
        AuthorDTO.AuthorDTOBuilder authorDTOBuilder = AuthorDTO.newBuilder()
            .setId(authorDO.getId())
            .setFirstName(authorDO.getFirstName())
            .setLastName(authorDO.getLastName())
            .setBooksOfAuthor(authorDO.getBookOfAuthor());

        return authorDTOBuilder.createAuthorDTO();
    }

}
