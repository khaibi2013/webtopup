package com.example.web_top_up.form;

import javax.persistence.Lob;

public class FileForm {

    private Long id;

    private String docName;

    private String docType;

    @Lob
    private byte[] data;
}
